package org.apache.servicemix.examples.cxf.test.ControlSendServiceInformation;

public class ControlSendServiceInformationTestMain {

	public static void main(String[] args) {

		
		
		/*
		 * Service service;
		 * 
		 * ControlSendServiceInformationTest controlSendService = new
		 * ControlSendServiceInformationTest();
		 * 
		 * List<Service> listService = new ArrayList<Service>();
		 * 
		 * // >>>>>>>>>>>>>>>>>>>>>> Test of sending new services System.out.
		 * println(">>>>>>>>>>>>>>>>>>>>>> Test1 of sending new services######################"
		 * );
		 * 
		 * for (int i = 0; i <= 6; i++) {
		 * 
		 * service = new Service();
		 * 
		 * service.setNameService("Service0" + i);
		 * 
		 * Bundler bundlerProvide = new Bundler();
		 * bundlerProvide.setName("BundlerNameP" + i);
		 * bundlerProvide.setLocation("BundlerLocationP" + i);
		 * bundlerProvide.setState("true"); bundlerProvide.setVersion("x.x.1");
		 * 
		 * service.setBundlerProvide(bundlerProvide);
		 * 
		 * List<Bundler> listAux = new ArrayList<Bundler>(); for (int j = 0; j
		 * <= 3; j++) { Bundler bundlerUse = new Bundler();
		 * bundlerUse.setName("BundlerNameU" + i + j);
		 * bundlerUse.setLocation("BundlerLocationU" + i + j);
		 * bundlerUse.setState("true"); bundlerUse.setVersion("x.x." + i + j);
		 * listAux.add(bundlerUse); } service.setListUsesBundles(listAux);
		 * 
		 * listService.add(service);
		 * 
		 * }
		 * 
		 * controlSendService.compareInfoService(listService);
		 * 
		 * // >>>>>>>>>>>>>>>>>>>>>> New service addition test System.out.
		 * println(">>>>>>>>>>>>>>>>>>>>>> Test2 of new service addition - 2 services######################"
		 * );
		 * 
		 * service = new Service();
		 * 
		 * service.setNameService("Service0XYZ");
		 * 
		 * Bundler bundlerProvide = new Bundler();
		 * bundlerProvide.setName("BundlerNamePXYZ");
		 * bundlerProvide.setLocation("BundlerLocationPXYZ");
		 * bundlerProvide.setState("true");
		 * bundlerProvide.setVersion("12.12.12");
		 * 
		 * service.setBundlerProvide(bundlerProvide);
		 * 
		 * List<Bundler> listAux = new ArrayList<Bundler>(); for (int j = 0; j
		 * <= 3; j++) { Bundler bundlerUse = new Bundler();
		 * bundlerUse.setName("BundlerNameUXYZ" + 12 + j);
		 * bundlerUse.setLocation("BundlerLocationUXYZ" + 12 + j);
		 * bundlerUse.setState("true"); bundlerUse.setVersion("x.x." + 12 + j);
		 * listAux.add(bundlerUse); } service.setListUsesBundles(listAux);
		 * 
		 * listService.add(service);
		 * 
		 * service = new Service();
		 * 
		 * service.setNameService("Service0XYZAAA");
		 * 
		 * bundlerProvide = new Bundler();
		 * bundlerProvide.setName("BundlerNamePXYZAAA");
		 * bundlerProvide.setLocation("BundlerLocationPXYZAAA");
		 * bundlerProvide.setState("true");
		 * bundlerProvide.setVersion("12.12.12");
		 * 
		 * service.setBundlerProvide(bundlerProvide);
		 * 
		 * listAux = new ArrayList<Bundler>(); for (int j = 0; j <= 3; j++) {
		 * Bundler bundlerUse = new Bundler();
		 * bundlerUse.setName("BundlerNameUXYZAAA" + 12 + j);
		 * bundlerUse.setLocation("BundlerLocationUXYZAAA" + 12 + j);
		 * bundlerUse.setState("true"); bundlerUse.setVersion("x.x." + 12 + j);
		 * listAux.add(bundlerUse); } service.setListUsesBundles(listAux);
		 * 
		 * listService.add(service);
		 * 
		 * controlSendService.compareInfoService(listService);
		 * 
		 * // >>>>>>>>>>>>>>>>>>>>>> Test of deletion new services System.out.
		 * println(">>>>>>>>>>>>>>>>>>>>>> Test3 of deletion services - 2 service######################"
		 * );
		 * 
		 * int cont = 0; Service serv = new Service(); Service serv2 = new
		 * Service();
		 * 
		 * for (Service s : listService) { if (cont == 6) { serv = s; } if (cont
		 * == 3) { serv2 = s; } cont++; }
		 * 
		 * listService.remove(serv); listService.remove(serv2);
		 * 
		 * controlSendService.compareInfoService(listService);
		 * 
		 * // >>>>>>>>>>>>>>>>>>>>>> Test of modify services - remove 2 bundles
		 * System.out.
		 * println(">>>>>>>>>>>>>>>>>>>>>> Test4 of modify services - remove 2 bundles######################"
		 * );
		 * 
		 * int cont1 = 0;
		 * 
		 * for (Service s : listService) { if (cont1 == 2) { int t = 0; Bundler
		 * removeB = new Bundler(); Bundler removeC = new Bundler(); for
		 * (Bundler b : s.getListUsesBundles()) { if (t == 1) { removeB = b; }
		 * if (t == 0) { removeC = b; } t++; }
		 * s.getListUsesBundles().remove(removeB);
		 * s.getListUsesBundles().remove(removeC); } cont1++; }
		 * 
		 * controlSendService.compareInfoService(listService);
		 * 
		 * // >>>>>>>>>>>>>>>>>>>>>> New service addition test System.out.
		 * println(" >>>>>>>>>>>>>>>>>>>>>> Test5 of new service addition - 1 service######################"
		 * );
		 * 
		 * service = new Service();
		 * 
		 * service.setNameService("Service0XYZCASSIO");
		 * 
		 * bundlerProvide = new Bundler();
		 * bundlerProvide.setName("BundlerNamePXYZCASSIO");
		 * bundlerProvide.setLocation("BundlerLocationPXYZCASSIO");
		 * bundlerProvide.setState("true");
		 * bundlerProvide.setVersion("12.12.12");
		 * 
		 * service.setBundlerProvide(bundlerProvide);
		 * 
		 * listAux = new ArrayList<Bundler>(); for (int j = 0; j <= 3; j++) {
		 * Bundler bundlerUse = new Bundler();
		 * bundlerUse.setName("BundlerNameUXYZCASSIO" + 12 + j);
		 * bundlerUse.setLocation("BundlerLocationUXYZCASSIO" + 12 + j);
		 * bundlerUse.setState("true"); bundlerUse.setVersion("x.x." + 12 + j);
		 * listAux.add(bundlerUse); } service.setListUsesBundles(listAux);
		 * 
		 * listService.add(service);
		 * 
		 * controlSendService.compareInfoService(listService);
		 * 
		 * // >>>>>>>>>>>>>>>>>>>>>> Test of modify services - add 2 bundles
		 * System.out.
		 * println(">>>>>>>>>>>>>>>>>>>>>> Test6 of modify services - add 2 bundles######################"
		 * );
		 * 
		 * int cont2 = 0;
		 * 
		 * for (Service s : listService) { if (cont2 == 1) { Bundler addBundler
		 * = new Bundler();
		 * 
		 * addBundler.setName("BundlerNameWISER");
		 * addBundler.setVersion("WISERVERSION");
		 * addBundler.setLocation("UFBAWISER"); addBundler.setState("true");
		 * 
		 * s.getListUsesBundles().add(addBundler);
		 * 
		 * addBundler = new Bundler();
		 * 
		 * addBundler.setName("BundlerNameWISER1212");
		 * addBundler.setVersion("WISERVERSION1212");
		 * addBundler.setLocation("UFBAWISER1212"); addBundler.setState("true");
		 * 
		 * s.getListUsesBundles().add(addBundler); } cont2++; }
		 * 
		 * controlSendService.compareInfoService(listService);
		 * 
		 * // >>>>>>>>>>>>>>>>>>>>>> Test of modify information services -
		 * modify 1 bundles in 1 service System.out.
		 * println(">>>>>>>>>>>>>>>>>>>>>> Test7 of modify information services - modify 1 bundles in 1 service######################"
		 * );
		 * 
		 * int cont3 = 0;
		 * 
		 * for (Service s : listService) { if (cont3 == 1) { int t = 0; for
		 * (Bundler b : s.getListUsesBundles()) { if(t == 4) { //
		 * b.setName("WISERCASSIO"); b.setLocation("ZZZZZZZZ"); //
		 * b.setVersion("ZZZZZZZZ"); b.setState("ZZZZZZZZ"); } t++; } } cont3++;
		 * }
		 * 
		 * controlSendService.compareInfoService(listService);
		 * 
		 * 
		 * System.out.println("Verification Final"); // for (Service s :
		 * listService) { // System.out.println(
		 * "\n####################Verification-Final####################"); //
		 * System.out.println(">>>ServiceName: " + s.getNameService()); //
		 * System.out.println(">>>ServiceBundlerProvideName: " + //
		 * s.getBundlerProvide().getName()); //
		 * System.out.println(">>>ServiceBundlerProvideVersion: " + //
		 * s.getBundlerProvide().getVersion()); // // for (Bundler bundler :
		 * s.getListUsesBundles()) { //
		 * System.out.println("---------------------------"); //
		 * System.out.println(" BundlerName: " + bundler.getName()); //
		 * System.out.println(" BundlerVersion: " + bundler.getVersion()); //
		 * System.out.println(" BundlerLocation: " + bundler.getLocation()); //
		 * System.out.println(" BundlerState: " + bundler.getState()); // } // }
		 * 
		 * for (Service g : controlSendService.listServices) {
		 * System.out.println(
		 * "\n####################List-Persistence####################");
		 * System.out.println(">>>ServiceName: " + g.getNameService());
		 * System.out.println(">>>ServiceBundlerProvideName: " +
		 * g.getBundlerProvide().getName());
		 * System.out.println(">>>ServiceBundlerProvideVersion: " +
		 * g.getBundlerProvide().getVersion());
		 * 
		 * for (Bundler bundler : g.getListUsesBundles()) {
		 * System.out.println("---------------------------");
		 * System.out.println(" BundlerName: " + bundler.getName());
		 * System.out.println(" BundlerVersion: " + bundler.getVersion());
		 * System.out.println(" BundlerLocation: " + bundler.getLocation());
		 * System.out.println(" BundlerState: " + bundler.getState()); } }
		 */
	}

}
