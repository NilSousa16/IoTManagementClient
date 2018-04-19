package org.apache.servicemix.examples.cxf.test.ControlSendServiceInformation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.servicemix.examples.cxf.info.GatewayInfo;
import org.apache.servicemix.examples.cxf.model.Bundler;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.model.Service;

public class ControlSendServiceInformationTestMain2 {
	static List<Service> listServiceDB = new ArrayList<Service>();
	
	public static void main(String[] args) {

		List<Bundler> listBundlerSys = new ArrayList<Bundler>();

		for (int i = 1; i <= 12; i++) {
			Bundler bundler = new Bundler();

			bundler.setName(String.valueOf(i) + "N");
			bundler.setLocation(String.valueOf(i) + "L");
			bundler.setStatus(String.valueOf(i) + "S");
			bundler.setVersion(String.valueOf(i) + "V");

			listBundlerSys.add(bundler);

		}

		List<Bundler> listBundlerDB = new ArrayList<Bundler>();

		for (int i = 1; i <= 20; i++) {
			Bundler bundler = new Bundler();

			bundler.setName(String.valueOf(i) + "N");
			bundler.setLocation(String.valueOf(i) + "L");
			bundler.setStatus(String.valueOf(i) + "S");
			bundler.setVersion(String.valueOf(i) + "V");

			listBundlerDB.add(bundler);

		}

		// List<Bundler> listCompare =
		// compareToServiceInformationCompareBundlers(listBundlerSys,
		// listBundlerDB);
		//
		// if(listCompare != null && !listCompare.isEmpty()) {
		// for(Bundler b : listCompare) {
		// System.out.println(b.getName());
		// System.out.println(b.getState());
		// System.out.println(b.getVersion());
		// System.out.println(b.getLocation());
		// System.out.println("------------");
		// }
		// } else {
		// System.out.println("Not bundlers.");
		// }

		List<Service> listServiceSys = new ArrayList<Service>();

		Bundler bundlerExp = new Bundler();

		bundlerExp.setName("expN");
		bundlerExp.setVersion("expV");
		bundlerExp.setLocation("expL");
		bundlerExp.setStatus("expS");

		for (int i = 1; i <= 2; i++) {
			Service service = new Service();

			service.setNameService(String.valueOf(i) + "N");
			service.setBundlerProvide(bundlerExp);
			service.getListUsesBundles().addAll(listBundlerSys);

			listServiceSys.add(service);

			if (i == 2) {
				service = new Service();

				service.setNameService(String.valueOf(i) + "N");
				service.setBundlerProvide(bundlerExp);
				service.getListUsesBundles().addAll(listBundlerDB);

				listServiceDB.add(service);
			}

		}

		//test compareToServiceInformationBundlerConnected
		
		List<Service> listServiceResult = new ArrayList<Service>();

		// retorna os serviços que tem bundler existentes na lista um e não na
		// lista 2
//		listServiceResult.addAll(compareToServiceInformationBundlerConnected(listServiceSys, listServiceDB));
//
//		if (!listServiceResult.isEmpty()) {
//			for (Service service : listServiceResult) {
//				System.out.println("Name: " + service.getNameService());
//				System.out.println("BundlerProvide: " + service.getBundlerProvide().getName());
//				System.out.println("--------------");
//				for (Bundler bdl : service.getListUsesBundles()) {
//					System.out.println("NameSer: " + bdl.getName());
//					System.out.println("StateSer: " + bdl.getState());
//					System.out.println("VersionSer: " + bdl.getVersion());
//					System.out.println("LocationSer: " + bdl.getLocation() + "\n");
//				}
//				System.out.println("--------------");
//			}
//
//		} else {
//			System.out.println("Not altered in lists.");
//		}
		
		//test compareToServiceInformationBundlerDisconnected
		listServiceResult.addAll(compareToServiceInformationBundlerDisconnected(listServiceDB, listServiceSys));

		if (!listServiceResult.isEmpty()) {
			for (Service service : listServiceResult) {
				System.out.println("Name: " + service.getNameService());
				System.out.println("BundlerProvide: " + service.getBundlerProvide().getName());
				System.out.println("--------------");
				for (Bundler bdl : service.getListUsesBundles()) {
					System.out.println("NameSer: " + bdl.getName());
					System.out.println("StateSer: " + bdl.getStatus());
					System.out.println("VersionSer: " + bdl.getVersion());
					System.out.println("LocationSer: " + bdl.getLocation() + "\n");
				}
				System.out.println("--------------");
			}

		} else {
			System.out.println("Not altered in lists.");
		}
		
		infoServiceAlteredDisconnectedBundlerUse(listServiceResult);
		
		for (Service service : listServiceDB) {
			System.out.println("Name: " + service.getNameService());
			System.out.println("BundlerProvide: " + service.getBundlerProvide().getName());
			System.out.println("--------------");
			for (Bundler bdl : service.getListUsesBundles()) {
				System.out.println("NameSer: " + bdl.getName());
				System.out.println("StateSer: " + bdl.getStatus());
				System.out.println("VersionSer: " + bdl.getVersion());
				System.out.println("LocationSer: " + bdl.getLocation() + "\n");
			}
			System.out.println("--------------");
		}
		
		
		

		System.out.println("End processing...");
	}

	/**
	 * Returns services that have new bundles. Receiving as input a system list
	 * and the local list that has the persistent information from the last
	 * update.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param s1
	 *            List<Service>, s2 List<Service>
	 * @return List<Service> - Returns services that have new bundles
	 */
	public static List<Service> compareToServiceInformationBundlerConnected(List<Service> s1, List<Service> s2) {

		List<Service> result = new ArrayList<Service>();

		for (Service service1 : s1) {
			for (Service service2 : s2) {
				if ((service1.getNameService().equals(service2.getNameService()))
						&& (service2.getBundlerProvide().equals(service2.getBundlerProvide()))) {

					List<Bundler> listTempService1 = new ArrayList<Bundler>();
					listTempService1.addAll(service1.getListUsesBundles());

					List<Bundler> listTempService2 = new ArrayList<Bundler>();
					listTempService2.addAll(service2.getListUsesBundles());

					/*
					 * Returns elements of the first list that does not exist in
					 * the second
					 */
					List<Bundler> listBundlerConnected = compareToServiceInformationCompareBundlers(listTempService1,
							listTempService2);

					if (listBundlerConnected != null && !listBundlerConnected.isEmpty()) {
						Service newService = new Service();

						newService.setNameService(service1.getNameService());

						Bundler newBundler = new Bundler();
						newBundler.setName(service1.getBundlerProvide().getName());
						newBundler.setVersion(service1.getBundlerProvide().getVersion());
						newBundler.setLocation(service1.getBundlerProvide().getLocation());
						newBundler.setStatus(service1.getBundlerProvide().getStatus());

						newService.setBundlerProvide(newBundler);

						newService.getListUsesBundles().addAll(listBundlerConnected);

						result.add(newService);
					}
				}
			}
		}

		return result;

	}

	/**
	 * The method that receives the list as persistent with the last update and
	 * the list with the system information at that time.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param s1
	 *            List<Service>, s2 List<Service>
	 * @return List<Service> - Returns services that have excluded bundles
	 */
	public static List<Service> compareToServiceInformationBundlerDisconnected(List<Service> s1, List<Service> s2) {

		List<Service> result = new ArrayList<Service>();

		for (Service service1 : s1) {
			for (Service service2 : s2) {
				if ((service1.getNameService().equals(service2.getNameService()))
						&& (service2.getBundlerProvide().equals(service2.getBundlerProvide()))) {

					List<Bundler> listTempService1 = new ArrayList<Bundler>();
					listTempService1.addAll(service1.getListUsesBundles());

					List<Bundler> listTempService2 = new ArrayList<Bundler>();
					listTempService2.addAll(service2.getListUsesBundles());

					List<Bundler> listBundlerConnected = compareToServiceInformationCompareBundlers(listTempService1,
							listTempService2);

					if (!(listBundlerConnected.isEmpty() || listBundlerConnected == null)) {
						Service serviceTemp = new Service();

						serviceTemp.setNameService(service1.getNameService());
						serviceTemp.setBundlerProvide(service1.getBundlerProvide());
						serviceTemp.getListUsesBundles().addAll(listBundlerConnected);

						result.add(serviceTemp);
					}
				}
			}
		}

		return result;

	}

	/**
	 * Method that compares two lists of bundles and returns the bundles that
	 * exist in the first list and do not exist in the second.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param l1
	 *            List<Bundler>, l2 List<Bundler>
	 * @return List<Bundler> - Returns elements of the first list that does not
	 *         exist in the second
	 */
	public static List<Bundler> compareToServiceInformationCompareBundlers(List<Bundler> l1, List<Bundler> l2) {

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
	
	/**
	 * Method responsible for returning services that exist in list1 and does
	 * not exist in list2
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param s1
	 *            List<Service>, s2 List<Service>
	 * @return List<Service> -Returns the services that exist in l1 and do not
	 *         exist in l2
	 */
	public List<Service> compareToServices(List<Service> s1, List<Service> s2) {

		List<Service> result = new ArrayList<Service>();
		boolean exist;

		for (Service service1 : s1) {
			exist = false;
			for (Service service2 : s2) {
				if ((service1.getNameService().equals(service2.getNameService()))
						&& (service1.getBundlerProvide().equals(service2.getBundlerProvide()))) {
					exist = true;
					break;
				}
			}
			if (exist == false) {
				result.add(service1);
			}
		}

		return result;
	}
	
	/**
	 * A method that receives a list of services that have new bundles, adds
	 * those bundles to the appropriate services in the local list, and sends
	 * the list of services with new bundles to the server to update the
	 * database.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param listServiceBundlesConnected
	 *            List<Service>
	 */
	public static void infoServiceAlteredConnectedBundlerUse(List<Service> listServiceBundlesConnected) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		Gateway gatewaySend = new Gateway();

		/* update information locale */
		for (Service serviceAlter : listServiceBundlesConnected) {
			for (Service service : listServiceDB) {
				if (serviceAlter.getNameService().equals(service.getNameService())
						&& serviceAlter.getBundlerProvide().equals(service.getBundlerProvide())) {
					// --service.setNameService(serviceAlter.getNameService());

					Bundler newBundler = new Bundler();

					// --newBundler.setName(serviceAlter.getBundlerProvide().getName());
					// --newBundler.setVersion(serviceAlter.getBundlerProvide().getVersion());
					// --newBundler.setLocation(serviceAlter.getBundlerProvide().getLocation());
					// --newBundler.setState(serviceAlter.getBundlerProvide().getState());

					// --service.setBundlerProvide(newBundler);

					List<Bundler> listBundlerTemp = new ArrayList<Bundler>();

					for (Bundler bg : serviceAlter.getListUsesBundles()) {
						newBundler = new Bundler();

						newBundler.setName(bg.getName());
						newBundler.setVersion(bg.getVersion());
						newBundler.setLocation(bg.getLocation());
						newBundler.setStatus(bg.getStatus());

						listBundlerTemp.add(newBundler);
					}

					/* update */
					service.getListUsesBundles().addAll(listBundlerTemp);
					break;
				}
			}
		}

		
	}

	/**
	 * Method that receives a list of services that have bundles removed,
	 * removes these bundles from the appropriate services in the local list,
	 * and sends the list of services with removed bundles to the server to
	 * update the database.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param listServiceBundlesDisconnected
	 *            List<Service>
	 */
	public static void infoServiceAlteredDisconnectedBundlerUse(List<Service> listServiceBundlesDisconnected) {

		GatewayInfo gatewayInfo = new GatewayInfo();
		Gateway gatewaySend = new Gateway();

		/* update information locale */
		for (Service serviceAlter : listServiceBundlesDisconnected) {
			for (Service service : listServiceDB) {
				if (serviceAlter.getNameService().equals(service.getNameService())
						&& serviceAlter.getBundlerProvide().equals(service.getBundlerProvide())) {
					System.out.println("%%%%%%%%%%Entrou para desconectar. 1");
					List<Bundler> listBundlerAux1 = new ArrayList<Bundler>();
					listBundlerAux1.addAll(serviceAlter.getListUsesBundles());

					List<Bundler> listBundlerAux2 = new ArrayList<Bundler>();
					listBundlerAux2.addAll(service.getListUsesBundles());

					for (Bundler bundlerDisconnected : listBundlerAux1) {
						for (Bundler bundlerToRemove : listBundlerAux2) {
							if (bundlerDisconnected.getName().equals(bundlerToRemove.getName())
									&& bundlerDisconnected.getVersion().equals(bundlerToRemove.getVersion())) {
								
								System.out.println("%%%%%%%%%%Entrou para desconectar. 2");
								service.getListUsesBundles().remove(bundlerToRemove);
								break;
							}
						}
					}
				}
			}
		}

		

	}

}
