package org.apache.servicemix.examples.cxf.info;

import java.util.ArrayList;
import java.util.List;

import org.apache.servicemix.examples.cxf.model.Actuator;
import org.apache.servicemix.examples.cxf.model.Device;
import org.apache.servicemix.examples.cxf.model.Sensor;

/**
 * Class responsible capture information about the device
 *
 * @author Nilson Rodrigues Sousa
 */
public class DeviceInfo {

	/**
	 * Method that returns information from an device - PROVISIONAL
	 * IMPLEMENTATION
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return Device - device information
	 */
	public Device getDevice() {
		Device device = new Device();

		device.setName("DeviceNorgren");
		device.setManufacturer("Norgren");
		device.setVersion("5.7.3");
		device.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");

		Sensor sensorTemperature = new Sensor();
		sensorTemperature.setName("SensorSiemens");
		sensorTemperature.setManufacturer("Siemens");
		sensorTemperature.setVersion("1.2.2");
		sensorTemperature.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		sensorTemperature.setMonitor("Temperature");

		Sensor sensorPressure = new Sensor();
		sensorPressure.setName("SensorOmron");
		sensorPressure.setManufacturer("Omron");
		sensorPressure.setVersion("0.5.2");
		sensorPressure.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		sensorPressure.setMonitor("Pressure");

		device.getListSensor().add(sensorTemperature);
		device.getListSensor().add(sensorPressure);

		Actuator actuadorKey = new Actuator();

		actuadorKey.setName("SensorAutonics");
		actuadorKey.setManufacturer("Autonics");
		actuadorKey.setVersion("3.1");
		actuadorKey.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		actuadorKey.setAction("On-Off");

		device.getListActuator().add(actuadorKey);

		return device;

	}

	/**
	 * Method returns a list of devices connected to the gateway -
	 * PROVISIONAL IMPLEMENTATION
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Device> - List of existing device
	 */
	public List<Device> getListDevice() {

		List<Device> listDevice = new ArrayList<Device>();

		Device device = new Device();

		device.setName("DeviceNorgren");
		device.setManufacturer("Norgren");
		device.setVersion("5.7.3");
		device.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");

		Sensor sensorTemperature = new Sensor();
		sensorTemperature.setName("SensorSiemens");
		sensorTemperature.setManufacturer("Siemens");
		sensorTemperature.setVersion("1.2.2");
		sensorTemperature.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		sensorTemperature.setMonitor("Temperature");

		Sensor sensorPressure = new Sensor();
		sensorPressure.setName("SensorOmron");
		sensorPressure.setManufacturer("Omron");
		sensorPressure.setVersion("0.5.2");
		sensorPressure.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		sensorPressure.setMonitor("Pressure");

		device.getListSensor().add(sensorTemperature);
		device.getListSensor().add(sensorPressure);

		Actuator actuadorKey = new Actuator();

		actuadorKey.setName("SensorAutonics");
		actuadorKey.setManufacturer("Autonics");
		actuadorKey.setVersion("3.1");
		actuadorKey.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		actuadorKey.setAction("On-Off");

		device.getListActuator().add(actuadorKey);

		listDevice.add(device);

		return listDevice;

	}

}
