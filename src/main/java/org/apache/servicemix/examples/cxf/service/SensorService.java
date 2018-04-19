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
import org.apache.servicemix.examples.cxf.info.SensorInfo;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.model.Sensor;
import org.apache.servicemix.examples.cxf.util.ConstantsUtil;

import com.google.gson.Gson;

/**
 * Class responsible for sending information about the sensors to the server
 * NOT USED IN CURRENT IMPLEMENTATION - FUTURE USE
 * 
 * @author Nilson Rodrigues Sousa
 */
@Path("/sensorservice/")
public class SensorService {
	
	private List<Sensor> listSensor;
	private SensorInfo sensorInfo;
	private String ip = ConstantsUtil.IP;

	public SensorService() {
		System.out.println("Sensor monitoring started.");
	}

	/**
	 * Method that returns the information all sensor
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Sensor> - Returns list sensors information
	 */
	@GET
	@Path("/gateway/gt/listSensor")
	@Produces("application/json")
	public List<Sensor> getSensor() {
		
		listSensor = new ArrayList<Sensor>();

		sensorInfo = new SensorInfo();

		listSensor.addAll(sensorInfo.getListSensor());

		return listSensor;

	}

	/**
	 * Method responsible for requesting the sending of the information of new
	 * connected sensor for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListBundlerConnected(Gateway gatewaySend) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending SensorConnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationsensor/connectedsensor", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting the sensor list for registration: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * disconnected sensor for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListSensorDisconnected(Gateway gatewaySend) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending SensorDisconnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationsensor/disconnectedsensor", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in sensor list submission for disconnection: " + e);
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
