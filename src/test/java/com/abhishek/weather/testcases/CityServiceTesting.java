package com.abhishek.weather.testcases;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.abhishek.weather.CityTest.GetCityDetailsServiceStubImpl;
import com.abhishek.weather.connection.CityDetailService;
import com.abhishek.weather.controller.WeatherController;
import com.abhishek.weather.coordinates.CustomJsonParser;
import com.abhishek.weather.service.WeatherServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class CityServiceTesting {

	@Mock
	CityDetailService original;
	
	@Mock
	WeatherServiceImpl weatherService;
	
	@Mock
	CustomJsonParser jsonParser;
	
	@InjectMocks
	WeatherController controller;
	
	GetCityDetailsServiceStubImpl testing = new GetCityDetailsServiceStubImpl();

	@Test
	public void testIntegrationOne() throws IOException, ParseException, JSONException {
		String someCity = "Patiala";
		
		String lat = "37.3229978";
		String lng = "-122.0321823";
		
		
		JSONParser parser = new JSONParser();
		ClassLoader classLoader = getClass().getClassLoader();
		File f1 = new File(classLoader.getResource("input.txt").getFile());
		
		
		Object cityDetails = parser.parse(new FileReader(f1));
		
		File f2 = new File(classLoader.getResource("weather.txt").getFile());
		
		Object cityClimate = parser.parse(new FileReader(f2));
	
		when(original.sendGetCityDetails(someCity)).thenReturn(cityDetails.toString());
	
		when(original.sendGetCityClimate(lat, lng)).thenReturn(cityClimate.toString());
		
		when(jsonParser.setCoordinates(cityDetails.toString())).thenReturn(true);
		
		when(jsonParser.getLatitude()).thenReturn(lat);
		
		when(jsonParser.getLongitude()).thenReturn(lng);
		
		when(jsonParser.getCurrentClimateObject(anyString())).thenReturn((JSONObject)cityClimate);
	
		ResponseEntity response = controller.getCurrentWeatherByCityName(someCity);
		
		assertEquals(cityClimate.toString(), response.getBody().toString());
		
	}
}
