package org.apache.servicemix.examples.cxf.test.ControlSendBundlerInformation;

import java.util.ArrayList;
import java.util.List;

import org.apache.servicemix.examples.cxf.info.BundlerInfo;
import org.apache.servicemix.examples.cxf.info.GatewayInfo;
import org.apache.servicemix.examples.cxf.model.Bundler;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.service.BundlerService;

public class ControlSendBundlerInformationTest {

	private static List<Bundler> listBundlers;
	private BundlerService bundlerService;
	private BundlerInfo bundlerInfo = null;
	
	public static List<Bundler> getListBundlers() {
		return listBundlers;
	}

	public void setBundlerInfo(BundlerInfo bundlerInfo) {
		this.bundlerInfo = bundlerInfo;
	}

	public ControlSendBundlerInformationTest() {
		bundlerService = new BundlerService();
		listBundlers = new ArrayList<Bundler>();
	}

	// information about bundlers
	public void compareInfoBundler(List<Bundler> listTestx) {
		
		List<Bundler> listTest = new ArrayList<Bundler>();
		
		for(Bundler tr : listTestx) {
			Bundler b = new Bundler();
			
			b.setName(tr.getName());
			b.setLocation(tr.getLocation());
			b.setStatus(tr.getStatus());
			b.setVersion(tr.getVersion());
			
			listTest.add(b);
		}

		if (listBundlers.isEmpty() || listBundlers == null) {
			
			listBundlers.addAll(listTest);

			GatewayInfo gatewayInfo = new GatewayInfo();
			Gateway gatewaySend = new Gateway();
			gatewaySend.setMac(gatewayInfo.getMac());
			gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
			gatewaySend.getListBundler().addAll(listBundlers);
			
			// sending information for the first time
			bundlerService.sendListBundlerConnected(gatewaySend);
		} else {

			List<Bundler> listBundlersIntance = listTest;

			List<Bundler> listBundlersDisconnected = this.compareToBundles(listBundlers, listBundlersIntance);

			List<Bundler> listBundlersConnected = this.compareToBundles(listBundlersIntance, listBundlers);

			List<Bundler> listInformationAltered = this.compareToInformation(listBundlersIntance, listBundlers);
			
			if (!(listBundlersDisconnected.isEmpty() || listBundlersDisconnected == null)) {
				this.infoBundlerDisconnected(listBundlersDisconnected);
			}

			if (!(listBundlersConnected.isEmpty() || listBundlersConnected == null)) {
				this.infoBundlerConnected(listBundlersConnected);
			}

			if (!(listInformationAltered.isEmpty() || listInformationAltered == null)) {
				this.infoBundlerAltered(listInformationAltered);
			}

		}

	}

	// method for sending and updating disconnected bundles
	public void infoBundlerDisconnected(List<Bundler> disconnected) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		Gateway gatewaySend = new Gateway();

		// removing bundles from the local information list
		for (Bundler bundlerDisc : disconnected) {
			for (Bundler bundler : listBundlers) {
				if (bundlerDisc.getName().equals(bundler.getName())
						&& bundlerDisc.getVersion().equals(bundler.getVersion())) {
					listBundlers.remove(bundler);
					break;
				}
			}
		}

		// mounting information to be sent
		gatewaySend.setMac(gatewayInfo.getMac());
		gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
		gatewaySend.getListBundler().addAll(disconnected);

		// sending information
		bundlerService.sendListBundlerDisconnected(gatewaySend);
	}

	public void infoBundlerConnected(List<Bundler> connected) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		Gateway gatewaySend = new Gateway();

		// update information locale
		listBundlers.addAll(connected);

		// mounting information to be sent
		gatewaySend.setMac(gatewayInfo.getMac());
		gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
		gatewaySend.getListBundler().addAll(connected);

		// sending information
		bundlerService.sendListBundlerConnected(gatewaySend);
	}

	public void infoBundlerAltered(List<Bundler> altered) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		//List<Bundler> listToUpdate = listBundlers;
		Gateway gatewaySend = new Gateway();

		// update information locale
		for (Bundler bundlerAlter : altered) {
			for (Bundler bundler : listBundlers) {
				if (bundlerAlter.getName().equals(bundler.getName())
						&& bundlerAlter.getVersion().equals(bundler.getVersion())) {
					bundler.setName(bundlerAlter.getName());
					bundler.setVersion(bundlerAlter.getVersion());
					bundler.setLocation(bundlerAlter.getLocation());
					bundler.setStatus(bundlerAlter.getStatus());

					break;
				}
			}
		}

		// mounting information to be sent
		gatewaySend.setMac(gatewayInfo.getMac());
		gatewaySend.setLastUpdate(gatewayInfo.getLastUpdate());
		gatewaySend.getListBundler().addAll(altered);

		// sending information
		bundlerService.sendListBundlerAltered(gatewaySend);

	}

	// returns elements of the first list that does not exist in the second
	// used to find new bundles and disconnected bundles
	public List<Bundler> compareToBundles(List<Bundler> l1, List<Bundler> l2) {
		List<Bundler> result = new ArrayList<Bundler>();
		boolean exist;

		for (Bundler b1 : l1) {
			exist = false;
			for (Bundler b2 : l2) {
				if ((b1.getName().equals(b2.getName())) && (b1.getVersion().equals(b2.getVersion()))) {
					exist = true;
					break;
				}
			}
			if (exist == false) {
				result.add(b1);
			}
		}
		return result;
	}

	// returns bundles with changed information
	public List<Bundler> compareToInformation(List<Bundler> l1, List<Bundler> l2) {
		List<Bundler> result = new ArrayList<Bundler>();
		boolean exist;

		for (Bundler b1 : l1) {
			exist = false;
			for (Bundler b2 : l2) {
				if ((b1.getName().equals(b2.getName())) && (b1.getVersion().equals(b2.getVersion()))) {
					if (b1.getLocation().equals(b2.getLocation()) && b1.getStatus().equals(b2.getStatus())) {
						exist = true;
					}
					break;
				}
			}
			if (exist == false) {
				result.add(b1);
			}
		}

		return result;
	}

}
