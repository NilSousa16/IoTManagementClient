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
import org.apache.servicemix.examples.cxf.info.BundlerInfo;
import org.apache.servicemix.examples.cxf.model.Bundler;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.util.ConstantsUtil;

import com.google.gson.Gson;

/**
 * Class responsible for sending information about the bundles to the server
 *
 * @author Nilson Rodrigues Sousa
 */
@Path("/bundlerservice/")
public class BundlerService {

	private List<Bundler> listBundler;
	private BundlerInfo bundlerInfo;
	private String ip = ConstantsUtil.IP;

	public BundlerService() {
		System.out.println("Bundler monitoring started.");
	}

	/**
	 * Method that returns the existing bundle list
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param List<Service> - Returns list bundles
	 */
	@GET
	@Path("/gateway/gt/listbundler")
	@Produces("application/json")
	public List<Bundler> getListBundler() {

		listBundler = new ArrayList<Bundler>();

		bundlerInfo = new BundlerInfo();

		listBundler.addAll(bundlerInfo.getListBundler());

		return listBundler;
	}

	/**
	 * Method responsible for requesting the sending of the information of new
	 * connected bundles for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListBundlerConnected(Gateway gatewaySend) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending BundlerConnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationbundler/connectedbundler", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting the bundler list for registration: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * disconnected bundles for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListBundlerDisconnected(Gateway gatewaySend) {
		// ALTER - remove unnecessary information from the message
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending BundlerDisconnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationbundler/disconnectedbundler", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in bundler list submission for disconnection: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * altered bundles for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListBundlerAltered(Gateway gatewaySend) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending BundlerAltered");

		try {
			this.sendData("http://" + ip + "/cxf/informationbundler/alteredbundler", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in bundler list submission for change: " + e);
		}
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
