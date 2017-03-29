package org.apache.servicemix.examples.cxf.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LevelBattery {

	public static void main(String[] args) {
		Map<String, String> result;
		String reportBattery;
		int ind = 2;
		while (true) {
			result = commandTerminal("upower -i /org/freedesktop/UPower/devices/battery_BAT" + ind);
			reportBattery = result.toString();
			int position = reportBattery.indexOf("native-path");
			String line = reportBattery.substring(position, position + 30);
			if (line.indexOf("null") == -1) {
				break;
			} else {
				ind++;
			}
			if (ind == 15) {
				break;
			}
			System.out.println("$");
		}

		System.out.println(result.toString());
		System.out.println("\n############################################\n");

		// System.out.println(result.toString().substring(500,750));
		// System.out.println(result.toString().substring(500,700).length());

		// reportBattery = result.toString();
		int position = reportBattery.indexOf("percentage");
		String subString = reportBattery.substring(position, position + 27);

		int pos = subString.indexOf("%");
		System.out.println(">>>>>>>>>>>" + subString.substring(pos - 3, pos).trim());

	}

	// >>>>>>>>MÉTODOS AUXILIARES<<<<<<<<

	// retorna o comando executado
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

	// Método auxiliar para o método commandTerminal
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
