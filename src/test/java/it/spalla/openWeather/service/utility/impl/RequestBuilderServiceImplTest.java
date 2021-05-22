package it.spalla.openWeather.service.utility.impl;

import it.spalla.openWeather.property.OpenWeatherMapRequests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RequestBuilderServiceImplTest
{
	@Test
	void buildSingleCityRequest()
	{
		//Test object creation
		OpenWeatherMapRequests openWeatherMapRequests = new OpenWeatherMapRequests();
		openWeatherMapRequests.setSingleCityBaseRequest("http://api.openweathermap.org/data/2.5/weather?appid=55d125f32b88d9e6d8acf4bfe74fee7b");
		RequestBuilderServiceImpl service = new RequestBuilderServiceImpl(openWeatherMapRequests);
		
		//Expected behavior definition
		RequestBuilderServiceImpl mock = mock(RequestBuilderServiceImpl.class);
		given(mock.buildSingleCityRequest("Pavia", "metric")).willReturn("http://api.openweathermap.org/data/2.5/weather?appid=55d125f32b88d9e6d8acf4bfe74fee7b&q=Pavia&units=metric");
		
		//Test
		assertEquals(mock.buildSingleCityRequest("Pavia", "metric"), service.buildSingleCityRequest("Pavia", "metric"));
	}
}
