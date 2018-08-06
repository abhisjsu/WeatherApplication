package com.abhishek.weather.controller;

import org.json.simple.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;

import com.abhishek.weather.connection.CityDetailService;
import com.abhishek.weather.coordinates.CustomJsonParser;
import com.abhishek.weather.service.WeatherService;

@RestController
@RequestMapping("/dashboard")
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	
	@Autowired
	CityDetailService cityService;
	
	@Autowired
	CustomJsonParser jsonParser;

	@GetMapping(value = "/getCurrentWeatherByCityCode/current")
	public JSONObject getCurrentWeatherByCityCode(@RequestParam(value="pincode",required=false) String pincode,ModelMap model) throws JSONException{
		
		try {
			
			if(pincode == null) {
				throw new Exception();
			}
			    		
			String cityResponse = cityService.sendGetCityDetails(pincode);
			
			jsonParser.setCoordinates(cityResponse);
			
			String lat = jsonParser.getLatitude();
			String lng = jsonParser.getLongitude();
			
			String cityClimate = cityService.sendGetCityClimate(lat,lng);
			
			JSONObject currentClimate = jsonParser.getCurrentClimateObject(cityClimate);

		    this.weatherService.validateInsertion(currentClimate, pincode);
		    
		    System.out.println("Obtained from Database for city : "+pincode);
		    System.out.println(this.weatherService.getObjectByCityInfo(pincode));
		 
		    
		    return currentClimate;
			
		} catch(Exception e) {
			e.printStackTrace();

			JSONObject output = new JSONObject();
			output.put("Exception", "Exception was encountered in the code");
			return output;
		}
	}
	
	@GetMapping(value = "/getCurrentWeatherByCityCode/hourly")
	public ResponseEntity getHourlyWeatherByCityCode(@RequestParam(value="pincode",required=false) String pincode) throws JSONException{
		
		try {
			
			if(pincode == null) {
				throw new Exception();
			}
			    		
			String cityResponse = cityService.sendGetCityDetails(pincode);
			
			jsonParser.setCoordinates(cityResponse);
			
			String lat = jsonParser.getLatitude();
			String lng = jsonParser.getLongitude();
			
			String cityClimate = cityService.sendGetCityClimate(lat,lng);
			
			JSONObject currentClimate = jsonParser.getHourlyClimateObject(cityClimate);

		    System.out.println("Pincode is "+pincode);
		    System.out.println(currentClimate);
		    
		    //String weatherResult = restTemplate.getForObject(currentClimate.toString(), String.class);
		    
			return new ResponseEntity<>(currentClimate, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getCurrentWeatherByCityCode/daily")
	public ResponseEntity getDailyWeatherByCityCode(@RequestParam(value="pincode",required=false) String pincode) throws JSONException{
		
		try {
			
			if(pincode == null) {
				throw new Exception();
			}
			    		
			String cityResponse = cityService.sendGetCityDetails(pincode);
			
			jsonParser.setCoordinates(cityResponse);
			
			String lat = jsonParser.getLatitude();
			String lng = jsonParser.getLongitude();
			
			String cityClimate = cityService.sendGetCityClimate(lat,lng);
			
			JSONObject currentClimate = jsonParser.getDailyClimateObject(cityClimate);
			
			
			
		    System.out.println("Pincode is "+pincode);
		    System.out.println(currentClimate);

			return new ResponseEntity<>(currentClimate, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getCurrentWeatherByCityName/current")
	public ResponseEntity getCurrentWeatherByCityName(@RequestParam(value="cityName",required=false) String cityName) throws JSONException{
		
	try {
		
		if(cityName == null) {
			throw new Exception();
		}
		    		
		String cityResponse = cityService.sendGetCityDetails(cityName);
		
		jsonParser.setCoordinates(cityResponse);
		
		String lat = jsonParser.getLatitude();
		String lng = jsonParser.getLongitude();
		
		String cityClimate = cityService.sendGetCityClimate(lat,lng);
		
		JSONObject currentClimate = jsonParser.getCurrentClimateObject(cityClimate);

	    this.weatherService.validateInsertion(currentClimate, cityName);
	    
	    System.out.println("City Name is "+cityName);
	    System.out.println(currentClimate);
	    
		return new ResponseEntity<>(currentClimate, HttpStatus.OK);
		
	} catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
	}
	}
	
	@GetMapping(value = "/getCurrentWeatherByCityName/hourly")
	public ResponseEntity getHourlyWeatherByCityName(@RequestParam(value="cityName",required=false) String cityName) throws JSONException{
		
	try {
		
		if(cityName == null) {
			throw new Exception();
		}
		    		
		String cityResponse = cityService.sendGetCityDetails(cityName);
		
		jsonParser.setCoordinates(cityResponse);
		
		String lat = jsonParser.getLatitude();
		String lng = jsonParser.getLongitude();
		
		String cityClimate = cityService.sendGetCityClimate(lat,lng);
		
		JSONObject currentClimate = jsonParser.getHourlyClimateObject(cityClimate);

	    System.out.println("City Name is "+cityName);
	    System.out.println(currentClimate);
	    
	    //String weatherResult = restTemplate.getForObject(currentClimate.toString(), String.class);
	    
		return new ResponseEntity<>(currentClimate, HttpStatus.OK);
		
	} catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
	}
	}
	
	@GetMapping(value = "/getCurrentWeatherByCityName/daily")
	public ResponseEntity getDailyWeatherByCityName(@RequestParam(value="cityName",required=false) String cityName) throws JSONException{
		
	try {
		
		if(cityName == null) {
			throw new Exception();
		}
		    		
		String cityResponse = cityService.sendGetCityDetails(cityName);
		
		jsonParser.setCoordinates(cityResponse);
		
		String lat = jsonParser.getLatitude();
		String lng = jsonParser.getLongitude();
		
		String cityClimate = cityService.sendGetCityClimate(lat,lng);
		
		JSONObject currentClimate = jsonParser.getDailyClimateObject(cityClimate);
		
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
