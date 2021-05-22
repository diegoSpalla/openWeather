package it.spalla.openWeather.service.entities.impl;

import it.spalla.openWeather.model.City;
import it.spalla.openWeather.repository.CityRepository;
import it.spalla.openWeather.service.entities.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService
{
	Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
	
	//Dependency Injection
	private final CityRepository cityRepository;
	
	public CityServiceImpl(CityRepository cityRepository)
	{
		this.cityRepository = cityRepository;
	}
	
	@Override
	public List<City> findAll()
	{
		logger.info("Finding all city recorded");
		
		List<City> cities = new ArrayList<>();
		cityRepository.findAll().forEach(cities::add);
		
		return cities;
	}
}
