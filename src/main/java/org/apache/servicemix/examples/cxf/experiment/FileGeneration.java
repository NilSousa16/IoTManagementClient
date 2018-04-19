package org.apache.servicemix.examples.cxf.experiment;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.servicemix.examples.cxf.util.InfraUtil;

import com.sun.management.OperatingSystemMXBean;

public class FileGeneration {

	public void generationLogMemProcessor() {

		/* file name generation */
		Date data = new Date();
		// String filePath = "/home/nilson/Documents/LOG-" +
		// data.toInstant().toString() + ".txt";
		
		System.out.println("\n--------------------------\n");
		System.out.println("MAC: " + InfraUtil.getMacAddress() + "\n IP: " + InfraUtil.getIpMachine());
		System.out.println("\n--------------------------\n");
		
		String filePath = "/home/gateway/experimentos/experimento-" + data.toInstant().toString() + ".txt";
		try {
			FileWriter arq = new FileWriter(filePath);
			PrintWriter gravarArq = new PrintWriter(arq);

			/* capture information memory and CPU */
			Runtime run = Runtime.getRuntime();
			Process proc = null;
			Map<String, String> result = new HashMap<String, String>();
			try {
				
				/* capture info memory */
				String command = "free\n";
				proc = run.exec(command);
				result.put("", inputStreamToString(proc.getInputStream()));
				/* total used memory */
				//gravarArq.printf("Used memory: %s Bytes %n", result.toString().substring(107, 122).trim());
				gravarArq.printf("%s %n", result.toString());
				
				/* capture info CPU */
				result = new HashMap<String, String>();
				command = "mpstat 1 1";
				proc = run.exec(command);
				result.put("", inputStreamToString(proc.getInputStream()));
				//gravarArq.printf("Used CPU: %s", result.toString().substring(192, 200).trim());
				gravarArq.printf("%s %n", result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			arq.close();
			System.out.println(">>>>>Information saved successfully - " + data.toInstant().toString());
		} catch (IOException e) {
			System.out.println("Unable to save file.");
			e.printStackTrace();
		}

	}

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
