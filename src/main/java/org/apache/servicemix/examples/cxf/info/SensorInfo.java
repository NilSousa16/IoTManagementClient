package org.apache.servicemix.examples.cxf.info;

import java.util.ArrayList;
import java.util.List;

import org.apache.servicemix.examples.cxf.model.Sensor;

/**
 * Class responsible capture information about the sensor
 *
 * @author Nilson Rodrigues Sousa
 */
public class SensorInfo {

	/**
	 * Method that returns information from an sensor - PROVISIONAL
	 * IMPLEMENTATION
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return Sensor - sensor information
	 */
	public Sensor getSensorInfo() {
		Sensor sensor = new Sensor();

		sensor.setName("SensorSiemens");
		sensor.setManufacturer("Siemens");
		sensor.setVersion("1.2.2");
		sensor.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		sensor.setMonitor("Temperature");

		return sensor;
	}

	/**
	 * Method that returns information from an sensor - PROVISIONAL
	 * IMPLEMENTATION
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Sensor> - sensor information
	 */
	public List<Sensor> getListSensor() {

		List<Sensor> listSensor = new ArrayList<Sensor>();

		Sensor sensor = new Sensor();

		sensor.setName("SensorSiemens");
		sensor.setManufacturer("Siemens");
		sensor.setVersion("1.2.2");
		sensor.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		sensor.setMonitor("Temperature");

		listSensor.add(sensor);
		
		sensor = new Sensor();

		sensor.setName("SensorMenics");
		sensor.setManufacturer("Menics");
		sensor.setVersion("4.2.2");
		sensor.setDescriptionSemantic("http://www.loa-cnr.it/ontologies/DUL.owl#isDescribedBy");
		sensor.setMonitor("PH");

		listSensor.add(sensor);
		
		return listSensor;
	}

}
