package org.apache.servicemix.examples.cxf.model;

//import com.github.cb372.metrics.sigar.FilesystemMetrics.FSType;

/**
 * Class representing a gateway storage component.
 *
 * @author Nilson Rodrigues Sousa
 */
public class Storage {

	/* Describes the device name */
	private String name;
	
	/* Describes the mount point */
	private String mountPoint;

	/* Not implemented */
	// private FSType genericFSType;
	
	/* Type of storage device */
	private String osSpecificFSType;
	
	/* Describes the total space in KB */
	private long totalSizeKB;
	
	/* Describes the total amount of free space in KB */
	private long freeSpaceKB;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMountPoint() {
		return mountPoint;
	}

	public void setMountPoint(String mountPoint) {
		this.mountPoint = mountPoint;
	}

	// public FSType getGenericFSType() {
	// return genericFSType;
	// }
	//
	// public void setGenericFSType(FSType genericFSType) {
	// this.genericFSType = genericFSType;
	// }

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
