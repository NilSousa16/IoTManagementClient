package org.apache.servicemix.examples.cxf.info;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.servicemix.examples.cxf.model.CPU;
import org.apache.servicemix.examples.cxf.model.Service;
import org.apache.servicemix.examples.cxf.model.Storage;
import org.hyperic.jni.ArchNotSupportedException;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarLoader;

import com.github.cb372.metrics.sigar.FilesystemMetrics.FileSystem;
import com.github.cb372.metrics.sigar.SigarMetrics;

import kamon.sigar.SigarProvisioner;

public class GatewayInfo {

	private final Sigar sigar;

	// This constructor could be the cause of the error in libsigar-amd64-linux.so in java.library.path
	// Possible solution will be to put the SigarProvisioner.provision () within the classes that use the sigar variable
	public GatewayInfo() {
		try {
			//final File location = new File("target/lib");
			final File location = new File("src/main/resources/META-INF/lib");	
			SigarProvisioner.provision(location);
			System.out.println("Configuration successfully completed!");
		} catch (Exception e) {
			System.out.println("Error in providing the SIGAR library.");
			e.printStackTrace();
		}
		
		sigar = new Sigar();
		
	}

	// description of OS in use
	public String getDescription() {
		OperatingSystem sys = OperatingSystem.getInstance();
		return sys.getDescription();
	}

	// to be defined
	public String getModel() {
		return null;
	}

	// to be defined
	public String getManufacturer() {
		return null;
	}

	// to be defined
	public String getFirmware() {
		return null;
	}
	
	// on-off used server side opportunistically
	public boolean isStatus() {
		// possible functionality is to use the method to return error status.
		return false;
	}
	
	// returns the list of components in existing stores
	public List<Storage> getStorage() {
		SigarMetrics sm = SigarMetrics.getInstance();

		List<FileSystem> list = sm.filesystems().filesystems();
		List<Storage> listSim = new ArrayList<Storage>();
		Storage sim;

		for (FileSystem fs : list) {
			String path = fs.mountPoint().toString();
			if (path.length() < 6) {
				path += "zzzzzzzz";
			}

			// considerando que todo disco válido estará no caminho /media/ ou
			// terá FSType == LocalDisk
			if (path.substring(0, 7).equalsIgnoreCase("/media/") || fs.genericFSType().toString().equals("LocalDisk")) {
				sim = new Storage();

				sim.setDeviceName(fs.deviceName());
				sim.setFreeSpaceKB(fs.freeSpaceKB());
				sim.setTotalSizeKB(fs.totalSizeKB());
				sim.setMountPoint(fs.mountPoint());
				sim.setOsSpecificFSType(fs.osSpecificFSType());
				//sim.setGenericFSType(fs.genericFSType());

				listSim.add(sim);
			}
		}
		return listSim;
	}
	// method for the server side, but the client can register its actualization if possible
	public String getLastUpdate() {
		return this.getDateHour();
	}
	
	// returns the battery level in percent
	// if it is not possible to use the command will be returned -1
	public int baterryLevel() {
		Map<String, String> result;
		String reportBattery;
		int ind = 0;

		//Search for some power source (battery)
		while (true) {
			result = commandTerminal("upower -i /org/freedesktop/UPower/devices/battery_BAT" + ind);
			reportBattery = result.toString();
			int position = reportBattery.indexOf("native-path");
			String line = reportBattery.substring(position, position + 30);
			if (line.indexOf("null") == -1) {
				break;
			} else {
				ind++;
			}
			if (ind == 15) {
				return -1;
			}
		}
		// captures the position of the initial character of the substring
		int position = reportBattery.indexOf("percentage");
		// returns the line of the substring
		String line = reportBattery.substring(position, position + 27);
		// captures the position of the initial character of the substring
		int pos = line.indexOf("%");
		return Integer.parseInt(line.substring(pos - 3, pos).trim());
	}

	// returns the total of memory
	public long getTotalMemory() {
		try {
			Mem mem = this.sigar.getMem();
			return mem.getTotal();
		} catch (SigarException e) {
			System.out.println("Failed to capture the total memory value.");
			e.printStackTrace();
			return 0;
		}
	}	
	
	// returns the total of used memory
	public long getUsedMemory() {
		try {
			Mem mem = this.sigar.getMem();
			return mem.getUsed();
		} catch (SigarException e) {
			System.out.println("Failed to capture the memory value used.");
			e.printStackTrace();
			return 0;
		}
	}

	// returns the total of free memory
	public long getFreeMemory() {
		try {
			Mem mem = this.sigar.getMem();
			return mem.getFree();
		} catch (SigarException e) {
			System.out.println("Failed to capture free memory value.");
			e.printStackTrace();
			return 0;
		}
	}

	// returns the percentage value of the processor's capacity used
	public double getUsedProcessor() {
		try {
			CpuPerc cpu = this.sigar.getCpuPerc();
			return (cpu.getCombined() * 100);
		} catch (SigarException e) {
			System.out.println("Failure to capture the percentage of processor usage.");
			e.printStackTrace();
			return 0;
		}
	}

	// returns the percentage value of the free processor capacity
	public double getFreeProcessor() {
		try {
			CpuPerc cpu = this.sigar.getCpuPerc();
			return 100 - (cpu.getCombined() * 100);
		} catch (SigarException e) {
			System.out.println("Failure to capture the free processor percentage.");
			e.printStackTrace();
			return 0;
		}
	}

	// returns information about CPU characteristics
	public List<CPU> getCPU() {
		CPU cim;
		CpuInfo[] cpuInfoList = null;
		List<CPU> listCPUInfoModel = new ArrayList<CPU>();
		try {
			cpuInfoList = sigar.getCpuInfoList();

			for (CpuInfo info : cpuInfoList) {
				cim = new CPU();

				cim.setVendor(info.getVendor());
				cim.setClock(info.getMhz());
				cim.setTotalCores(info.getTotalCores());
				cim.setModel(info.getModel());

				listCPUInfoModel.add(cim);
			}
			return listCPUInfoModel;
		} catch (SigarException e) {
			System.out.println("Failed to capture CPU information.");
			e.printStackTrace();
			return null;
		}
	}

	// returns the mac address
	public String getMac() {
		try {
			NetInterfaceConfig config = this.sigar.getNetInterfaceConfig(null);
			return config.getHwaddr();
		} catch (SigarException e) {
			System.out.println("Mac address capture failed.");
			e.printStackTrace();
			return "";
		}
	}
	
	// returns the ip address
	public String getIp() {
		try {
			NetInterfaceConfig config = this.sigar.getNetInterfaceConfig(null);
			return config.getAddress();
		} catch (SigarException e) {
			System.out.println("Failed to capture the IP address.");
			e.printStackTrace();
			return "";
		}
	}

	// returns the hostname
	public String getHostName() {
		try {
			org.hyperic.sigar.NetInfo info = this.sigar.getNetInfo();
			return info.getHostName();
		} catch (SigarException e) {
			System.out.println("Failure to capture the HostName.");
			e.printStackTrace();
			return "";
		}
	}

	// returns the location
	public String getLocation() {
		// Will return a fake location
		return null;
	}

	// will return a fake location
	public List<Service> getService() {
		// not implemented
		return null;
	}

	// returns a list with the existing network interfaces
	// untested method may then display errors	
	public String[] getIntefaceNetwork() {
		try {
			String[] interf = this.sigar.getNetInterfaceList();
			return interf;
		} catch (SigarException e) {
			System.out.println("Failed to capture the network interface.");
			e.printStackTrace();
		}
		return null;
	}

	// >>>>>>>>>>AUXILIARY METHODS<<<<<<<<<<

	// returns date and time
	public String getDateHour() {
		String data = "dd/MM/yyyy";
		String hora = "h:mm - a";
		String data1, hora1;
		java.util.Date agora = new java.util.Date();
		;
		SimpleDateFormat formata = new SimpleDateFormat(data);
		data1 = formata.format(agora);
		formata = new SimpleDateFormat(hora);
		hora1 = formata.format(agora);
		return data1 + " " + hora1;
	}

	// method to run linux command
	private static Map<String, String> commandTerminal(String stringCommand) {
		Runtime run = Runtime.getRuntime();
		Process proc = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			String command = stringCommand;
			proc = run.exec(command);
			result.put("input", inputStreamToString(proc.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// auxiliary method for the commandTerminal
	private static String inputStreamToString(InputStream isr) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(isr));
			StringBuilder sb = new StringBuilder();
			String s = null;
			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}
			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
