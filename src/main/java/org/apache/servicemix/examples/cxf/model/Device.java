package org.apache.servicemix.examples.cxf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents the properties of an device.
 *
 * @author Nilson Rodrigues Sousa
 */
public class Device {

	/* Describes the name of the device */
	private String name;

	/* Describes the manufacturer of the device */
	private String manufacturer;

	/* Describes the version of the device */
	private String version;

	/* Describes the location of the device */
	private String location;

	/* Describes the URI of the device */
	private String descriptionSemantic;

	/* Device sensor list */
	private List<Sensor> listSensor = new ArrayList<Sensor>();

	/* Device actuador list */
	private List<Actuator> listActuator = new ArrayList<Actuator>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescriptionSemantic() {
		return descriptionSemantic;
	}

	public void setDescriptionSemantic(String descriptionSemantic) {
		this.descriptionSemantic = descriptionSemantic;
	}

	public List<Sensor> getListSensor() {
		return listSensor;
	}

	public void setListSensor(List<Sensor> listSensor) {
		this.listSensor = listSensor;
	}

	public List<Actuator> getListActuator() {
		return listActuator;
	}

	public void setListActuator(List<Actuator> listActuador) {
		this.listActuator = listActuador;
	}

}
