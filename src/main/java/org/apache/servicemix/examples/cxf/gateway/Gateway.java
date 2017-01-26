package org.apache.servicemix.examples.cxf.gateway;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.servicemix.examples.cxf.service.Service;

@XmlRootElement(name = "Gateway")
public class Gateway {

	private String description;
	private String model;
	private String manufacturer;
	private String firmware;
	private boolean status;
	private boolean storage;
	private Date lastUpdate;
	private String baterryLevel;
	private int usedMemory;
	private int freeMemory;
	private int usedProcessor;
	private int freeProcessor;
	private String mac;
	private String ip;
	private String location;
	private Set<Service> service = new HashSet<Service>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isStorage() {
		return storage;
	}

	public void setStorage(boolean storage) {
		this.storage = storage;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getBaterryLevel() {
		return baterryLevel;
	}

	public void setBaterryLevel(String baterryLevel) {
		this.baterryLevel = baterryLevel;
	}

	public int getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(int usedMemory) {
		this.usedMemory = usedMemory;
	}

	public int getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(int freeMemory) {
		this.freeMemory = freeMemory;
	}

	public int getUsedProcessor() {
		return usedProcessor;
	}

	public void setUsedProcessor(int usedProcessor) {
		this.usedProcessor = usedProcessor;
	}

	public int getFreeProcessor() {
		return freeProcessor;
	}

	public void setFreeProcessor(int freeProcessor) {
		this.freeProcessor = freeProcessor;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Service> getService() {
		return service;
	}

	public void setService(Set<Service> service) {
		this.service = service;
	}

}
