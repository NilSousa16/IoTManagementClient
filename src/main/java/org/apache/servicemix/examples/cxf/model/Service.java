package org.apache.servicemix.examples.cxf.model;

import javax.xml.bind.annotation.XmlRootElement;

// Class that reflects the serviceIoT
@XmlRootElement(name = "Service")
public class Service {

	private String nameService;
	private String id;

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
