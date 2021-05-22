package it.spalla.openWeather.service.utility.impl;

import it.spalla.openWeather.property.OpenWeatherMapRequests;
import it.spalla.openWeather.service.utility.RequestBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("requestBuilderService")
public class RequestBuilderServiceImpl implements RequestBuilderService
{
	Logger logger = LoggerFactory.getLogger(RequestBuilderServiceImpl.class);

	//Dependency Injection
	private final String singleCityBaseRequestAPI;
	
	public RequestBuilderServiceImpl(OpenWeatherMapRequests openWeatherMapRequests)
	{
		this.singleCityBaseRequestAPI = openWeatherMapRequests.getSingleCityBaseRequest();
	}
	
	@Override
	public String buildSingleCityRequest(String cityName,
										 String unit)
	{
		logger.info("Building URI for cityName " + cityName + " and unit " + unit);
		
		StringBuilder uriBuilder = new StringBuilder(singleCityBaseRequestAPI);
		
		if(cityName != null && !cityName.isBlank())
		{
			uriBuilder.append("&q=").append(cityName);
		}
		
		if(unit.isBlank())
		{
			logger.warn("No measure unit specified, falling back to default...");
		}
		uriBuilder.append("&units=").append(unit);
		
		return uriBuilder.toString();
	}
}
