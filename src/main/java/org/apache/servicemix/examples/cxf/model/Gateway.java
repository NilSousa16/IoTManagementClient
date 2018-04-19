package org.apache.servicemix.examples.cxf.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A class that represents the properties that exist in a gateway. This model is
 * used to keep the last state of the gateway properties in memory and is also
 * used every time you want to send information to the server.
 *
 * @author Nilson Rodrigues Sousa
 */
@XmlRootElement(name = "Gateway")
public class Gateway {

	/* OS description used */
	private String description;

	/* Gateway model description */
	private String model;

	/* Manufacturer Description */
	private String manufacturer;

	/* not defined */
	private String firmware;

	/* Describe the total storage capacity of a gateway */
	private long storage;

	/* Describe of the last update made */
	private Calendar lastUpdate;

	/* Logs information about the processor - not used */
	// private List<CPU> cpu;

	/* Describe the mac address */
	private String mac;

	/* Describe the ip address */
	private String ip;

	/* Describe the hostname */
	private String hostName;

	/* Describe the location of the gateway */
	private String location;

	/* Describe information about the bundles in the gateway */
	private List<Bundler> listBundler = new ArrayList<Bundler>();

	/* Describe information about existing network interfaces - not used */
	// private String[] intefaceNetwork;

	/* Describe information about the services in the gateway */
	private List<Service> listService = new ArrayList<Service>();

	/* Describe information about devices connected to the gateway */
	private List<Device> listDevice = new ArrayList<Device>();

	/* identifies first communication */
	private int flag = 1;

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

	public long getStorage() {
		return storage;
	}

	public void setStorage(long storage) {
		this.storage = storage;
	}

	public Calendar getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Calendar lastUpdate) {
		this.lastUpdate = lastUpdate;
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

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Bundler> getListBundler() {
		return listBundler;
	}

	public void setListBundler(List<Bundler> listBundler) {
		this.listBundler = listBundler;
	}

	public List<Service> getListService() {
		return listService;
	}

	public void setListService(List<Service> listService) {
		this.listService = listService;
	}

	public List<Device> getListDevice() {
		return listDevice;
	}

	public void setListDevice(List<Device> listDevice) {
		this.listDevice = listDevice;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
