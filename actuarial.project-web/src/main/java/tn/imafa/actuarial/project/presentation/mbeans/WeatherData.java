package tn.imafa.actuarial.project.presentation.mbeans;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WeatherData {
	public static JSONObject getData(String city, String forcastorcurrent) throws JSONException{
		Client client = Client.create();
		String endp="http://api.openweathermap.org/data/2.5/";
		if(forcastorcurrent.toLowerCase().equals("current")){
			endp+="weather?q="+city+"&appid=902f7bf655a79bb9e4835a3e5190914b";
		}
		else 
		{	endp+="forecast?q="+city+"&appid=902f7bf655a79bb9e4835a3e5190914b"; }
		System.out.println(endp);
		WebResource target =client.resource(endp);
		ClientResponse response =
		target.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
		 throw new RuntimeException("Failed : HTTP error code : "
		+ response.getStatus());
		}
		String output = response.getEntity(String.class);
		JSONObject obj = new JSONObject(output);
		System.out.println(obj);
		return obj;
	}

}
