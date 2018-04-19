package org.apache.servicemix.examples.cxf.test.ControlSendGatewayInformation;

import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.service.GatewayService;

public class ControlSendGatewayInformationTest {

	private static Gateway gateway;
	private GatewayService gatewayService;

	public ControlSendGatewayInformationTest() {
		gatewayService = new GatewayService();
	}

	// information about gateway
	public void compareInfoGateway(Gateway gatewayTest) {
		Gateway gatewaySend;

		Gateway gatewayCompare = new Gateway();

		if (gateway == null) {
			gateway = new Gateway();

			gateway.setDescription(gatewayTest.getDescription());
			gateway.setFirmware(gatewayTest.getFirmware());
			gateway.setHostName(gatewayTest.getHostName());
			gateway.setIp(gatewayTest.getIp());
			gateway.setLocation(gatewayTest.getLocation());
			gateway.setMac(gatewayTest.getMac());
			gateway.setManufacturer(gatewayTest.getManufacturer());
			gateway.setModel(gatewayTest.getModel());
			gateway.setStorage(gatewayTest.getStorage());

			gatewayService.sendGatewayAdd(gateway);
		} else {

			// returns the gateway information
			gatewayCompare.setDescription(gatewayTest.getDescription());
			gatewayCompare.setFirmware(gatewayTest.getFirmware());
			gatewayCompare.setHostName(gatewayTest.getHostName());
			gatewayCompare.setIp(gatewayTest.getIp());
			gatewayCompare.setLocation(gatewayTest.getLocation());
			gatewayCompare.setMac(gatewayTest.getMac());
			gatewayCompare.setManufacturer(gatewayTest.getManufacturer());
			gatewayCompare.setModel(gatewayTest.getModel());
			gatewayCompare.setStorage(gatewayTest.getStorage());

			// responsible for storing information to be sent
			gatewaySend = new Gateway();

			Boolean modify = false;

			if (!gateway.getDescription().equals(gatewayCompare.getDescription())) {
				gatewaySend.setDescription(gatewayCompare.getDescription());
				gateway.setDescription(gatewayCompare.getDescription());
				modify = true;
			}

			if (!gateway.getModel().equals(gatewayCompare.getModel())) {
				gatewaySend.setModel(gatewayCompare.getModel());
				gateway.setModel(gatewayCompare.getModel());
				modify = true;
			}

			if (!gateway.getManufacturer().equals(gatewayCompare.getManufacturer())) {
				gatewaySend.setManufacturer(gatewayCompare.getManufacturer());
				gateway.setManufacturer(gatewayCompare.getManufacturer());
				modify = true;
			}

			if (!gateway.getFirmware().equals(gatewayCompare.getFirmware())) {
				gatewaySend.setFirmware(gatewayCompare.getFirmware());
				gateway.setFirmware(gatewayCompare.getFirmware());
				modify = true;
			}

			if (!(gateway.getStorage() == gatewayCompare.getStorage())) {
				gatewaySend.setStorage(gatewayCompare.getStorage());
				gateway.setStorage(gatewayCompare.getStorage());
				modify = true;
			}

			if (!gateway.getMac().equals(gatewayCompare.getMac())) {
				gatewaySend.setMac(gatewayCompare.getMac());
				gateway.setMac(gatewayCompare.getMac());
				modify = true;
			}

			if (!gateway.getIp().equals(gatewayCompare.getIp())) {
				gatewaySend.setIp(gatewayCompare.getIp());
				gateway.setIp(gatewayCompare.getIp());
				modify = true;
			}

			if (!gateway.getHostName().equals(gatewayCompare.getHostName())) {
				gatewaySend.setHostName(gatewayCompare.getHostName());
				gateway.setHostName(gatewayCompare.getHostName());
				modify = true;
			}

			if (!gateway.getLocation().equals(gatewayCompare.getLocation())) {
				gatewaySend.setLocation(gatewayCompare.getLocation());
				gateway.setLocation(gatewayCompare.getLocation());
				modify = true;
			}

			// conditional to send information
			if (modify == true) {
				gatewaySend.setLastUpdate(gatewayCompare.getLastUpdate());
				gatewaySend.setMac(gatewayCompare.getMac());
				gatewayService.sendGatewayInformationAltered(gatewaySend);
			}
		}
	}

}
