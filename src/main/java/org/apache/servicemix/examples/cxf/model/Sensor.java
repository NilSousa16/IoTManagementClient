package org.apache.servicemix.examples.cxf.model;

/**
 * Class that describes a sensor.
 *
 * @author Nilson Rodrigues Sousa
 */
public class Sensor extends Device {
	
	/* Description of the type of monitoring done by a sensor */
	private String monitor;

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

}
