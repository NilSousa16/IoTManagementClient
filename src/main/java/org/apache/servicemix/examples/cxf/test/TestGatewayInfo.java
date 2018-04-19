package org.apache.servicemix.examples.cxf.test;

import org.apache.servicemix.examples.cxf.info.GatewayInfo;
import org.apache.servicemix.examples.cxf.model.CPU;

public class TestGatewayInfo {
	
	public static void main (String args []) {
		GatewayInfo gi = new GatewayInfo();
		
		for(int i = 0; i <= 3; i++) {
			System.out.println("############################################");
			System.out.println("Description: " + gi.getDescription());
			System.out.println("Firmware: " + gi.getFirmware());
			System.out.println("HostName: " + gi.getHostName());
			System.out.println("Ip: " + gi.getIp());
			System.out.println("LastUpdate: " + gi.getLastUpdate());
			System.out.println("Location: " + gi.getLocation());
			System.out.println("Mac: " + gi.getMac());
			System.out.println("Manufacturer: " + gi.getManufacturer());
			System.out.println("Model: " + gi.getModel());
			System.out.println("Storage: " + gi.getStorage());
			for(CPU cpu : gi.getCPU()) {
				System.out.println("CPU: " + cpu.getTotalCores());
			}
		}
			
	}

}
