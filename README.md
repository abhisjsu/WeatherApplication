# WeatherApplication
Weather Forecasting Application 


Create a web based application that will allow the user to check weather for a city or zip code.

It should support the following use cases:

1. Lookup current weather by city name or zip code
2. Lookup hourly weather for 24 hours using city name or zip code
3. Lookup weather for next 5 or 7 days using city name or zip code

**These REST endpoints have dependence on on google maps api which often becomes dysfunctional**
**That Exception caser has been handled case a case of "Input City Not Found"**


*** REST END POINT Deployed on Heroku Cloud ***
1. **By City Name**

- 1.1 GET current Climate
- https://weather101abhi.herokuapp.com/weather-api/dashboard/getCurrentWeatherByCityName/current?cityName=Cupertino
- 
- 1.2 GET hourly Climate
- https://weather101abhi.herokuapp.com/weather-api/dashboard/getCurrentWeatherByCityName/hourly?cityName=Cupertino
- 
- 1.3 GET daily Climate
- https://weather101abhi.herokuapp.com/weather-api/dashboard/getCurrentWeatherByCityName/daily?cityName=Cupertino

2. **By City PinCode**

- 2.1 GET current Climate
- https://weather101abhi.herokuapp.com/weather-api/dashboard/getCurrentWeatherByCityCode/current?pincode=95110
- 
- 2.2 GET hourly Climate
- https://weather101abhi.herokuapp.com/weather-api/dashboard/getCurrentWeatherByCityCode/hourly?pincode=95110
- 
- 2.3 GET daily Climate
- https://weather101abhi.herokuapp.com/weather-api/dashboard/getCurrentWeatherByCityCode/daily?pincode=95110
