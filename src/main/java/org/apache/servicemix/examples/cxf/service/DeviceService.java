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
import org.apache.servicemix.examples.cxf.info.DeviceInfo;
import org.apache.servicemix.examples.cxf.model.Device;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.util.ConstantsUtil;

import com.google.gson.Gson;

/**
 * Class responsible for sending information about the devices to the server
 * 
 * 
 * @author Nilson Rodrigues Sousa
 */
@Path("/deviceservice/")
public class DeviceService {

	private List<Device> listDevice;
	private DeviceInfo deviceInfo;
	private String ip = ConstantsUtil.IP;

	public DeviceService() {
		System.out.println("Device monitoring started.");
	}

	/**
	 * Method that returns the information all device
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<device> - Returns list device information
	 */
	@GET
	@Path("/gateway/gt/listdevice")
	@Produces("application/json")
	public List<Device> getDevice() {

		listDevice = new ArrayList<Device>();

		deviceInfo = new DeviceInfo();

		listDevice.addAll(deviceInfo.getListDevice());

		return listDevice;

	}

	/**
	 * Method responsible for requesting the sending of the information of new
	 * connected device for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListDeviceConnected(Gateway gatewaySend) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending DeviceConnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationdevice/connecteddevice", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting the device list for registration: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of the information of
	 * disconnected device for the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendListDeviceDisconnected(Gateway gatewaySend) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Sending DeviceDisconnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationdevice/disconnecteddevice", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in device list submission for disconnection: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of changed device
	 * information with new sensors or actuators connected to the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendDeviceInformationAlteredConnected(Gateway gatewaySend) {
		System.out.println(">>>>Sending DeviceInformationAlteredConnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationdevice/alterdeviceinformation/connected", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting device information altered: " + e);
		}
	}

	/**
	 * Method responsible for requesting the sending of changed device
	 * information with new sensors or actuators disconnected to the server
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param gatewaySend
	 *            Gateway - Information to be sent
	 */
	public void sendDeviceInformationAlteredDisconnected(Gateway gatewaySend) {
		System.out.println(">>>>Sending DeviceInformationAlteredDisconnected");

		try {
			this.sendData("http://" + ip + "/cxf/informationdevice/alterdeviceinformation/disconnected", gatewaySend);
		} catch (Exception e) {
			System.out.println("Error in submitting device information altered: " + e);
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
