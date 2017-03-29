package org.apache.servicemix.examples.cxf.test;

import java.io.IOException;

import org.apache.servicemix.examples.cxf.model.Gateway;
import org.apache.servicemix.examples.cxf.service.GatewayService;

public class TestInfra {

	public static void main(String args[]) throws IOException {

		GatewayService gatewayService = new GatewayService();
		Gateway gateway;

		gateway = gatewayService.getGateway();

		System.out.println("Gateway IP: " + gateway.getIp());
		System.out.println("Gateway IP: " + gateway.getMac());

		System.out.println("###################");
		
	}
}
