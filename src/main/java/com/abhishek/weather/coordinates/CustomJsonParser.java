package com.abhishek.weather.coordinates;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class CustomJsonParser {

	public String lat;
	
	public String lng;
	
	public boolean setCoordinates(String jsonString) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		JSONArray array = (JSONArray)json.get("results");
		
		if(array.size()>0)
		{
			JSONObject obj2 =(JSONObject)array.get(0);
			
			obj2 = (JSONObject)obj2.get("geometry");
			obj2 = (JSONObject)obj2.get("location");

			System.out.println(obj2.get("lat")+" , "+obj2.get("lng"));
			
			lat = (String)obj2.get("lat").toString();
			lng = (String)obj2.get("lng").toString();
			
			return true;
		}
		else
		{
			System.out.println("City not Found");
			return false;
		}
		
	}
	
	public JSONObject getCurrentClimateObject(String jsonString) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		json = (JSONObject)json.get("currently");
		
		return json;
	}
	
	public JSONObject getHourlyClimateObject(String jsonString) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		json = (JSONObject)json.get("hourly");
		
		return json;
	}
	
	public JSONObject getDailyClimateObject(String jsonString) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		json = (JSONObject)json.get("daily");
		
		return json;
	}
	
	public String getLatitude() {
		return lat;
	}

	public String getLongitude() {
		return lng;
	}
	

}
