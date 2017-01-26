package org.apache.servicemix.examples.cxf.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/gatewayservice/")
public class GatewayService {
	private Gateway gateway;

	public GatewayService() {
		System.out.println("Gateway monitoring started.");
	}
	
	@GET
	@Path("/gateway/gt/")
	@Produces("application/json")
	public Gateway getGateway() {
		gateway = new Gateway();
		Gateway info = statusMemory();
		
		gateway.setFreeMemory(info.getFreeMemory());
		gateway.setUsedMemory(info.getUsedMemory());
		gateway.setIp(this.recoverIp());
		gateway.setMac(this.recoverMac());
		
		return gateway;
	}
	
	//retorna a qtd de memória usada e memória livre
	private Gateway statusMemory() {
		Runtime run = Runtime.getRuntime();
		Process proc = null;
		Map<String, String> result = new HashMap<String, String>();
		Gateway g = new Gateway();

		try {
			String command = "free -m -t";

			proc = run.exec(command);

			result.put("input", inputStreamToString(proc.getInputStream()));
			// memória usada
			g.setUsedMemory(Integer.parseInt(result.toString().substring(104, 116).trim()));
			// memória livre
			g.setFreeMemory(Integer.parseInt(result.toString().substring(116, 126).trim()));
			return g;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//retorna a qtd de processamento utilizado e livre
	private Gateway statusProcessor(){
		//problema com o comando TOP para retornar o uso do processador
		return null;
	}
	
	//retorna as informações do gateway como descrição, fabricante, tipo de alimentação etc
	private String infoGateway(){
		//A desenvolver
		return null;
	}
	
	//recuperar endereço Ip
	private String recoverIp(){
		Runtime run = Runtime.getRuntime();
		Process proc = null;
		Map<String, String> result = new HashMap<String, String>();
		Gateway g = new Gateway();

		try {
			String command = "ifconfig";

			proc = run.exec(command);

			result.put("input", inputStreamToString(proc.getInputStream()));
			// valor do endereço ip do gateway						
			return result.toString().substring(94,107).trim();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//recuperar endereço Mac
	private String recoverMac(){
		Runtime run = Runtime.getRuntime();
		Process proc = null;
		Map<String, String> result = new HashMap<String, String>();
		Gateway g = new Gateway();

		try {
			String command = "ifconfig";

			proc = run.exec(command);

			result.put("input", inputStreamToString(proc.getInputStream()));
			// valor do endereço mac do gateway						
			return result.toString().substring(52,80).trim();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//<<<<<<<<<<<<<<<<<<<<<<Método auxiliar para o método statusMemory>>>>>>>>>>>>>>>>>>>>>>
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
