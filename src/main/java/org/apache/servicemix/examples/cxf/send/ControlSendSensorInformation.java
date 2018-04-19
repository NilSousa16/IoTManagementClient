package org.apache.servicemix.examples.cxf.send;

import java.util.List;

import org.apache.servicemix.examples.cxf.info.SensorInfo;
import org.apache.servicemix.examples.cxf.model.Sensor;
import org.apache.servicemix.examples.cxf.service.SensorService;

/**
 * Class responsible for verifying changes to the sensors information at the
 * gateway and requesting submission of changes. 
 * NOT IMPLEMENTED - IMPLEMENTATION WAS DONE ON DEVICE
 * POSSIBLE FUTURE USE
 * @author Nilson Rodrigues Sousa
 */
public class ControlSendSensorInformation {

	/* responsible for storing last gateway sensor upgrade information */
	private static List<Sensor> listSensor;

	/* responsible for sending the changes to the server */
	private SensorService SensorService;

	/* responsible for capturing sensor information */
	private SensorInfo sensorInfo = null;

	/**
	 * Builder method for instantiation
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public ControlSendSensorInformation() {

	}

	/**
	 * Method used by the model for instantiation of bundlerInfo
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public void setBundlerInfo(SensorInfo sensorInfo) {
		this.sensorInfo = sensorInfo;
	}

	/**
	 * Method that compares sensor information
	 * 
	 * @author Nilson Rodrigues Sousa
	 */
	public void compareSensor() {

		/*
		 * Conditional that verifies that the gateway has already been properly
		 * registered on the server
		 */
		if (ControlSendGatewayInformation.storaged) {

		}
	}

	/**
	 * Method responsible for requesting deletion of disconnected sensors
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param disconnected
	 *            List<Sensor> - List sensors disconnected
	 */
	public void infoSensorDisconnected(List<Sensor> disconnected) {

	}

	/**
	 * Method responsible for requesting inclusion of connected sensors
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param connected
	 *            List<Sensor> - List sensors connected
	 */
	public void infoSensorConnected(List<Sensor> connected) {

	}

}
