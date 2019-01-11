package tn.imafa.actuarial.project.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
/*import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
*/
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class test3 {

	public static void main(String[] args) throws JSONException {
		/*Client client = ClientBuilder.newClient();
		   WebTarget target= client.target("http://api.openweathermap.org/data/2.5/weather");
		   WebTarget calcul= target.queryParam("q","Nice" ).queryParam("appid", "902f7bf655a79bb9e4835a3e5190914b");
		   System.out.println(calcul.toString());
		   Response response=calcul.request().get();*/
		   //String result=response.readEntity(String.class);
		 //  System.out.println(response.getMediaType().toString());
		   //JSONObject result=response.readEntity(JSONObject.class);
		  // WeatherStatusResponse wsr= new WeatherStatusResponse(result);
		  // System.out.println("result : "+result);
		  // System.out.println(wsr.getWeatherStatus().get(0));
		   
		  /* Client client = Client.create();
			WebResource target =
			client.resource("http://api.openweathermap.org/data/2.5/weather?q=Nice&appid=902f7bf655a79bb9e4835a3e5190914b");
			ClientResponse response =
			target.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 200) {
			 throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
			}
			String output = response.getEntity(String.class);
			JSONObject obj = new JSONObject(output);
			
			Double n = obj.getDouble("dt");
			//System.out.println(n);
			String pr= obj.getJSONObject("rain").getString("3h");
			System.out.println(pr);*/
		
		
		
		
		
		
		
		
		Client client = Client.create();
		WebResource target =
		client.resource("http://api.openweathermap.org/data/2.5/forecast?q=Paris&appid=902f7bf655a79bb9e4835a3e5190914b");
		ClientResponse response =
		target.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
		 throw new RuntimeException("Failed : HTTP error code : "
		+ response.getStatus());
		}
		String output = response.getEntity(String.class);
		JSONObject obj = new JSONObject(output);
		System.out.println(obj);
		//obj.getJSONArray("list").getJSONObject(1).getJSONObject("rain").getString("3h");
		
		//System.out.println(obj.getJSONArray("list").getJSONObject(20).getJSONObject("rain").getDouble("3h"));
		
			//System.out.println(obj.getJSONArray("list").getJSONObject(2).getJSONObject("rain").getDouble("3h"));
		//System.out.println(obj.getJSONArray("list").getJSONObject(2).isNull("rain"));
		//System.out.println(obj.getJSONArray("list"));
		//System.out.println(obj.getJSONArray("list").getJSONObject(0).getJSONObject("rain").getDouble("3h"));
		//System.out.println(obj.getJSONArray("weather").getJSONObject(0).getString("icon"));
		System.out.println(obj.getJSONArray("list"));
		JSONArray array=obj.getJSONArray("list");
		for (int i = 0; i < array.length(); i++) {
			System.out.print(array.getJSONObject(i).isNull("rain"));
			if (array.getJSONObject(i).has("rain")==true){
				if(array.getJSONObject(i).getJSONObject("rain").has("3h"))
				//System.out.print(array.getJSONObject(i).isNull("rain"));
				{
					System.out.print(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0"));
				    System.out.println(array.getJSONObject(i).getString("dt_txt"));
				
				}
			    }
			
		}
		/*if (!obj.isNull("rain")){
			System.out.println(obj.getJSONObject("rain").getDouble("3h"));
		}*/
	   response.close();
		   response.close();

	}

}
