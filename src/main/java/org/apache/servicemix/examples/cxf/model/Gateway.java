package org.apache.servicemix.examples.cxf.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Gateway")
public class Gateway { // class with the gateway properties

	private String description; // description of OS in use
	private String model; // to be defined
	private String manufacturer; // to be defined
	private String firmware; // to be defined
	private boolean status; // on-off used server side opportunistically
	//private List<Storage> storage; // returns the list of components in existing stores
	private String lastUpdate; // Method for the server side, but the client can register its actualization if possible
	private int baterryLevel; // returns the battery level in percent
	private long totalMemory; // returns the total of memory
	private long usedMemory; // returns the total of used memory
	private long freeMemory; // returns the total of free memory
	private double usedProcessor; // returns the percentage value of the processor's capacity used
	private double freeProcessor; // returns the percentage value of the free processor capacity
	//private List<CPU> cpu; // returns information about CPU characteristics
	private String mac; // returns the mac address
	private String ip; // returns the ip address
	private String hostName; // returns the hostname
	private String location; // returns the fake location
	//private List<Service> service = new ArrayList<Service>(); // returns the list of services
	//private String[] intefaceNetwork; //returns a list with the existing network interfaces
	
	
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

//	public List<Storage> getStorage() {
//		return storage;
//	}
//
//	public void setStorage(List<Storage> storage) {
//		this.storage = storage;
//	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getBaterryLevel() {
		return baterryLevel;
	}

	public void setBaterryLevel(int baterryLevel) {
		this.baterryLevel = baterryLevel;
	}
	
	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public double getUsedProcessor() {
		return usedProcessor;
	}

	public void setUsedProcessor(double usedProcessor) {
		this.usedProcessor = usedProcessor;
	}

	public double getFreeProcessor() {
		return freeProcessor;
	}

	public void setFreeProcessor(double freeProcessor) {
		this.freeProcessor = freeProcessor;
	}

//	public List<CPU> getCpu() {
//		return cpu;
//	}
//
//	public void setCpu(List<CPU> cpu) {
//		this.cpu = cpu;
//	}

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

//	public List<Service> getService() {
//		return service;
//	}
//
//	public void setService(List<Service> service) {
//		this.service = service;
//	}
//
//	public String[] getIntefaceNetwork() {
//		return intefaceNetwork;
//	}
//
//	public void setIntefaceNetwork(String[] intefaceNetwork) {
//		this.intefaceNetwork = intefaceNetwork;
//	}
}
