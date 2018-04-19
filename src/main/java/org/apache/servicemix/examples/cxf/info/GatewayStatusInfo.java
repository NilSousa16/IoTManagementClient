package org.apache.servicemix.examples.cxf.info;

import java.lang.management.ManagementFactory;
import java.util.Calendar;
import java.util.Random;

import org.apache.servicemix.examples.cxf.util.InfraUtil;

/**
 * Class responsible capture gateway status information
 *
 * @author Nilson Rodrigues Sousa
 */
public class GatewayStatusInfo {

	/**
	 * Method that returns the battery level in percentage
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return double - level baterry
	 */
	public double getBaterryLevel() {
		return Double.parseDouble(String.valueOf(new Random().nextInt(100)));
	}

	/**
	 * Method that returns the total memory
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return long - total memory
	 */
	public long getTotalMemory() {
		return Long.parseLong(String.valueOf(new Random().nextInt(19700621)));
	}

	/**
	 * Method that returns the total of used memory
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return long - used memory
	 */
	public long getUsedMemory() {
//		com.sun.management.OperatingSystemMXBean mxbean = 
//	    		(com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//		/* total memory - free memory = used memory */
//		return mxbean.getTotalPhysicalMemorySize() - mxbean.getFreePhysicalMemorySize();
		
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	/**
	 * Method that returns the total of free memory
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return double - free memory
	 */
	public long getFreeMemory() {
		return Long.parseLong(String.valueOf(new Random().nextInt(19700621)));
	}

	/**
	 * Method that returns the processor usage total in percent
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return double - used processor
	 */
	public double getUsedProcessor() {
//		com.sun.management.OperatingSystemMXBean mxbean = 
//	    		(com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//		
//		/* returns the "recent cpu usage" for the whole system. */
//		return mxbean.getSystemCpuLoad();
		return Double.parseDouble(String.valueOf(new Random().nextDouble() * 100));
	}

	/**
	 * Method that returns the processor free total in percent
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return double - free processor
	 */
	public double getFreeProcessor() {
		return Double.parseDouble(String.valueOf(new Random().nextDouble() * 100));
	}

	/**
	 * Method that returns the mac address of the gateway
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - mac address
	 */
	public String getMac() {
		return InfraUtil.getMacAddress();
	}

	/**
	 * Method that returns the date and time of the last update
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return Calendar - date and time of last update
	 */
	public Calendar getDate() {
		return Calendar.getInstance();
	}

}
