package org.apache.servicemix.examples.cxf.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

/**
 * Class with utility methods
 * 
 * @author Nilson Rodrigues Sousa
 */
public class InfraUtil {

	/**
	 * Method returns the ip address of the local machine
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - Returns ip
	 */
	public static String getIpMachine() {
		String ipAddress = null;
		Enumeration<NetworkInterface> net = null;
		try {
			net = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

		while (net.hasMoreElements()) {
			NetworkInterface element = net.nextElement();
			Enumeration<InetAddress> addresses = element.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress ip = addresses.nextElement();

				if (ip.isSiteLocalAddress()) {
					ipAddress = ip.getHostAddress();
				}
			}
		}
		return ipAddress;
	}

	/**
	 * Method returns the mac address of the local machine
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - Returns mac
	 */
	public static String getMacAddress() {
		String enderecoMac = "";
		int i = 1;

		try {
			Enumeration<NetworkInterface> enumNetWork = NetworkInterface.getNetworkInterfaces();
			while (enumNetWork.hasMoreElements()) {
				NetworkInterface networkInterface = enumNetWork.nextElement();
				if (networkInterface.getName().startsWith("eth") || networkInterface.getName().startsWith("eth0")
						|| networkInterface.getName().startsWith("wlp3s0")
						|| networkInterface.getName().startsWith("enp2s0")) {
					Enumeration<InetAddress> ds = networkInterface.getInetAddresses();
					while (ds.hasMoreElements()) {
						if (i != 2) {
							for (int o = 0; o < networkInterface.getHardwareAddress().length; o++) {
								enderecoMac += (String.format("%02X%s", networkInterface.getHardwareAddress()[o],
										(o < networkInterface.getHardwareAddress().length - 1) ? "-" : ""));
							}
							break;
						}
						i++;
					}
					break;
				}
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}

		return enderecoMac;

	}

	/**
	 * Method returns date and time 
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - Returns in the format dd/MM/yyyy h:mm - PM
	 */
	public static String getDateHour() {
		String data = "dd/MM/yyyy";
		String hora = "h:mm - a";
		String data1, hora1;
		java.util.Date agora = new java.util.Date();
		SimpleDateFormat formata = new SimpleDateFormat(data);
		data1 = formata.format(agora);
		formata = new SimpleDateFormat(hora);
		hora1 = formata.format(agora);
		return data1 + " " + hora1;
	}

}
