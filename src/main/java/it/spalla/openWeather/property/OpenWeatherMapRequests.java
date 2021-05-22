package it.spalla.openWeather.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openweather.api")
public class OpenWeatherMapRequests
{
	private String singleCityBaseRequest;
	
	public String getSingleCityBaseRequest()
	{
		return singleCityBaseRequest;
	}
	
	public void setSingleCityBaseRequest(String singleCityBaseRequest)
	{
		this.singleCityBaseRequest = singleCityBaseRequest;
	}
}
