package org.apache.servicemix.examples.cxf.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.servicemix.examples.cxf.info.GatewayStatusInfo;
import org.apache.servicemix.examples.cxf.model.GatewayStatus;
import org.apache.servicemix.examples.cxf.util.ConstantsUtil;

import com.google.gson.Gson;

/**
 * Class responsible for sending status information from the gateway to the
 * server
 * 
 * @author Nilson Rodrigues Sousa
 */
@Path("/gatewaystatusservice/")
public class GatewayStatusService {
	private GatewayStatus gatewayStatus;
	private GatewayStatusInfo gatewayStatusInfo;
	private String ip = ConstantsUtil.IP;

	public GatewayStatusService() {
		System.out.println("GatewayStatus monitoring started.");
	}

	/**
	 * Method that returns the information gateway status
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return GatewayStatus - Returns gateway status information
	 */
	@GET
	@Path("/gateway/gt/")
	@Produces("application/json")
	public GatewayStatus getGateway() {
		gatewayStatus = new GatewayStatus();

		gatewayStatusInfo = new GatewayStatusInfo();

		gatewayStatus.setBaterryLevel(gatewayStatusInfo.getBaterryLevel());
		gatewayStatus.setTotalMemory(gatewayStatusInfo.getTotalMemory());
		gatewayStatus.setUsedMemory(gatewayStatusInfo.getUsedMemory());
		gatewayStatus.setFreeMemory(gatewayStatusInfo.getFreeMemory());
		gatewayStatus.setUsedProcessor(gatewayStatusInfo.getUsedProcessor());
		gatewayStatus.setFreeProcessor(gatewayStatusInfo.getFreeProcessor());
		gatewayStatus.setDate(gatewayStatusInfo.getDate());
		gatewayStatus.setMac(gatewayStatusInfo.getMac());

		return gatewayStatus;
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * status gateway for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewayStatusSend
	 *            Gateway - Information to be sent
	 */
	public void sendGatewayStatus(GatewayStatus gatewayStatusSend) {
		System.out.println("Sending information gatewayStatusSend");

		try {
			this.sendData("http://" + ip + "/cxf/informationgatewaystatus/addgatewaystatus", gatewayStatusSend);
		} catch (Exception e) {
			System.out.println("Error in submitting gateway status: " + e);
		}
	}

	/**
	 * Method responsible for assembling the message and sending it to the
	 * server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param url String - URL to be sent
	 * @param gateway Gateway - Content for message assembly
	 * 
	 */
	public void sendData(String url, GatewayStatus gatewayStatusSend) throws Exception {
		try {
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(url);

			Gson gson = new Gson();

			String jsonInString = gson.toJson(gatewayStatusSend);

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
