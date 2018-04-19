package org.apache.servicemix.examples.cxf.test.ControlSendGatewayStatusInformation;

import org.apache.servicemix.examples.cxf.model.GatewayStatus;
import org.apache.servicemix.examples.cxf.service.GatewayStatusService;

public class ControlSendGatewayStatusInformationTest {

	private static GatewayStatus gatewayStatus;
	private GatewayStatusService gatewayStatusService;

	public ControlSendGatewayStatusInformationTest() {
		gatewayStatusService = new GatewayStatusService();
	}

	// information about gateway status
	public void compareInfoGatewayStatus() {

		GatewayStatus gatewaySend;

		GatewayStatus gatewayStatusCompare = new GatewayStatus();

		if (gatewayStatus == null) {
			gatewaySend = gatewayStatusService.getGateway();
			gatewayStatusService.sendGatewayStatus(gatewayStatus);
		} else {
			// returns the gateway status information
			gatewayStatusCompare = gatewayStatusService.getGateway();
			// responsible for storing information to be sent
			gatewaySend = new GatewayStatus();

			Boolean modify = false;
			
			if (!(gatewayStatus.getBaterryLevel() == gatewayStatusCompare.getBaterryLevel())) {
				gatewaySend.setBaterryLevel(gatewayStatusCompare.getBaterryLevel());
				gatewayStatus.setBaterryLevel(gatewayStatusCompare.getBaterryLevel());
				modify = true;
			}

			if (!(gatewayStatus.getTotalMemory() == gatewayStatusCompare.getTotalMemory())) {
				gatewaySend.setTotalMemory(gatewayStatusCompare.getTotalMemory());
				gatewayStatus.setTotalMemory(gatewayStatusCompare.getTotalMemory());
				modify = true;
			}

			if (!(gatewayStatus.getUsedMemory() == gatewayStatusCompare.getUsedMemory())) {
				gatewaySend.setUsedMemory(gatewayStatusCompare.getUsedMemory());
				gatewayStatus.setUsedMemory(gatewayStatusCompare.getUsedMemory());
				modify = true;
			}

			if (!(gatewayStatus.getFreeMemory() == gatewayStatusCompare.getFreeMemory())) {
				gatewaySend.setFreeMemory(gatewayStatusCompare.getFreeMemory());
				gatewayStatus.setFreeMemory(gatewayStatusCompare.getFreeMemory());
				modify = true;
			}

			if (!(gatewayStatus.getUsedProcessor() == gatewayStatusCompare.getUsedProcessor())) {
				gatewaySend.setUsedProcessor(gatewayStatusCompare.getUsedProcessor());
				gatewayStatus.setUsedProcessor(gatewayStatusCompare.getUsedProcessor());
				modify = true;
			}

			if (!(gatewayStatus.getFreeProcessor() == gatewayStatusCompare.getFreeProcessor())) {
				gatewaySend.setFreeProcessor(gatewayStatusCompare.getFreeProcessor());
				gatewayStatus.setFreeProcessor(gatewayStatusCompare.getFreeProcessor());
				modify = true;
			}
			
			if(modify == true) {
				gatewaySend.setDate(gatewayStatusCompare.getDate());
				gatewaySend.setMac(gatewayStatusCompare.getMac());
				gatewayStatusService.sendGatewayStatus(gatewaySend);
			}
		}
	}

}
