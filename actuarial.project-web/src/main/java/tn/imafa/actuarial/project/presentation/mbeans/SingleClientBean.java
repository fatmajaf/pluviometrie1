package tn.imafa.actuarial.project.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import tn.imafa.actuarial.project.persistence.Client;
import tn.imafa.actuarial.project.services.ClientService;
/**
*
* @author Fatma Jaafar
*/
@ManagedBean
@SessionScoped
public class SingleClientBean {
	private Client client;
	
	private double precipitation=0;
	private String notification="";
	
	private String srcweatherwidget;
	private String aweatherwidget;
	
	private String weathericon;
	private String weatherDescription;
	
	private List<Double> precipssupseuil;
	private List<String> dateprecipssupseuil;
	
	//calc
	private double ca;
	private double cf;
	private double result;
	private String msgval;
	private double resultca;
	
	@ManagedProperty(value="#{loginBean}")
LoginBean loginBean;
	private int nbjourspluviometrie;
	private int moypluviometrie;
	private String notifann;
	@EJB
	ClientService service;
	
    
	public String populateProfileClient(Client client) throws JSONException{
		
		this.client= new Client();
		this.client=client;
		System.out.println("le clientt courant "+this.client);
		JSONObject obj = null;
		JSONObject objf=null;
		
			obj=WeatherData.getData(this.client.getVille(), "current");
			if (!obj.isNull("rain")){
				try {
					precipitation=obj.getJSONObject("rain").getDouble("3h");
					System.out.println(precipitation);
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
				if (client.getSeuil()<precipitation){
					notification="Precipitation exceeds the client threshold ! ";
				}
				
			}
			
			weatherDescription= obj.getJSONArray("weather").getJSONObject(0).getString("description");
			weathericon="http://openweathermap.org/img/w/"+obj.getJSONArray("weather").getJSONObject(0).getString("icon")+".png";
			System.out.println(weathericon);
			srcweatherwidget="https://www.meteoblue.com/en/weather/widget/three/"+client.getVille()+"_france?geoloc=fixed&amp;nocurrent=0&amp;noforecast=0&amp;days=7&amp;tempunit=CELSIUS&amp;windunit=KILOMETER_PER_HOUR&amp;layout=image";
			aweatherwidget="https://www.meteoblue.com/en/weather/forecast/week/"+client.getVille()+"_france?utm_source=weather_widget&amp;utm_medium=linkus&amp;utm_content=three&amp;utm_campaign=Weather%2BWidget";
		
			
			precipssupseuil= new ArrayList();
			dateprecipssupseuil= new ArrayList();
			objf=WeatherData.getData(this.client.getVille(), "forecast");
			JSONArray array=objf.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				/*if(!array.getJSONObject(i).getJSONObject("rain").isNull("3h")){
					System.out.print(array.getJSONObject(i).getJSONObject("rain").getString("3h"));
				    System.out.println(array.getJSONObject(i).getString("dt_txt"));
				    
				    if (this.client.getSeuil()< Double.valueOf(array.getJSONObject(i).getJSONObject("rain").getString("3h"))){
				    	precipssupseuil.add(Double.valueOf(array.getJSONObject(i).getJSONObject("rain").getString("3h")));
				    	dateprecipssupseuil.add(array.getJSONObject(i).getString("dt_txt"));
				    }
				    }*/
				if (array.getJSONObject(i).has("rain")==true){
					if(array.getJSONObject(i).getJSONObject("rain").has("3h"))
					//System.out.print(array.getJSONObject(i).isNull("rain"));
					{
						System.out.print(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0"));
					    System.out.println(array.getJSONObject(i).getString("dt_txt"));
				    
					if (this.client.getSeuil()< Double.valueOf(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0"))){
				    	precipssupseuil.add(Double.valueOf(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0")));
				    	dateprecipssupseuil.add(array.getJSONObject(i).getString("dt_txt"));
				    }}
				}
				
			}
			
			System.out.println("size precip in bean : "+precipssupseuil.size());
			
			
			
			nbjourspluviometrie=csvreader.getCitiespluvio(this.client.getVille(), "nb");
			
			moypluviometrie=csvreader.getCitiespluvio(this.client.getVille(), "moy");
			if (client.getSeuil()<moypluviometrie){
				notifann="Precipitation average in "+this.client.getVille()+"exceeds the client threshold ! ";
			}
			
			
			return "/pages/admin/clientprofile?faces-redirect=true";
	}
	
	
	
	
	
public String populateProfileClientclient() throws JSONException{
		
		this.client= new Client();
		this.client=loginBean.getClientco();
		System.out.println("le clientt courant "+this.client);
		JSONObject obj = null;
		JSONObject objf=null;
		
			obj=WeatherData.getData(this.client.getVille(), "current");
			if (!obj.isNull("rain")){
				try {
					precipitation=obj.getJSONObject("rain").getDouble("3h");
					System.out.println(precipitation);
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
				if (client.getSeuil()<precipitation){
					notification="Precipitation exceeds the client threshold ! ";
				}
				
			}
			
			weatherDescription= obj.getJSONArray("weather").getJSONObject(0).getString("description");
			weathericon="http://openweathermap.org/img/w/"+obj.getJSONArray("weather").getJSONObject(0).getString("icon")+".png";
			System.out.println(weathericon);
			srcweatherwidget="https://www.meteoblue.com/en/weather/widget/three/"+client.getVille()+"_france?geoloc=fixed&amp;nocurrent=0&amp;noforecast=0&amp;days=7&amp;tempunit=CELSIUS&amp;windunit=KILOMETER_PER_HOUR&amp;layout=image";
			aweatherwidget="https://www.meteoblue.com/en/weather/forecast/week/"+client.getVille()+"_france?utm_source=weather_widget&amp;utm_medium=linkus&amp;utm_content=three&amp;utm_campaign=Weather%2BWidget";
		
			
			precipssupseuil= new ArrayList();
			dateprecipssupseuil= new ArrayList();
			objf=WeatherData.getData(this.client.getVille(), "forecast");
			JSONArray array=objf.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				/*if(!array.getJSONObject(i).getJSONObject("rain").isNull("3h")){
					System.out.print(array.getJSONObject(i).getJSONObject("rain").getString("3h"));
				    System.out.println(array.getJSONObject(i).getString("dt_txt"));
				    
				    if (this.client.getSeuil()< Double.valueOf(array.getJSONObject(i).getJSONObject("rain").getString("3h"))){
				    	precipssupseuil.add(Double.valueOf(array.getJSONObject(i).getJSONObject("rain").getString("3h")));
				    	dateprecipssupseuil.add(array.getJSONObject(i).getString("dt_txt"));
				    }
				    }*/
				if (array.getJSONObject(i).has("rain")==true){
					if(array.getJSONObject(i).getJSONObject("rain").has("3h"))
					//System.out.print(array.getJSONObject(i).isNull("rain"));
					{
						System.out.print(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0"));
					    System.out.println(array.getJSONObject(i).getString("dt_txt"));
				    
					if (this.client.getSeuil()< Double.valueOf(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0"))){
				    	precipssupseuil.add(Double.valueOf(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0")));
				    	dateprecipssupseuil.add(array.getJSONObject(i).getString("dt_txt"));
				    }}
				}
				
			}
			
			System.out.println("size precip in bean : "+precipssupseuil.size());
			
			
			
			nbjourspluviometrie=csvreader.getCitiespluvio(this.client.getVille(), "nb");
			
			moypluviometrie=csvreader.getCitiespluvio(this.client.getVille(), "moy");
			if (client.getSeuil()<moypluviometrie){
				notifann="Precipitation average in "+this.client.getVille()+"exceeds the client threshold ! ";
			}
			
			
			return "/pages/client/profilclient?faces-redirect=true";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public void changesmanagement(){
	if(ca<cf){
		msgval="Turnover must be superior than the fixed price !";
		resultca=0;
		result=0;
	}
	else 
	{
		msgval="";
	}
	if(precipitation==0){
		resultca=ca;
		result=ca-cf;
	}
	else{
	if(precipitation > this.client.getSeuil()){
		
		result=-cf;
		resultca=0;
	}
	if(precipitation < this.client.getSeuil()){
		result=((this.client.getSeuil()-precipitation)/this.client.getSeuil())*(ca-cf);
	    resultca=((this.client.getSeuil()-precipitation)/this.client.getSeuil())*ca;
	}
	}
	
	System.out.println("precip "+precipitation + "/ seuil "+this.client.getSeuil());
	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:foo");
	
}
	public Client getClient() {
		return client;
	}

	public double getResultca() {
		return resultca;
	}
	public void setResultca(double resultca) {
		this.resultca = resultca;
	}
	public String getMsgval() {
		return msgval;
	}
	public void setMsgval(String msgval) {
		this.msgval = msgval;
	}
	public List<Double> getPrecipssupseuil() {
		return precipssupseuil;
	}

	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public double getCa() {
		return ca;
	}

	public void setCa(double ca) {
		this.ca = ca;
	}

	public double getCf() {
		return cf;
	}

	public void setCf(double cf) {
		this.cf = cf;
	}

	public void setPrecipssupseuil(List<Double> precipssupseuil) {
		this.precipssupseuil = precipssupseuil;
	}

	public List<String> getDateprecipssupseuil() {
		return dateprecipssupseuil;
	}

	public void setDateprecipssupseuil(List<String> dateprecipssupseuil) {
		this.dateprecipssupseuil = dateprecipssupseuil;
	}

	public String getSrcweatherwidget() {
		return srcweatherwidget;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public String getWeathericon() {
		return weathericon;
	}

	public void setWeathericon(String weathericon) {
		this.weathericon = weathericon;
	}

	public void setSrcweatherwidget(String srcweatherwidget) {
		this.srcweatherwidget = srcweatherwidget;
	}

	public String getNotification() {
		return notification;
	}

	public String getAweatherwidget() {
		return aweatherwidget;
	}

	public void setAweatherwidget(String aweatherwidget) {
		this.aweatherwidget = aweatherwidget;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	

	public String getNotifann() {
		return notifann;
	}
	public void setNotifann(String notifann) {
		this.notifann = notifann;
	}
	public double getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}

	




	public LoginBean getLoginBean() {
		return loginBean;
	}





	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}





	public ClientService getService() {
		return service;
	}

	public void setService(ClientService service) {
		this.service = service;
	}
	public int getNbjourspluviometrie() {
		return nbjourspluviometrie;
	}
	public void setNbjourspluviometrie(int nbjourspluviometrie) {
		this.nbjourspluviometrie = nbjourspluviometrie;
	}
	public int getMoypluviometrie() {
		return moypluviometrie;
	}
	public void setMoypluviometrie(int moypluviometrie) {
		this.moypluviometrie = moypluviometrie;
	}
	
	
}
