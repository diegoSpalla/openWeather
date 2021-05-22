package it.spalla.openWeather.service.entities.impl;

import it.spalla.openWeather.model.City;
import it.spalla.openWeather.model.WeatherInformation;
import it.spalla.openWeather.repository.WeatherInformationRepository;
import it.spalla.openWeather.service.utility.impl.RequestBuilderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherInformationInformationServiceImplTest
{
	@Mock
	WeatherInformationRepository weatherInformationRepository;
	
	@Mock
	CityServiceImpl cityService;
	
	@Mock
	RequestBuilderServiceImpl requestBuilderService;
	
	@InjectMocks
	WeatherInformationInformationServiceImpl weatherInformationInformationService;
	
	@Test
	void persist()
	{
		//Mock data creation
		Random rand = new Random();
		City pavia = new City().toBuilder().name("Pavia").build();
		WeatherInformation info = new WeatherInformation().toBuilder().type("Temperature").city(pavia).value(29.4)
				.build();
		
		//Behaviour definition
		when(weatherInformationRepository.save(info))
				.thenReturn(WeatherInformation.builder().id(rand.nextLong()).value(info.getValue()).city(info.getCity())
						.type(info.getType()).build());
		
		//Test
		WeatherInformation result = weatherInformationInformationService.persist(info);
		assertNotNull(result.getId());
		assertEquals(pavia, result.getCity());
		assertEquals("Temperature", result.getType());
		assertEquals(29.4, result.getValue());
	}
	
	@Test
	void createTemperaturesInformationForAllCities()
	{
		//Mock data creation
		City pavia = City.builder().name("Pavia").build();
		City roma = City.builder().name("Roma").build();
		City napoli = City.builder().name("Napoli").build();
		
		List<City> cityList = new ArrayList<>();
		cityList.add(pavia);
		cityList.add(roma);
		cityList.add(napoli);
		
		//Behavior definition
		given(cityService.findAll()).willReturn(cityList);
		given(requestBuilderService.buildSingleCityRequest("Pavia", "metric"))
				.willReturn("http://api.openweathermap.org/data/2.5/weather?appid=55d125f32b88d9e6d8acf4bfe74fee7b&q=Pavia&units=metric");
		given(requestBuilderService.buildSingleCityRequest("Roma", "metric"))
				.willReturn("http://api.openweathermap.org/data/2.5/weather?appid=55d125f32b88d9e6d8acf4bfe74fee7b&q=Roma&units=metric");
		given(requestBuilderService.buildSingleCityRequest("Napoli", "metric"))
				.willReturn("http://api.openweathermap.org/data/2.5/weather?appid=55d125f32b88d9e6d8acf4bfe74fee7b&q=Napoli&units=metric");
		
		//Test
		int count = weatherInformationInformationService.createTemperaturesInformationForAllCities();
		assertEquals(3, count);
	}
	
	@Test
	void createTemperatureInformationForCity()
	{
		//Mock data creation
		City pavia = City.builder().name("Pavia").build();
		
		//Internal call behavior definition
		given(requestBuilderService.buildSingleCityRequest("Pavia", "metric"))
				.willReturn("http://api.openweathermap.org/data/2.5/weather?appid=55d125f32b88d9e6d8acf4bfe74fee7b&q=Pavia&units=metric");
		
		//Test
		assertTrue(weatherInformationInformationService.createTemperatureInformationForCity(pavia));
	}
}
