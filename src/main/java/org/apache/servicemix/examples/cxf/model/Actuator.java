package org.apache.servicemix.examples.cxf.model;

/**
 * A class that represents the properties of an actuator.
 *
 * @author Nilson Rodrigues Sousa
 */
public class Actuator extends Device {
	
	/* Describe the action performed by an actuator */
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
