package org.apache.servicemix.examples.cxf.send;

import java.util.List;

import org.apache.servicemix.examples.cxf.info.ActuatorInfo;
import org.apache.servicemix.examples.cxf.model.Actuator;
import org.apache.servicemix.examples.cxf.service.ActuatorService;

/**
 * Class responsible for verifying changes to the actuator information at the
 * gateway and requesting submission of changes. 
 * NOT IMPLEMENTED - IMPLEMENTATION WAS DONE ON DEVICE
 * POSSIBLE FUTURE USE
 * @author Nilson Rodrigues Sousa
 */
public class ControlSendActuatorInformation {
	
	/* responsible for storing last gateway actuator upgrade information */
	private static List<Actuator> listActuator;
	
	/* responsible for sending the changes to the server */
	private ActuatorService actuatorService;
	
	/* responsible for capturing actuator information */
	private ActuatorInfo actuatorInfo = null;

	/**
	 * Builder method for instantiation.
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public ControlSendActuatorInformation() {

	}

	/**
	 * Method used by the model for instantiation of actuatorInfo.
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public void setActuadorInfo(ActuatorInfo actuatorInfo) {
		this.actuatorInfo = actuatorInfo;
	}

	/**
	 * Method that compares actuator information.
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public void compareActuator() {

		/*
		 * Conditional that verifies that the gateway has already been properly
		 * registered on the server
		 */
		if (ControlSendGatewayInformation.storaged) {

		}
	}

	/**
	 * Method responsible for requesting deletion of disconnected actuators.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param disconnected
	 *            List<Actuator> - List actuators disconnected
	 */
	public void infoActuatorDisconnected(List<Actuator> disconnected) {

	}

	/**
	 * Method responsible for requesting inclusion of connected actuators.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param connected
	 *            List<Actuator> - List actuators connected
	 */
	public void infoActuatorConnected(List<Actuator> connected) {

	}

}
