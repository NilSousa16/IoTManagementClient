package org.apache.servicemix.examples.cxf.test.ControlSendBundlerInformation;

import java.util.ArrayList;
import java.util.List;

import org.apache.servicemix.examples.cxf.model.Bundler;

public class ControlSEndBundlerInformationTestMain {

	public static void main(String[] args) {

		ControlSendBundlerInformationTest controlSend = new ControlSendBundlerInformationTest();

		List<Bundler> listBundler = new ArrayList<Bundler>();

		Bundler bundler;

		// >>>>>>>>>>>>>>>>>TEST ONE - ADD 6 BUNDLERS

		System.out.println("\n>>>>>>>>>>>>>>>>>TEST ONE - ADD 6 BUNDLERS");
		
		for (int i = 1; i <= 6; i++) {
			bundler = new Bundler();

			bundler.setName("BundlerName0" + i);
			bundler.setStatus("BundlerState0" + i);
			bundler.setVersion("BundlerVersion0" + i);
			bundler.setLocation("BundlerLocation0" + i);

			listBundler.add(bundler);

		}

		controlSend.compareInfoBundler(listBundler);


		for (Bundler b : controlSend.getListBundlers()) {
			System.out.println("############################");
			System.out.println("BundlerName: " + b.getName());
			System.out.println("BundlerLocation: " + b.getLocation());
			System.out.println("BundlerState: " + b.getStatus());
			System.out.println("BundlerVersion: " + b.getVersion());

		}

		System.out.println(">>>>>>>>>>>>>>>>>END THE TEST ONE - ADD 6 BUNDLERS\n");

		// >>>>>>>>>>>>>>>>>TEST TWO - REMOVE 2 BUNDLERS - B3, B5
		
		System.out.println("\n>>>>>>>>>>>>>>>>>TEST TWO - REMOVE 2 BUNDLERS - B3, B5");

		int cont = 1;
		List<Bundler> lTemp = new ArrayList<Bundler>();
		for (Bundler bundlerRemove : listBundler) {
			if (cont == 3 || cont == 5) {
				lTemp.add(bundlerRemove);
			}
			cont++;
		}
		listBundler.removeAll(lTemp);

		controlSend.compareInfoBundler(listBundler);

		for (Bundler b : controlSend.getListBundlers()) {
			System.out.println("############################");
			System.out.println("BundlerName: " + b.getName());
			System.out.println("BundlerLocation: " + b.getLocation());
			System.out.println("BundlerState: " + b.getStatus());
			System.out.println("BundlerVersion: " + b.getVersion());

		}

		System.out.println(">>>>>>>>>>>>>>>>>END THE TEST TWO - REMOVE 2 BUNDLERS - B3, B5\n");
		
		// >>>>>>>>>>>>>>>>>TEST THREE - ALTER 2 BUNDLERS - B1, B4

		System.out.println("\n>>>>>>>>>>>>>>>>>TEST THREE - ALTER 2 BUNDLERS - B1, B4");
		
		for (Bundler bundlerAlter : listBundler) {
			if (bundlerAlter.getName().equals("BundlerName01")) {
				bundlerAlter.setStatus("BundlerState0X1");
				bundlerAlter.setLocation("BundlerLocation0X1");
			}
			
			if(bundlerAlter.getName().equals("BundlerName04")) {
				bundlerAlter.setStatus("BundlerState0X4");
				bundlerAlter.setLocation("BundlerLocation0X4");
			}
		}

		controlSend.compareInfoBundler(listBundler);
		
		for (Bundler b : controlSend.getListBundlers()) {
			System.out.println("############################");
			System.out.println("BundlerName: " + b.getName());
			System.out.println("BundlerLocation: " + b.getLocation());
			System.out.println("BundlerState: " + b.getStatus());
			System.out.println("BundlerVersion: " + b.getVersion());

		}

		System.out.println(">>>>>>>>>>>>>>>>>END THE TEST THREE - ALTER 2 BUNDLERS - B1, B4\n");
		
		// >>>>>>>>>>>>>>>>>TEST FOUR - ADD 1 BUNDLER - B9 / REMOVE 2 BUNDLERS - B2, B4 / ALTER 1 BUNDLERS - B6
		
		System.out.println("TEST FOUR - ADD 1 BUNDLER - B9 / REMOVE 2 BUNDLERS - B2, B4 / ALTER 1 BUNDLERS - B6\n");
		
		// >>>>>>>>ADD B9
		bundler = new Bundler();
		
		bundler.setName("BundlerName09");
		bundler.setStatus("BundlerState09");
		bundler.setVersion("BundlerVersion09");
		bundler.setLocation("BundlerLocation09");
		
		listBundler.add(bundler);
		// >>>>>>>>ADD B9
		
		// >>>>>>>>REMOVE B2, B4
		lTemp = new ArrayList<Bundler>();
		for(Bundler bundlerRemove : listBundler) {
			if (bundlerRemove.getName().equals("BundlerName02")) {
				lTemp.add(bundlerRemove);
			}
			
			if (bundlerRemove.getName().equals("BundlerName04")) {
				lTemp.add(bundlerRemove);
			}
		}
		listBundler.removeAll(lTemp);
		// >>>>>>>>REMOVE B2, B4		
		
		// >>>>>>>>ALTER B6	
		for(Bundler bundlerAlter : listBundler) {
			if (bundlerAlter.getName().equals("BundlerName06")) {
				bundlerAlter.setStatus("BundlerState0X6");
				bundlerAlter.setLocation("BundlerLocation0X6");
			}
		}
		//>>>>>>>>ALTER B6
		
		controlSend.compareInfoBundler(listBundler);
		
		System.out.println("END THE TEST FOUR - ADD 1 BUNDLER - B9 / REMOVE 2 BUNDLERS - B2, B4 / ALTER 1 BUNDLERS - B6\n");
		
		System.out.println("\n>>>>>>>>>>>>>>>>>FINAL RESULT");

		for (Bundler b : controlSend.getListBundlers()) {
			System.out.println("############################");
			System.out.println("BundlerName: " + b.getName());
			System.out.println("BundlerLocation: " + b.getLocation());
			System.out.println("BundlerState: " + b.getStatus());
			System.out.println("BundlerVersion: " + b.getVersion());

		}
		
		// >>>>>>>>>>>>>>>>>TEST FOUR - ADD 1 BUNDLER - B9 / REMOVE 2 BUNDLERS - B2, B4 / ALTER 1 BUNDLERS - B6
		
	}
}
