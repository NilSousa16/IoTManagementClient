package org.apache.servicemix.examples.cxf.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarLoader;

import kamon.sigar.SigarProvisioner;

public class CPUInfo2 {

	public static void main(String[] args) throws Exception {

		SigarProvisioner.provision();
		Sigar sigar = new Sigar();

		CpuPerc cpu = sigar.getCpuPerc();

		System.out.println(commandTerminal("top").toString());

		//uso de cpu usar
		System.out.println(">>>>>>>>>>>>S: "+(cpu.getCombined()*100)+"\t");
		// System.out.println("\n#####################################\n");
		//
		// System.out.println("User Time....." + cpu.getUser());
		// System.out.println("Sys Time......" + cpu.getSys());
		// System.out.println("Idle Time....." + cpu.getIdle());
		// System.out.println("Wait Time....." + cpu.getWait());
		// System.out.println("Nice Time....." + cpu.getNice());
		// System.out.println("Combined......" + cpu.getCombined());
		// System.out.println("Irq Time......" + cpu.getIrq());
		// System.out.println("\n#####################################\n");
		// System.out.println("User Time....." + cpu.getUser());
		// System.out.println("Sys Time......" + cpu.getSys());
		// System.out.println("Idle Time....." + cpu.getIdle());
		// System.out.println("Wait Time....." + cpu.getWait());
		// System.out.println("Nice Time....." + cpu.getNice());
		// System.out.println("Combined......" + cpu.getCombined());
		// System.out.println("Irq Time......" + cpu.getIrq());
		// System.out.println("\n#####################################\n");
		 System.out.println(sigar.getCpuPerc());
		// CPUInfo2.impress();
	}

	public static void impress() throws Exception {
		SigarProvisioner.provision();
		Sigar sigar = new Sigar();

		System.out.println("Totals........");
		// System.out.println(sigar.getCpuPerc());
		System.out.println("\n#####################################\n");
		System.out.println("User Time....." + CpuPerc.format(sigar.getCpuPerc().getUser()));
		System.out.println("Sys Time......" + sigar.getCpuPerc().getSys());
		System.out.println("Idle Time....." + CpuPerc.format(sigar.getCpuPerc().getIdle()));
		System.out.println("Wait Time....." + CpuPerc.format(sigar.getCpuPerc().getWait()));
		System.out.println("Nice Time....." + CpuPerc.format(sigar.getCpuPerc().getNice()));
		System.out.println("Combined......" + CpuPerc.format(sigar.getCpuPerc().getCombined()));
		System.out.println("Irq Time......" + CpuPerc.format(sigar.getCpuPerc().getIrq()));
		if (SigarLoader.IS_LINUX) {
			System.out.println("SoftIrq Time.." + sigar.getCpuPerc().getSoftIrq());
			System.out.println("Stolen Time...." + sigar.getCpuPerc().getStolen());
		}
		System.out.println("");
	}

	// método para executar comando linux
	private static Map<String, String> commandTerminal(String stringCommand) {
		Runtime run = Runtime.getRuntime();
		Process proc = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			String command = stringCommand;
			proc = run.exec(command);
			result.put("input", inputStreamToString(proc.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// método auxiliar para o método commandTerminal
	private static String inputStreamToString(InputStream isr) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(isr));
			StringBuilder sb = new StringBuilder();
			String s = null;
			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}
			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
