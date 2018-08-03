package com.abhishek.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.weather.model.Weather;
import com.abhishek.weather.repository.WeatherRepository;



@Service
public class WeatherServiceImpl implements WeatherService{
	
	@Autowired
	WeatherRepository repo;

	@Override
	public boolean validateInsertion(JSONObject newVal,String info) {
		// TODO Auto-generated method stub
		
		Weather previousObject = this.getObjectByCityInfo(info);
		
		if(previousObject!=null)//there exist previous weather info for this city
		{
			/*check for the time difference between newObject and the object in Database
			 * and converting it into sec from msec
			 */
			Long timeDifference = ((Long)newVal.get("time") - previousObject.getTime())/1000;
			
			timeDifference/=60; //converting the difference to minutes
			
			if(timeDifference > 10)
			{
				Weather newobject = this.createWeatherObject(newVal, info);
				this.repo.delete(previousObject);
				this.saveObject(newobject);
			}
		}
		else
		{
			Weather newobject = this.createWeatherObject(newVal, info);
			this.saveObject(newobject);
		}

		return false;
	}

	@Override
	public void saveObject(Weather latest) {
		// TODO Auto-generated method stub
	
		this.repo.save(latest);
	}

	@Override
	public Weather getObjectByCityInfo(String info) {
		// TODO Auto-generated method stub
		
		Weather output = null;
		
		List<Weather> listWeather = new ArrayList<>();
		
		listWeather = this.repo.findAll();
		
		for(Weather w:listWeather)
		{
			if(w.getCityInfo().equals(info))
			{
				output = w;
			}
		}
		
		return output;
	}

	@Override
	public Weather createWeatherObject(JSONObject currentClimate,String info) {
		// TODO Auto-generated method stub
		
	    Weather weather = new Weather();
	    
	    weather.summary = (String)currentClimate.get("summary");
	    weather.precipType = (String)currentClimate.get("precipType");
	    weather.temperature = (Double)currentClimate.get("apparentTemperature");
	    weather.humidity = (Double)currentClimate.get("humidity");
	    weather.time = (Long)currentClimate.get("time");
	    weather.cityInfo = info;
	    
	    
		return weather;
	}
	
	
}
