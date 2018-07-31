package com.abhishek.weather.controller;

import org.json.simple.JSONObject;
import org.json.JSONException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.abhishek.weather.connection.CreateConnection;
import com.abhishek.weather.coordinates.CustomJsonParser;

@RestController
@RequestMapping("/dashboard")
public class WeatherController {
	
	@RequestMapping(value = "/getCurrentWeatherByCityCode", method = RequestMethod.GET)
	public ResponseEntity getCurrentWeatherByCityCode(@RequestParam(value="pincode",required=false) String pincode) throws JSONException{
		
		try {
			
			if(pincode == null) {
				throw new Exception();
			}
			    		
			String cityResponse = CreateConnection.sendGetCityDetails(pincode);
			
			CustomJsonParser.setCoordinates(cityResponse);
			
			String lat = CustomJsonParser.getLatitude();
			String lng = CustomJsonParser.getLongitude();
			
			String cityClimate = CreateConnection.sendGetCityClimate(lat,lng);
			
			JSONObject currentClimate = CustomJsonParser.getClimateObject(cityClimate);
			
			
			RestTemplate restTemplate = new RestTemplate();
		    System.out.println("Pincode is "+pincode);
		    System.out.println(currentClimate);
		    
		    //String weatherResult = restTemplate.getForObject(currentClimate.toString(), String.class);
		    
			return new ResponseEntity<>(currentClimate, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getCurrentWeatherByCityName", method = RequestMethod.GET)
	public ResponseEntity getCurrentWeatherByCityName(@RequestParam(value="cityName",required=false) String cityName) throws JSONException{
		
	try {
		
		if(cityName == null) {
			throw new Exception();
		}
		    		
		String cityResponse = CreateConnection.sendGetCityDetails(cityName);
		
		CustomJsonParser.setCoordinates(cityResponse);
		
		String lat = CustomJsonParser.getLatitude();
		String lng = CustomJsonParser.getLongitude();
		
		String cityClimate = CreateConnection.sendGetCityClimate(lat,lng);
		
		JSONObject currentClimate = CustomJsonParser.getClimateObject(cityClimate);
		
		
		RestTemplate restTemplate = new RestTemplate();
	    System.out.println("City Name is "+cityName);
	    System.out.println(currentClimate);
	    
	    //String weatherResult = restTemplate.getForObject(currentClimate.toString(), String.class);
	    
		return new ResponseEntity<>(currentClimate, HttpStatus.OK);
		
	} catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
	}
	}
}
