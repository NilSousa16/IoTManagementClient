package org.apache.servicemix.examples.cxf.intervention;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.servicemix.examples.cxf.model.Bundler;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;

import com.google.gson.Gson;

/**
 * Class responsible for performing the reinstallation of the settings retrieved
 * from the server.
 *
 * @author Nilson Rodrigues Sousa
 */
public class Recover {

	private BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

	/**
	 * Responsible for reinstalling recovered gateway services.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param value
	 *            String
	 * @throws Exception
	 */
	@POST
	@Path("/restoreconfiguration")
	@Produces("application/json")
	public void restoreConfiguration(String value) throws Exception {
		new Thread() {
			public void run() {
				String dateBeginRecover;
				String dateStartRecoverGateway;
				String dateFinalRecover;

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSSS");
				Date hora = Calendar.getInstance().getTime(); // Ou qualquer
																// outra forma
																// que tem
				dateStartRecoverGateway = sdf.format(hora);

				Gson gson = new Gson();
				Gateway gatewayCommunication = gson.fromJson(value, Gateway.class);
				dateBeginRecover = gatewayCommunication.getDescription();

				System.out.println(">>>>> DateBeginRecover: " + dateBeginRecover);
				System.out.println(">>>>> DateStartRecoverGateway: " + dateStartRecoverGateway);

				System.out.println("Recuperação em andamento.....");

				/* Allow OSGi R3 bundles */
				boolean allowR3 = false;

				/* install the bundles */
				boolean r3warned = false;

				List<Exception> exceptions = new ArrayList<>();

				List<Bundle> bundles = new ArrayList<>();

				List<URI> urls = new ArrayList<URI>();

				/* Used to list the status of bundles to be retrieved */
				String[] listStatus = new String[300];

				int cont = 0;

				/* Assembly of URIs for installation */
				for (Bundler bundler : gatewayCommunication.getListBundler()) {

					listStatus[cont] = bundler.getStatus();
					cont++;

					URI uri;
					try {
						uri = new URI(bundler.getLocation());
						urls.add(uri);
					} catch (URISyntaxException e) {
						System.out.println("Failed to mount the URI or the assignment in list.");
						e.printStackTrace();
					}

				}

				cont = 0;

				/* Installing bundles */
				for (URI url : urls) {
					try {
						Bundle bundle = bundleContext.installBundle(url.toString(), null);
						if (!"2".equals(bundle.getHeaders().get(Constants.BUNDLE_MANIFESTVERSION))) {
							if (allowR3) {
								if (!r3warned) {
									System.err.println("WARNING: use of OSGi r3 bundles is discouraged");
									r3warned = true;
								}
							} else {
								bundle.uninstall();
								throw new BundleException("OSGi R3 bundle not supported");
							}
						}
						bundles.add(bundle);

						/* Used to check if the bundle should be initialized */
						try {
							if (listStatus[cont].equals("Active")) {
								bundle.start();
							}
							cont++;
						} catch (Exception e) {
							exceptions.add(new Exception(
									"Unable to start bundle " + bundle.getLocation() + ": " + e.toString(), e));
						}
					} catch (Exception e) {
						exceptions.add(new Exception("Unable to install bundle " + url + ": " + e.toString(), e));
					}
				}

				/* print the installed bundles */
				if (bundles.size() == 1) {
					System.out.println("Bundle ID: " + bundles.get(0).getBundleId());
				} else {
					String msg = bundles.stream().map(b -> Long.toString(b.getBundleId()))
							.collect(Collectors.joining(", ", "Bundle IDs: ", ""));
					System.out.println(msg);
				}

				hora = Calendar.getInstance().getTime(); // Ou qualquer outra
															// forma que tem
				dateFinalRecover = sdf.format(hora);

				System.out.println(">>>>> DateFinalRecover: " + dateFinalRecover);

				System.out.println("Recuperação concluída.....");

				String report = "Inicio da Recuperação: " + dateBeginRecover + "\n";
				report += "Inicio da Recuperação Gateway: " + dateStartRecoverGateway + "\n";
				report += "Fim da recuperação: " + dateFinalRecover;
				
				System.out.println(">>>>> FileToStorage: " + report);

				/* create file log */
				Date data = new Date();
				String filePath = "/home/gateway/experimentos/experimento-" + data.toInstant().toString() + ".txt";
				try {
					FileWriter arq = new FileWriter(filePath);
					PrintWriter gravarArq = new PrintWriter(arq);

					gravarArq.printf("%s %n", report);
					arq.close();
					
				} catch (IOException e) {
					System.out.println("Unable to save file.");
					e.printStackTrace();
				}
			}
		}.start();
	}

	// public void restoreConfiguration(String value) throws Exception {
	// new Thread() {
	// public void run() {
	// System.out.println("Recuperação em andamento.....");
	//
	// /* Allow OSGi R3 bundles */
	// boolean allowR3 = false;
	//
	// /* install the bundles */
	// boolean r3warned = false;
	//
	// List<Exception> exceptions = new ArrayList<>();
	//
	// List<Bundle> bundles = new ArrayList<>();
	//
	// List<URI> urls = new ArrayList<URI>();
	//
	// /* Used to list the status of bundles to be retrieved */
	// String[] listStatus = new String[300];
	//
	// int cont = 0;
	//
	// Gson gson = new Gson();
	// Gateway gatewayCommunication = gson.fromJson(value, Gateway.class);
	//
	// /* Assembly of URIs for installation */
	// for (Bundler bundler : gatewayCommunication.getListBundler()) {
	//
	// listStatus[cont] = bundler.getStatus();
	// cont++;
	//
	// URI uri;
	// try {
	// uri = new URI(bundler.getLocation());
	// urls.add(uri);
	// } catch (URISyntaxException e) {
	// System.out.println("Failed to mount the URI or the assignment in list.");
	// e.printStackTrace();
	// }
	//
	// }
	//
	// cont = 0;
	//
	// /* Installing bundles */
	// for (URI url : urls) {
	// try {
	// Bundle bundle = bundleContext.installBundle(url.toString(), null);
	// if
	// (!"2".equals(bundle.getHeaders().get(Constants.BUNDLE_MANIFESTVERSION)))
	// {
	// if (allowR3) {
	// if (!r3warned) {
	// System.err.println("WARNING: use of OSGi r3 bundles is discouraged");
	// r3warned = true;
	// }
	// } else {
	// bundle.uninstall();
	// throw new BundleException("OSGi R3 bundle not supported");
	// }
	// }
	// bundles.add(bundle);
	//
	// /* Used to check if the bundle should be initialized */
	// try {
	// if (listStatus[cont].equals("Active")) {
	// bundle.start();
	// }
	// cont++;
	// } catch (Exception e) {
	// exceptions.add(new Exception(
	// "Unable to start bundle " + bundle.getLocation() + ": " + e.toString(),
	// e));
	// }
	// } catch (Exception e) {
	// exceptions.add(new Exception("Unable to install bundle " + url + ": " +
	// e.toString(), e));
	// }
	// }
	//
	// /* print the installed bundles */
	// if (bundles.size() == 1) {
	// System.out.println("Bundle ID: " + bundles.get(0).getBundleId());
	// } else {
	// String msg = bundles.stream().map(b -> Long.toString(b.getBundleId()))
	// .collect(Collectors.joining(", ", "Bundle IDs: ", ""));
	// System.out.println(msg);
	// }
	//
	// //realizar gravação
	//
	// System.out.println("Recuperação concluída.....");
	// }
	// }.start();
	// }
}
