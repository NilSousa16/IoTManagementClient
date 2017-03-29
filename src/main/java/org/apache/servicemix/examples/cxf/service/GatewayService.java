package org.apache.servicemix.examples.cxf.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.servicemix.examples.cxf.info.GatewayInfo;
import org.apache.servicemix.examples.cxf.model.CPU;
import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.model.Service;
import org.apache.servicemix.examples.cxf.model.Storage;

//import org.json.JSONObject;

@Path("/gatewayservice/")
public class GatewayService {
	private Gateway gateway;
	private GatewayInfo gatewayInfo;

	public GatewayService() {
		System.out.println("Gateway monitoring started.");		
	}

	@GET
	@Path("/gateway/gt/")
	@Produces("application/json")
	public Gateway getGateway() {
		gateway = new Gateway();
		
		gatewayInfo = new GatewayInfo();

		gateway.setDescription(this.getDescriptionService());
		gateway.setModel(this.getModelService());
		gateway.setManufacturer(this.getManufacturerService());
		gateway.setFirmware(this.getFirmwareService());
		gateway.setStatus(this.getStatusService());
		//gateway.setStorage(this.getStorageService());
		gateway.setLastUpdate(this.getLastUpdateService());
		gateway.setBaterryLevel(this.getBaterryLevelService());
		gateway.setTotalMemory(this.getTotalMemoryService());
		gateway.setUsedMemory(this.getUsedMemoryService());
		gateway.setFreeMemory(this.getFreeMemoryService());
		gateway.setUsedProcessor(this.getUsedProcessorService());
		gateway.setFreeProcessor(this.getFreeProcessorService());		
		//gateway.setCpu(this.getCPUService());
		gateway.setMac(this.getMacService());
		gateway.setIp(this.getIpService());
		gateway.setHostName(this.getHostNameService());
		gateway.setLocation(this.getLocationService());
		//gateway.setService(this.getServiceService());
		//gateway.setIntefaceNetwork(this.getIntefaceNetworkService());
		
		return gateway;
	}
	
	// >>>>>>>>METHODS FOR CATCHING INFORMATION<<<<<<<<
	// description of OS in use
	public String getDescriptionService() {
		return gatewayInfo.getDescription();
	}
	
	// to be defined
	public String getModelService() {
		return gatewayInfo.getModel();
	}

	// to be defined
	public String getManufacturerService() {
		return gatewayInfo.getManufacturer();
	}
	
	// to be define
	public String getFirmwareService() {
		return gatewayInfo.getFirmware();
	}
	
	// on-off used server side opportunistically
	public boolean getStatusService() {
		return gatewayInfo.isStatus();
	}

	// returns the list of components in existing stores
	public List<Storage> getStorageService() {
		return gatewayInfo.getStorage();
	}

	// Method for the server side, but the client can register its actualization if possible
	public String getLastUpdateService() {
		return gatewayInfo.getLastUpdate();
	}

	// returns the battery level in percent
	public int getBaterryLevelService() {
		return gatewayInfo.baterryLevel();
	}

	// returns the total of memory
	public long getTotalMemoryService() {
		return gatewayInfo.getTotalMemory();
	}
	
	// returns the total of used memory
	public long getUsedMemoryService() {
		return gatewayInfo.getUsedMemory();
	}
	
	// returns the total of free memory
	public long getFreeMemoryService() {
		return gatewayInfo.getFreeMemory();
	}

	// returns the percentage value of the processor's capacity used
	public double getUsedProcessorService() {
		return gatewayInfo.getUsedProcessor();
	}

	// returns the percentage value of the free processor capacity
	public double getFreeProcessorService() {
		return gatewayInfo.getFreeProcessor();
	}

	// returns information about CPU characteristics
	public List<CPU> getCPUService() {
		return gatewayInfo.getCPU();
	}
	
	// returns the mac address
	public String getMacService() {
		return gatewayInfo.getMac();
	}

	// returns the ip address
	public String getIpService() {
		return gatewayInfo.getIp();
	}

	// returns the hostname
	public String getHostNameService() {
		return gatewayInfo.getHostName();
	}
	
	// returns the fake location
	public String getLocationService() {
		return gatewayInfo.getLocation();
	}

	// returns the list of services
	public List<Service> getServiceService() {
		return gatewayInfo.getService();
	}

	//returns a list with the existing network interfaces
	public String[] getIntefaceNetworkService() {
		return gatewayInfo.getIntefaceNetwork();
	}
}
