package org.apache.servicemix.examples.cxf.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.servicemix.examples.cxf.model.Bundler;
import org.apache.servicemix.examples.cxf.model.Service;

import com.google.gson.Gson;

public class JSONTest {
	
	public static void main (String args []) {
		
		Service service = new Service();
		
		
		service.setNameService("org.osgi.service.jdbc.DataSourceFactory");
		
		
		Bundler bundlerProvide = new Bundler();
		bundlerProvide.setName("OPS4J Pax JDBC Pooling Support using Commons-DBCP2");
		bundlerProvide.setVersion("1.5.3");
		service.setBundlerProvide(bundlerProvide);
		
		List<Bundler> listUsesBundles = new ArrayList<Bundler>();
		bundlerProvide = new Bundler();
		bundlerProvide.setName("OPS4J Pax Web - Runtime");
		bundlerProvide.setVersion("4.7.0");
		listUsesBundles.add(bundlerProvide);
		
		bundlerProvide = new Bundler();
		bundlerProvide.setName("Apache Aries Transaction Blueprint");
		bundlerProvide.setVersion("1.1.2");
		listUsesBundles.add(bundlerProvide);		
		
		service.setListUsesBundles(listUsesBundles);
		
		Gson gson = new Gson();
		 
		// converte objetos Java para JSON e retorna JSON como String
		String json = gson.toJson(service);
				
		System.out.println(json);
		
	}

}
