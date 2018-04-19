package org.apache.servicemix.examples.cxf.test.ControlSendGatewayInformation;

import org.apache.servicemix.examples.cxf.model.Gateway;

public class ControlSendGatewayInformationTestMain {

	public static void main(String[] args) {
		Gateway gateway = new Gateway();

		ControlSendGatewayInformationTest controlSendGateway = new ControlSendGatewayInformationTest();

		for (int i = 0; i <= 12; i++) {
			System.out.println(">>>>>>>>>>>>>>>>>>" + i);
			if (i > 0)
				gateway.setDescription("SmartGatewayWaterMODIFY");
			else
				gateway.setDescription("SmartGatewayWater");

			if (i > 1)
				gateway.setFirmware("2.1.2MODIFY");
			else
				gateway.setFirmware("2.1.2");

			if (i > 2)
				gateway.setHostName("SmartGatewayMODIFY");
			else
				gateway.setHostName("SmartGateway");

			if (i > 3)
				gateway.setIp("192.168.1.1MODIFY");
			else
				gateway.setIp("192.168.1.1");

			if (i > 4)
				gateway.setLocation("-125,1235 -552,235MODIFY");
			else
				gateway.setLocation("-125,1235 -552,235");

			if (i > 5)
				gateway.setMac("A1-33-E4-9FMODIFY");
			else
				gateway.setMac("A1-33-E4-9F");

			if (i > 6)
				gateway.setManufacturer("SAMSUMGMODIFY");
			else
				gateway.setManufacturer("SAMSUMG");

			if (i > 7)
				gateway.setModel("S3MODIFY");
			else
				gateway.setModel("S3");

			if (i > 8)
				gateway.setStorage(1204253);
			else
				gateway.setStorage(1526335);

			controlSendGateway.compareInfoGateway(gateway);
		}

	}

}
