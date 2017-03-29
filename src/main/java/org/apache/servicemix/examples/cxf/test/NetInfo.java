package org.apache.servicemix.examples.cxf.test;


import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Shell;
import org.hyperic.sigar.cmd.SigarCommandBase;

import kamon.sigar.SigarProvisioner;

/**
 * Display network info.
 */
public class NetInfo extends SigarCommandBase {

    public NetInfo(Shell shell) {
        super(shell);
    }

    public NetInfo() {    	
        super();   
    }

    public String getUsageShort() {
        return "Display network info";
    }

    public void output(String[] args) throws SigarException {
        NetInterfaceConfig config = this.sigar.getNetInterfaceConfig(null);
        println("primary interface....." +
                config.getName());

        println("primary ip address...." +
                config.getAddress());

        println("primary mac address..." +
                config.getHwaddr());

        println("primary netmask......." +
                config.getNetmask());

        org.hyperic.sigar.NetInfo info =
            this.sigar.getNetInfo();

        println("host name............." +
                info.getHostName());

        println("domain name..........." +
                info.getDomainName());

        println("default gateway......." +
                info.getDefaultGateway() +
                " (" + info.getDefaultGateway() + ")");

        println("primary dns..........." +
                info.getPrimaryDns());

        println("secondary dns........." +
                info.getSecondaryDns());
    }

    public static void main(String[] args) throws Exception {
    	SigarProvisioner.provision();
        new NetInfo().processCommand(args);
    }
}