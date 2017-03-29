package org.apache.servicemix.examples.cxf.test;

import java.util.List;

import com.github.cb372.metrics.sigar.SigarMetrics;

import kamon.sigar.SigarProvisioner;

public class MetricsSigar {

	public static void main(String[] args) throws Exception {
		SigarProvisioner.provision();
		SigarMetrics sm = SigarMetrics.getInstance();

		List<com.github.cb372.metrics.sigar.FilesystemMetrics.FileSystem> listF = sm.filesystems().filesystems();

		for (com.github.cb372.metrics.sigar.FilesystemMetrics.FileSystem fs : listF) {
			String path = fs.mountPoint().toString();
			if(path.length() < 6) {
				path += "zzzzzzzz";
			}		
			
			if (path.substring(0, 7).equalsIgnoreCase("/media/") || fs.genericFSType().toString().equals("LocalDisk")) {
				System.out.println(">>>>>>>>> DeviceName: " + fs.deviceName());
				System.out.println(">>>>>>>>> FreeSpaceKB: " + fs.freeSpaceKB());
				System.out.println(">>>>>>>>> TotalSizeKB: " + fs.totalSizeKB());
				System.out.println(">>>>>>>>> MountPoint: " + fs.mountPoint());
				System.out.println(">>>>>>>>> OsSpecificFSType: " + fs.osSpecificFSType());
				System.out.println(">>>>>>>>> GenericFSType: " + fs.genericFSType());
				System.out.println("\n###########################################################\n");
			}
		}
	}
}