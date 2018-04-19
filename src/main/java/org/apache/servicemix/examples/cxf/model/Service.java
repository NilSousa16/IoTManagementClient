package org.apache.servicemix.examples.cxf.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class that describes a service.
 *
 * @author Nilson Rodrigues Sousa
 */
@XmlRootElement(name = "Service")
public class Service {

	private long id;

	/* Describes the name of the service */
	private String nameService;

	/* Describes the bundle information provided by the service */
	private Bundler bundlerProvide;

	/* Describes information about bundles that use the service */
	private List<Bundler> listUsesBundles = new ArrayList<Bundler>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public Bundler getBundlerProvide() {
		return bundlerProvide;
	}

	public void setBundlerProvide(Bundler bundlerProvide) {
		this.bundlerProvide = bundlerProvide;
	}

	public List<Bundler> getListUsesBundles() {
		return listUsesBundles;
	}

	public void setListUsesBundles(List<Bundler> listUsesBundles) {
		this.listUsesBundles = listUsesBundles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bundlerProvide == null) ? 0 : bundlerProvide.hashCode());
		result = prime * result + ((listUsesBundles == null) ? 0 : listUsesBundles.hashCode());
		result = prime * result + ((nameService == null) ? 0 : nameService.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (bundlerProvide == null) {
			if (other.bundlerProvide != null)
				return false;
		} else if (!bundlerProvide.equals(other.bundlerProvide))
			return false;
		if (listUsesBundles == null) {
			if (other.listUsesBundles != null)
				return false;
		} else if (!listUsesBundles.equals(other.listUsesBundles))
			return false;
		if (nameService == null) {
			if (other.nameService != null)
				return false;
		} else if (!nameService.equals(other.nameService))
			return false;
		return true;
	}

}
