package org.apache.servicemix.examples.cxf.test;

import java.io.IOException;
import java.util.List;

import org.apache.servicemix.examples.cxf.model.Service;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

public class AppTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		Service service = new Service();

//		service.setNameService("Discovery");
//		service.setBundlerProvide("Fot-Discovery");
//		service.getListUsesBundles().add("Fot-Balance");
//		service.getListUsesBundles().add("Fot-Controller");
//		service.getListUsesBundles().add("Fot-Semantic");
//		service.getListUsesBundles().add("Fot-Command");

		Gson gson = new Gson();

		String gatewaySend = gson.toJson(service);

		JSONObject userObject;
		
		try {
			
			userObject = new JSONObject(gatewaySend);

			String nameService = userObject.getString("nameService");

			String bundlerProvide = userObject.getString("bundlerProvide");
			
			String listUsesBundles = userObject.getString("listUsesBundles");

			System.out.println("NameService: " + nameService);
			System.out.println("BundlerProvide: " + bundlerProvide);
			System.out.println("ListUsesBundles: " + listUsesBundles);
			
			String jsonString = listUsesBundles;
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			List<String> strings = mapper.readValue(jsonString, List.class);
			
			for(String s : strings) {
				System.out.println(s);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
