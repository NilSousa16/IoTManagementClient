package org.apache.servicemix.examples.cxf.model;

/**
 * A class that represents the properties of an CPU. This class will not be used
 * at this time due to failure to implement hardware information capture
 *
 * @author Nilson Rodrigues Sousa
 */
public class CPU {
	
	/* Describes a vendor */
	String vendor;
	
	/* Describes the processor clock */
	int clock;
	
	/* Describes the number of cores */
	int TotalCores;
	
	/* Describes the processor model */
	String Model;

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getClock() {
		return clock;
	}

	public void setClock(int clock) {
		this.clock = clock;
	}

	public int getTotalCores() {
		return TotalCores;
	}

	public void setTotalCores(int totalCores) {
		TotalCores = totalCores;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

}
