package org.apache.servicemix.examples.cxf.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.servicemix.examples.cxf.info.ServiceInfo;
import org.apache.servicemix.examples.cxf.model.Service;

@Path("/serviceservice/")
public class ServiceService {
	
		private Service service;
		private ServiceInfo serviceInfo;

		public ServiceService() {
			System.out.println("Service monitoring started.");		
		}

		@GET
		@Path("/service/sv/")
		@Produces("application/json")
		public Service getService() {
			
			service = new Service();
			
			serviceInfo = new ServiceInfo();

			serviceInfo.listNames();
			
			return service;
		}
		
		// >>>>>>>>METHODS FOR CATCHING INFORMATION<<<<<<<<

}
