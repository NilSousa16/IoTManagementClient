package org.apache.servicemix.examples.cxf.command;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;
import org.apache.servicemix.examples.cxf.info.ServiceInfo;

@Command(scope = "ws", name = "gatewayServiceInformation")
public class CommandServiceInfo implements Action {

	// referenciar via CDI ou blueprint
	ServiceInfo serviceInfo = new ServiceInfo();

	@Override
	public Object execute(CommandSession session) throws Exception {
		System.out.println("Funcionado");
		return null;
	}

}
