package com.abhishek.weather.service;

import org.json.simple.JSONObject;

import com.abhishek.weather.model.Weather;

public interface WeatherService {
	
	public boolean validateInsertion(JSONObject newVal,String info);
	
	public void saveObject(Weather latest);
	
	public Weather getObjectByCityInfo(String info);
	
	public Weather createWeatherObject(JSONObject newVal,String info);
}
