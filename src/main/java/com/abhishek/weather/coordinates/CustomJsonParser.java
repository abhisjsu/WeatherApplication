package com.abhishek.weather.coordinates;


import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.abhishek.weather.connection.CreateConnection;

public class CustomJsonParser {

	public  static String lat;
	
	public static String lng;
	
	public static void setCoordinates(String jsonString) throws ParseException
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
		}
		else
		{
			System.out.println("City not Found");
			System.exit(0);
		}
		
	}
	
	public static JSONObject getCurrentClimateObject(String jsonString) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		json = (JSONObject)json.get("currently");
		
		return json;
	}
	
	public static JSONObject getHourlyClimateObject(String jsonString) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		json = (JSONObject)json.get("hourly");
		
		return json;
	}
	
	public static JSONObject getDailyClimateObject(String jsonString) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		
		json = (JSONObject)json.get("daily");
		
		return json;
	}
	
	public static String getLatitude() {
		return lat;
	}

	public static String getLongitude() {
		return lng;
	}
	
	public static void main(String argv[]) throws IOException, ParseException
	{
		
		String cityResponse = CreateConnection.sendGetCityDetails("95014");
		
		CustomJsonParser.setCoordinates(cityResponse);
		
		String lat = CustomJsonParser.getLatitude();
		String lng = CustomJsonParser.getLongitude();
		
		String cityClimate = CreateConnection.sendGetCityClimate(lat,lng);
		
		System.out.println(CustomJsonParser.getCurrentClimateObject(cityClimate));
		System.out.println(CustomJsonParser.getHourlyClimateObject(cityClimate));
		System.out.println(CustomJsonParser.getDailyClimateObject(cityClimate));
	}


}
