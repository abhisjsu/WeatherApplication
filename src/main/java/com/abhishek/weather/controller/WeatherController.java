package com.abhishek.weather.controller;

import org.json.simple.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.ui.ModelMap;

import com.abhishek.weather.connection.CreateConnection;
import com.abhishek.weather.coordinates.CustomJsonParser;
import com.abhishek.weather.model.Weather;
import com.abhishek.weather.service.WeatherService;

@RestController
@RequestMapping("/dashboard")
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;

	
	@RequestMapping(value = "/getCurrentWeatherByCityCode/current", method = RequestMethod.GET)
	public JSONObject getCurrentWeatherByCityCode(@RequestParam(value="pincode",required=false) String pincode,ModelMap model) throws JSONException{
		
		try {
			
			if(pincode == null) {
				throw new Exception();
			}
			    		
			String cityResponse = CreateConnection.sendGetCityDetails(pincode);
			
			CustomJsonParser.setCoordinates(cityResponse);
			
			String lat = CustomJsonParser.getLatitude();
			String lng = CustomJsonParser.getLongitude();
			
			String cityClimate = CreateConnection.sendGetCityClimate(lat,lng);
			
			JSONObject currentClimate = CustomJsonParser.getCurrentClimateObject(cityClimate);
		    
		    Weather weather = this.weatherService.createWeatherObject(currentClimate, pincode);

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
	
	@RequestMapping(value = "/getCurrentWeatherByCityCode/hourly", method = RequestMethod.GET)
	public ResponseEntity getHourlyWeatherByCityCode(@RequestParam(value="pincode",required=false) String pincode) throws JSONException{
		
		try {
			
			if(pincode == null) {
				throw new Exception();
			}
			    		
			String cityResponse = CreateConnection.sendGetCityDetails(pincode);
			
			CustomJsonParser.setCoordinates(cityResponse);
			
			String lat = CustomJsonParser.getLatitude();
			String lng = CustomJsonParser.getLongitude();
			
			String cityClimate = CreateConnection.sendGetCityClimate(lat,lng);
			
			JSONObject currentClimate = CustomJsonParser.getHourlyClimateObject(cityClimate);
			
			
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
	
	@RequestMapping(value = "/getCurrentWeatherByCityCode/daily", method = RequestMethod.GET)
	public ResponseEntity getDailyWeatherByCityCode(@RequestParam(value="pincode",required=false) String pincode) throws JSONException{
		
		try {
			
			if(pincode == null) {
				throw new Exception();
			}
			    		
			String cityResponse = CreateConnection.sendGetCityDetails(pincode);
			
			CustomJsonParser.setCoordinates(cityResponse);
			
			String lat = CustomJsonParser.getLatitude();
			String lng = CustomJsonParser.getLongitude();
			
			String cityClimate = CreateConnection.sendGetCityClimate(lat,lng);
			
			JSONObject currentClimate = CustomJsonParser.getDailyClimateObject(cityClimate);
			
			
			RestTemplate restTemplate = new RestTemplate();
		    System.out.println("Pincode is "+pincode);
		    System.out.println(currentClimate);

			return new ResponseEntity<>(currentClimate, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getCurrentWeatherByCityName/current", method = RequestMethod.GET)
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
		
		JSONObject currentClimate = CustomJsonParser.getCurrentClimateObject(cityClimate);
		
	    Weather weather = this.weatherService.createWeatherObject(currentClimate, cityName);

	    this.weatherService.validateInsertion(currentClimate, cityName);
	    
	    System.out.println("City Name is "+cityName);
	    System.out.println(currentClimate);
	    
		return new ResponseEntity<>(currentClimate, HttpStatus.OK);
		
	} catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
	}
	}
//	
//	@RequestMapping(value = "/getCurrentWeatherByCityName/hourly", method = RequestMethod.GET)
//	public ResponseEntity getHourlyWeatherByCityName(@RequestParam(value="cityName",required=false) String cityName) throws JSONException{
//		
//	try {
//		
//		if(cityName == null) {
//			throw new Exception();
//		}
//		    		
//		String cityResponse = CreateConnection.sendGetCityDetails(cityName);
//		
//		CustomJsonParser.setCoordinates(cityResponse);
//		
//		String lat = CustomJsonParser.getLatitude();
//		String lng = CustomJsonParser.getLongitude();
//		
//		String cityClimate = CreateConnection.sendGetCityClimate(lat,lng);
//		
//		JSONObject currentClimate = CustomJsonParser.getHourlyClimateObject(cityClimate);
//		
//		
//		RestTemplate restTemplate = new RestTemplate();
//	    System.out.println("City Name is "+cityName);
//	    System.out.println(currentClimate);
//	    
//	    //String weatherResult = restTemplate.getForObject(currentClimate.toString(), String.class);
//	    
//		return new ResponseEntity<>(currentClimate, HttpStatus.OK);
//		
//	} catch(Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
//	}
//	}
//	
//	@RequestMapping(value = "/getCurrentWeatherByCityName/daily", method = RequestMethod.GET)
//	public ResponseEntity getDailyWeatherByCityName(@RequestParam(value="cityName",required=false) String cityName) throws JSONException{
//		
//	try {
//		
//		if(cityName == null) {
//			throw new Exception();
//		}
//		    		
//		String cityResponse = CreateConnection.sendGetCityDetails(cityName);
//		
//		CustomJsonParser.setCoordinates(cityResponse);
//		
//		String lat = CustomJsonParser.getLatitude();
//		String lng = CustomJsonParser.getLongitude();
//		
//		String cityClimate = CreateConnection.sendGetCityClimate(lat,lng);
//		
//		JSONObject currentClimate = CustomJsonParser.getDailyClimateObject(cityClimate);
//		
//		
//		RestTemplate restTemplate = new RestTemplate();
//	    System.out.println("City Name is "+cityName);
//	    System.out.println(currentClimate);
//	    
//	    //String weatherResult = restTemplate.getForObject(currentClimate.toString(), String.class);
//	    
//		return new ResponseEntity<>(currentClimate, HttpStatus.OK);
//		
//	} catch(Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<>(new JSONObject().put("result", "Error "+e.toString()).toString(), HttpStatus.BAD_REQUEST);
//	}
//	}
	
	
//	public static void main(String argv[])
//	{
//		String weather = "{\"results\":[{\"address_components\":[{\"long_name\":\"95110\",\"short_name\":\"95110\",\"types\":[\"postal_code\"]},{\"long_name\":\"San Jose\",\"short_name\":\"San Jose\",\"types\":[\"locality\",\"political\"]},{\"long_name\":\"Santa Clara County\",\"short_name\":\"Santa Clara County\",\"types\":[\"administrative_area_level_2\",\"political\"]},{\"long_name\":\"California\",\"short_name\":\"CA\",\"types\":[\"administrative_area_level_1\",\"political\"]},{\"long_name\":\"United States\",\"short_name\":\"US\",\"types\":[\"country\",\"political\"]}],\"formatted_address\":\"San Jose, CA 95110, USA\",\"geometry\":{\"bounds\":{\"northeast\":{\"lat\":37.3749269,\"lng\":-121.870558},\"southwest\":{\"lat\":37.307479,\"lng\":-121.9368571}},\"location\":{\"lat\":37.354611,\"lng\":-121.918866},\"location_type\":\"APPROXIMATE\",\"viewport\":{\"northeast\":{\"lat\":37.3749269,\"lng\":-121.870558},\"southwest\":{\"lat\":37.307479,\"lng\":-121.9368571}}},\"place_id\":\"ChIJ31SYUmLLj4AR4o5iJ_upyvk\",\"types\":[\"postal_code\"]}],\"status\":\"OK\"}";
//		WeatherController wc = new WeatherController();
//		wc.get
//	}
}
