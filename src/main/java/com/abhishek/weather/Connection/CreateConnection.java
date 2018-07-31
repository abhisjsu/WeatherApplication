package com.abhishek.weather.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateConnection {
	
	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL_CITY = "https://maps.googleapis.com/maps/api/geocode/json?&address=";	
	
	private static final String GET_URL_CLIMATE = "https://api.darksky.net/forecast/key/";
	
	public static String sendGetCityDetails(String city) throws IOException {
		URL obj = new URL(GET_URL_CITY+city);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		
		System.out.println("GET Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			return response.toString();
		} 
		
		else {
			return null;
		}

	}
	
	public static String sendGetCityClimate(String lat,String lng) throws IOException {
		URL obj = new URL(GET_URL_CLIMATE+lat+","+lng);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		
		System.out.println("GET Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			return response.toString();
		} 
		
		else {
			return null;
		}

	}

//	public static void main(String argv[]) throws IOException
//	{
//		String output = CreateConnection.sendGET("95110");
//		System.out.println(output);
//	}
	

}
