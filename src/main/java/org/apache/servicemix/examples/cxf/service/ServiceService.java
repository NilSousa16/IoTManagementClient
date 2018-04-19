package org.apache.servicemix.examples.cxf.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.servicemix.examples.cxf.info.ServiceInfo;
import org.apache.servicemix.examples.cxf.model.Bundler;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.model.Service;
import org.apache.servicemix.examples.cxf.util.ConstantsUtil;

import com.google.gson.Gson;

/**
 * Class responsible for sending information about the services to the server
 *
 * @author Nilson Rodrigues Sousa
 */
@Path("/serviceservice/")
public class ServiceService {

	private List<Service> listService;
	private ServiceInfo serviceInfo;
	private String ip = ConstantsUtil.IP;

	public ServiceService() {
		System.out.println("**************Service monitoring started.");
	}

	/**
	 * Method that returns the existing service list
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param List<Service>
	 *            - Returns list services
	 */
	@GET
	@Path("/service/gt/listservice")
	@Produces("application/json")
	public List<Service> getListService() {

		listService = new ArrayList<Service>();

		serviceInfo = new ServiceInfo();

		listService = serviceInfo.getListService();

		return listService;
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * disconnected services for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway -
	 */
	public void sendServiceDisconnected(Gateway gatewaySend) {
		System.out.println("\n****************Sending service information for disconnection.\n");
		// >>>>>>>>>>>>>>>>>>>>>>>VERIFICATION INFORMATION
		for (Service service : gatewaySend.getListService()) {
			System.out.println("Service: " + service.getNameService());
			System.out.println("BundlerProvided: " + service.getBundlerProvide().getName() + " Ver: "
					+ service.getBundlerProvide().getVersion());
			for(Bundler bundler : service.getListUsesBundles()) {
				System.out.println(">>>>>Bundler: " + bundler.getName() + " Ver: " + bundler.getVersion());
			}
		}

		// ATENCION: VERIFICATION OF CONTENT SENT IS NOT PERFORMED
		try {
			this.sendData("http://" + ip + "/cxf/informationservice/disconnectedservice", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting service disconnected: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * connected services for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway -
	 */
	public void sendServiceConnected(Gateway gatewaySend) {
		System.out.println("\n****************Sending service information for connection.\n");
		// >>>>>>>>>>>>>>>>>>>>>>>VERIFICATION INFORMATION
				for (Service service : gatewaySend.getListService()) {
					System.out.println("Service: " + service.getNameService());
					System.out.println("BundlerProvided: " + service.getBundlerProvide().getName() + " Ver: "
							+ service.getBundlerProvide().getVersion());
					for(Bundler bundler : service.getListUsesBundles()) {
						System.out.println(">>>>>Bundler: " + bundler.getName() + " Ver: " + bundler.getVersion());
					}
				}

		try {
			this.sendData("http://" + ip + "/cxf/informationservice/connectedservice", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting service connected: " + e);
		}

	}

	/**
	 * Method responsible for requesting the sending of the information of use
	 * bundles connected in services for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway -
	 */
	public void sendServiceAlteredConnectedBundlerUse(Gateway gatewaySend) {
		System.out.println("\n****************Sending sendServiceAlteredConnectedBundlerUse.\n");
		// >>>>>>>>>>>>>>>>>>>>>>>VERIFICATION INFORMATION
				for (Service service : gatewaySend.getListService()) {
					System.out.println("Service: " + service.getNameService());
					System.out.println("BundlerProvided: " + service.getBundlerProvide().getName() + " Ver: "
							+ service.getBundlerProvide().getVersion());
					for(Bundler bundler : service.getListUsesBundles()) {
						System.out.println(">>>>>Bundler: " + bundler.getName() + " Ver: " + bundler.getVersion());
					}
				}

		// ATENCION: VERIFICATION OF CONTENT SENT IS NOT PERFORMED
		try {
			this.sendData("http://" + ip + "/cxf/informationservice/alteredserviceconnectedbundleruse", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting service altered connected BundlerUse: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of the information of use
	 * bundles disconnected in services for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway -
	 */
	public void sendServiceAlteredDisconnectedBundlerUse(Gateway gatewaySend) {
		System.out.println("\n****************Sending sendServiceAlteredDisconnectedBundlerUse.\n");
		// >>>>>>>>>>>>>>>>>>>>>>>VERIFICATION INFORMATION
				for (Service service : gatewaySend.getListService()) {
					System.out.println("Service: " + service.getNameService());
					System.out.println("BundlerProvided: " + service.getBundlerProvide().getName() + " Ver: "
							+ service.getBundlerProvide().getVersion());
					for(Bundler bundler : service.getListUsesBundles()) {
						System.out.println(">>>>>Bundler: " + bundler.getName() + " Ver: " + bundler.getVersion());
					}
				}

		// ATENCION: VERIFICATION OF CONTENT SENT IS NOT PERFORMED
		try {
			this.sendData("http://" + ip + "/cxf/informationservice/alteredservicedisconnectedbundleruse", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting service altered disconnected BundlerUse: " + e);
		}
	}

	// METHOD NOT USED, IMPLEMENTED IN BUNDLERSERVICE
	public void sendServiceAlteredInformationAlteredBundlerUse(Gateway gatewaySend) {
		// System.out.println("\n>>>>>>>>>>>>>>>>>Sending
		// sendServiceAlteredInformationAlteredBundlerUse.\n");
		//
		//
		// try {
		// this.sendData("http://" + ip + "/cxf/informationservice/",
		// gatewaySend);
		// } catch (Exception e) {
		// System.out.println("Error in submitting gateway information: " + e);
		// }
	}

	/**
	 * Method responsible for assembling the message and sending it to the
	 * server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param url
	 *            String - URL to be sent
	 * @param gateway
	 *            Gateway - Content for message assembly
	 * 
	 */
	public void sendData(String url, Gateway gateway) throws Exception {
		try {
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(url);

			Gson gson = new Gson();

			String jsonInString = gson.toJson(gateway);

			mPost.setRequestEntity(new StringRequestEntity(jsonInString, null, null));

			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/x-www-form-urlencoded");
			mtHeader.setName("accept");
			mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);
			client.executeMethod(mPost);
			mPost.releaseConnection();
		} catch (Exception e) {
			throw new Exception("Exception in adding bucket : " + e);
		}
	}
}
