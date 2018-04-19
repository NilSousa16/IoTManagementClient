package org.apache.servicemix.examples.cxf.info;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.servicemix.examples.cxf.model.Bundler;
import org.apache.servicemix.examples.cxf.model.CPU;
import org.apache.servicemix.examples.cxf.model.Service;
import org.apache.servicemix.examples.cxf.util.InfraUtil;

/**
 * Class responsible capture information about the gateway
 *
 * @author Nilson Rodrigues Sousa
 */
public class GatewayInfo {

	// This constructor could be the cause of the error in
	// libsigar-amd64-linux.so in java.library.path
	// Possible solution will be to put the SigarProvisioner.provision () within
	// the classes that use the sigar variable
	public GatewayInfo() {

	}

	/**
	 * Method that returns the OS description used
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - OS used
	 */
	public String getDescription() {
		return "Debian_Light";
	}

	/**
	 * Method that returns the model description
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - model description
	 */
	public String getModel() {
		return "0.11.2";
	}

	/**
	 * Method that returns the manufacturer's description
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - manufacturer description
	 */
	public String getManufacturer() {
		return "Raspberry";
	}

	/**
	 * Method that returns the firmware description
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - firmware description
	 */
	public String getFirmware() {
		return "2.1.0";
	}

	/**
	 * Method that returns the storage capacity. (Originally it would return the
	 * existing storage components with their respective characteristics)
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return long - storage capacity
	 */
	public long getStorage() {
		// List<Storage> listSim = new ArrayList<Storage>();
		//
		// Storage storage;
		//
		// for (int i = 0; i < 3; i++) {
		// storage = new Storage();
		//
		// storage.setDeviceName("/dev/sda1");
		// storage.setFreeSpaceKB(3 * 100 * 12 * 5 * i);
		// storage.setTotalSizeKB(288237920);
		// storage.setMountPoint("/");
		// storage.setOsSpecificFSType("ext4");
		//
		// listSim.add(storage);
		// }
		// return listSim;

		return new Random().nextInt(19700621);
	}

	/**
	 * Method that returns the time of the last update
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return Calendar - latest update time
	 */
	public Calendar getLastUpdate() {
		return Calendar.getInstance();
	}

	/**
	 * Method that returns the CPU characteristics of the gateways. NOT USED
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - CPU features
	 */
	public List<CPU> getCPU() {
		CPU cpu;
		List<CPU> listCPUInfoModel = new ArrayList<CPU>();

		for (int i = 0; i < 3; i++) {
			cpu = new CPU();

			cpu.setVendor("Intel");
			cpu.setClock(i + 1);
			cpu.setTotalCores(i + 1);
			cpu.setModel("Intel 4004");

			listCPUInfoModel.add(cpu);
		}

		return listCPUInfoModel;
	}

	/**
	 * Method that returns the gateway MAC
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - address mac
	 */
	public String getMac() {
		return InfraUtil.getMacAddress();
	}

	/**
	 * Method that returns the gateway IP
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - address ip
	 */
	public String getIp() {
		return InfraUtil.getIpMachine();
	}

	/**
	 * Method that returns the hostname
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - hostname
	 */
	public String getHostName() {
		return "RaspberryHostName";
	}

	/**
	 * Method that returns the location
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - location
	 */
	public String getLocation() {
		return "-12.9990189,-38.5140298";
	}

	/**
	 * Method that returns the list of gateway bundles
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Bundler> - bundle list
	 */
	public List<Bundler> getListBundler() {
		return BundlerInfo.listBundler;
	}

	/**
	 * Method that returns the list of gateway service
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return List<Service> - service list
	 */
	public List<Service> getService() {
		return ServiceInfo.listService;
	}

	/**
	 * Method that returns the list of existing network interfaces. NOT USED.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - list interface network
	 */
	public String[] getIntefaceNetwork() {
		return null;
	}

}
