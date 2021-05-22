package it.spalla.openWeather.service.entities.impl;

import it.spalla.openWeather.model.City;
import it.spalla.openWeather.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest
{
	@Mock
	CityRepository cityRepository;
	
	@InjectMocks
	CityServiceImpl cityService;
	
	@Test
	void findAll()
	{
		//Mock data creation
		City pavia = new City().toBuilder().name("Pavia").build();
		City roma = new City().toBuilder().name("Roma").build();
		City napoli = new City().toBuilder().name("Napoli").build();
		
		List<City> cityList = new ArrayList<>();
		cityList.add(pavia);
		cityList.add(roma);
		cityList.add(napoli);
		
		//Behaviour definition
		when(cityRepository.findAll()).thenReturn(cityList);
		
		//Test
		List<City> cities = cityService.findAll();
		assertEquals(3, cities.size());
		assertTrue(cities.contains(pavia));
		assertTrue(cities.contains(roma));
		assertTrue(cities.contains(napoli));
	}
}
