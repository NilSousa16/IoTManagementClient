package org.apache.servicemix.examples.cxf.model;

//import com.github.cb372.metrics.sigar.FilesystemMetrics.FSType;

public class Storage {

	private String deviceName;
	private String mountPoint;
	//private FSType genericFSType;
	private String osSpecificFSType;
	private long totalSizeKB;
	private long freeSpaceKB;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getMountPoint() {
		return mountPoint;
	}

	public void setMountPoint(String mountPoint) {
		this.mountPoint = mountPoint;
	}

//	public FSType getGenericFSType() {
//		return genericFSType;
//	}
//
//	public void setGenericFSType(FSType genericFSType) {
//		this.genericFSType = genericFSType;
//	}

	public String getOsSpecificFSType() {
		return osSpecificFSType;
	}

	public void setOsSpecificFSType(String osSpecificFSType) {
		this.osSpecificFSType = osSpecificFSType;
	}

	public long getTotalSizeKB() {
		return totalSizeKB;
	}

	public void setTotalSizeKB(long totalSizeKB) {
		this.totalSizeKB = totalSizeKB;
	}

	public long getFreeSpaceKB() {
		return freeSpaceKB;
	}

	public void setFreeSpaceKB(long freeSpaceKB) {
		this.freeSpaceKB = freeSpaceKB;
	}

}
