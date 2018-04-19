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
import org.apache.servicemix.examples.cxf.info.ActuatorInfo;
import org.apache.servicemix.examples.cxf.model.Actuator;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.util.ConstantsUtil;

import com.google.gson.Gson;

/**
 * Class responsible for sending information about the actuadors to the server
 * NOT USED IN CURRENT IMPLEMENTATION - FUTURE USE
 * 
 * @author Nilson Rodrigues Sousa
 */
@Path("/actuadorservice/")
public class ActuatorService {

	private List<Actuator> listActuator;
	private ActuatorInfo actuatorInfo;
	private String ip = ConstantsUtil.IP;

	public ActuatorService() {
		System.out.println("Sensor monitoring started.");
	}

	/**
	 * Method that returns the information all actuator
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<actuator> - Returns list actuators information
	 */
	@GET
	@Path("/gateway/gt/listactuator")
	@Produces("application/json")
	public List<Actuator> getActuator() {

		listActuator = new ArrayList<Actuator>();

		actuatorInfo = new ActuatorInfo();

		listActuator.addAll(actuatorInfo.getListActuator());

		return listActuator;

	}

	/**
	 * Method responsible for requesting the sending of the information of new
	 * connected actuator for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListActuatorConnected(Gateway gatewaySend) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending ActuatorConnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationactuator/connectedactuator", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting the actuator list for registration: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * disconnected actuator for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListActuatorDisconnected(Gateway gatewaySend) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending ActuatorDisconnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationactuator/disconnectedactuator", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in actuator list submission for disconnection: " + e);
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
