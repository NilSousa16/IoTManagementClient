package org.apache.servicemix.examples.cxf.model;

public class CPU {
	String vendor;
	int clock;
	int TotalCores;
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
