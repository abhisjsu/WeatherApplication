package com.abhishek.weather.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Weather {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	public String cityInfo;
	
	public String summary;
	
	public String precipType;
	
	public Double temperature;
	
	public Double humidity;
	
	public Long time;

	public String getCityInfo() {
		return cityInfo;
	}

	public void setCityInfo(String cityInfo) {
		this.cityInfo = cityInfo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPrecipType() {
		return precipType;
	}
	
	public void setPrecipType(String precipType) {
		this.precipType = precipType;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", \ncityInfo=" + cityInfo + ", \nsummary=" + summary + ", \nprecipType=" + precipType
				+ ", \ntemperature=" + temperature + ", \nhumidity=" + humidity + ", \ntime=" + time + "]";
	}
	

}
