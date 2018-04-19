package org.apache.servicemix.examples.cxf.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.servicemix.examples.cxf.info.GatewayInfo;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.util.ConstantsUtil;

import com.google.gson.Gson;

/**
 * Class responsible for sending information about the gateway to the server
 * 
 * @author Nilson Rodrigues Sousa
 */
//@Path("/gatewayservice/")
public class GatewayService {
	private Gateway gateway;
	private GatewayInfo gatewayInfo;
	private String ip = ConstantsUtil.IP;

	public GatewayService() {
		System.out.println("Gateway monitoring started.");
	}

	/**
	 * Method that returns the information gateway
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return Gateway - Returns gateway information
	 */
	@GET
	@Path("/gateway/gt/")
	@Produces("application/json")
	public Gateway getGateway() {
		gateway = new Gateway();

		gatewayInfo = new GatewayInfo();

		gateway.setDescription(gatewayInfo.getDescription());
		gateway.setModel(gatewayInfo.getModel());
		gateway.setManufacturer(gatewayInfo.getManufacturer());
		gateway.setFirmware(gatewayInfo.getFirmware());
		gateway.setStorage(gatewayInfo.getStorage());
		gateway.setLastUpdate(gatewayInfo.getLastUpdate());
		gateway.setMac(gatewayInfo.getMac());
		gateway.setIp(gatewayInfo.getIp());
		gateway.setHostName(gatewayInfo.getHostName());
		gateway.setLocation(gatewayInfo.getLocation());

		return gateway;
	}

	/**
	 * Method that returns the information address mac of gateway
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return Gateway - Returns gateway information
	 */
	@GET
	@Path("/gateway/mac/")
	@Produces("application/json")
	public Gateway getMacGateway() {
		gateway = new Gateway();

		gatewayInfo = new GatewayInfo();

		gateway.setMac(gatewayInfo.getMac());

		return gateway;
	}

	/**
	 * Method responsible for requesting the sending of the information of new
	 * connected gateway for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 * @return boolean - Returns true if the information was received by the
	 *         server
	 */
	public boolean sendGatewayAdd(Gateway gatewaySend) {
		System.out.println(">>>>GS - Sending sendGatewayInformationAdd");
		System.out.println(">>>>GS - Valor do Flag: " + gatewaySend.getFlag());
		System.out.println("GS - IP: " + gatewaySend.getIp());
		System.out.println("GS - MAC: " + gatewaySend.getMac());
		
		try {
			if (this.sendDataRegister("http://" + ip + "/cxf/informationgateway/addgateway", gatewaySend)) {
				System.out.println(">>>>GS - Ending sendGatewayInformationAdd");
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error in submitting gateway information add...");
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * altered gateway for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendGatewayInformationAltered(Gateway gatewaySend) {
		System.out.println(">>>>GS - Sending sendGatewayInformationAltered");
		System.out.println(">>>>GS - Valor do flag: " + gatewaySend.getFlag());
		try {
			this.sendDataAltered("http://" + ip + "/cxf/informationgateway/altergatewayinformation", gatewaySend);
			System.out.println(">>>>GS - Ending sendGatewayInformationAltered");
		} catch (Exception e) {
			System.out.println("Error in submitting gateway information altered...");
			e.printStackTrace();
		}
	}

	/**
	 * Method responsible for assembling the message for registration gateway
	 * and sending it to the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param url
	 *            String - URL to be sent
	 * @param gateway
	 *            Gateway - Content for message assembly
	 * @return boolean - Returns true if the message was received by the server
	 */
	public boolean sendDataRegister(String url, Gateway gateway) throws Exception {

		String output = null;
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
//			output = mPost.getResponseBodyAsString();
			mPost.releaseConnection();
//			if (output.equals("true")) {
//				return true;
//			}
		} catch (Exception e) {
			//throw new Exception("Exception in adding bucket : " + e);
			System.out.println("Exception in adding bucket...");
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Method responsible for assembling the message to change information and
	 * sending it to the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param url
	 *            String - URL to be sent
	 * @param gateway
	 *            Gateway - Content for message assembly
	 * 
	 */
	public void sendDataAltered(String url, Gateway gateway) throws Exception {
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
//			throw new Exception("Exception in adding bucket : " + e);
			System.out.println("Exception in adding bucket...");
			e.printStackTrace();
		}
	}

	
}
