package com.abhishek.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhishek.weather.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather,Long>{
	
}
