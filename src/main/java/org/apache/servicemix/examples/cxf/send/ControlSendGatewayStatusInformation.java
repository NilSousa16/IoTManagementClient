package org.apache.servicemix.examples.cxf.send;

import org.apache.servicemix.examples.cxf.info.GatewayStatusInfo;
import org.apache.servicemix.examples.cxf.model.GatewayStatus;
import org.apache.servicemix.examples.cxf.service.GatewayStatusService;

/**
 * Class responsible for verifying changes in gateway status information and
 * requesting it to be sent to the server.
 *
 * @author Nilson Rodrigues Sousa
 */
public class ControlSendGatewayStatusInformation {

	/* instance that stores the information from the last update */
	private static GatewayStatus gatewayStatus;

	/* instance sending the information to the server */
	private GatewayStatusService gatewayStatusService;

	/* instance that tells the gateway status conditions */
	private GatewayStatusInfo gatewayStatusInfo;

	/**
	 * Method to create instances of gatewayStatusService and gatewayStatusInfo.
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public ControlSendGatewayStatusInformation() {
		gatewayStatusService = new GatewayStatusService();
		gatewayStatusInfo = new GatewayStatusInfo();
	}

	/**
	 * A method that compares gateway status information and prompts you to send
	 * the modified information to the server.
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public void compareInfoGatewayStatus() {

		/* responsible for storing information to be sent */
		GatewayStatus gatewaySend;

		/* information retrieved for comparison with last update */
		GatewayStatus gatewayStatusCompare = new GatewayStatus();

		/*
		 * conditional that verifies that the gateway has already been properly
		 * registered on the server
		 */
		if (ControlSendGatewayInformation.storaged) {

			/* conditional for evaluation of first iteration */
			if (gatewayStatus == null) {
				gatewayStatus = new GatewayStatus();

				/* mounting information to be sent */
				gatewayStatus.setMac(gatewayStatusInfo.getMac());
				gatewayStatus.setDate(gatewayStatusInfo.getDate());
				gatewayStatus.setBaterryLevel(gatewayStatusInfo.getBaterryLevel());
				gatewayStatus.setFreeMemory(gatewayStatusInfo.getFreeMemory());
				gatewayStatus.setFreeProcessor(gatewayStatusInfo.getFreeProcessor());
				gatewayStatus.setTotalMemory(gatewayStatusInfo.getTotalMemory());
				gatewayStatus.setUsedMemory(gatewayStatusInfo.getUsedMemory());
				gatewayStatus.setUsedProcessor(gatewayStatusInfo.getUsedProcessor());

				/* sending information for the first time */
				gatewayStatusService.sendGatewayStatus(gatewayStatus);
			} else {

				// returns the gateway status information
				gatewayStatusCompare.setMac(gatewayStatusInfo.getMac());
				gatewayStatusCompare.setDate(gatewayStatusInfo.getDate());
				gatewayStatusCompare.setBaterryLevel(gatewayStatusInfo.getBaterryLevel());
				gatewayStatusCompare.setFreeMemory(gatewayStatusInfo.getFreeMemory());
				gatewayStatusCompare.setFreeProcessor(gatewayStatusInfo.getFreeProcessor());
				gatewayStatusCompare.setTotalMemory(gatewayStatusInfo.getTotalMemory());
				gatewayStatusCompare.setUsedMemory(gatewayStatusInfo.getUsedMemory());
				gatewayStatusCompare.setUsedProcessor(gatewayStatusInfo.getUsedProcessor());

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

				/* conditional assessment of the need to send information */
				if (modify == true) {
					gatewaySend.setDate(gatewayStatusCompare.getDate());
					gatewaySend.setMac(gatewayStatusCompare.getMac());
					
					/* sending information */
					gatewayStatusService.sendGatewayStatus(gatewaySend);
				}
			}
		}
	}
}
