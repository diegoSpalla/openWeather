package it.spalla.openWeather.service.entities.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.spalla.openWeather.model.City;
import it.spalla.openWeather.model.WeatherInformation;
import it.spalla.openWeather.repository.WeatherInformationRepository;
import it.spalla.openWeather.service.entities.CityService;
import it.spalla.openWeather.service.entities.WeatherInformationService;
import it.spalla.openWeather.service.utility.RequestBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("weatherInformationService")
@Transactional
public class WeatherInformationInformationServiceImpl implements WeatherInformationService
{
	Logger logger = LoggerFactory.getLogger(WeatherInformationInformationServiceImpl.class);
	
	//Dependency Injection
	private final CityService cityService;
	
	private final WeatherInformationRepository weatherInformationRepository;
	
	private final RequestBuilderService requestBuilderService;
	
	public WeatherInformationInformationServiceImpl(CityService cityService,
													WeatherInformationRepository weatherInformationRepository,
													RequestBuilderService requestBuilderService)
	{
		this.cityService = cityService;
		this.weatherInformationRepository = weatherInformationRepository;
		this.requestBuilderService = requestBuilderService;
	}
	
	@Override
	public WeatherInformation persist(WeatherInformation information)
	{
		logger.info("Persisting weather information of type " + information.getType() + " for city " + information
				.getCity().getName());
		
		return weatherInformationRepository.save(information);
	}
	
	@Override
	public int createTemperaturesInformationForAllCities()
	{
		//Getting the available cities
		List<City> availableCities = cityService.findAll();
		logger.info("Found " + availableCities.size() + " available cities");
		
		//Retrieving cities temperature information
		int informationCount = 0;
		for(City city : availableCities)
		{
			if(createTemperatureInformationForCity(city))
			{
				logger.info("Information successfully retrieved");
				informationCount++;
			}
		}
		
		logger.info(informationCount + " new weather information");
		return informationCount;
	}
	
	@Override
	public boolean createTemperatureInformationForCity(City city)
	{
		logger.info("Retrieving temperature information for city: " + city.getName());
		
		//building request
		String requestUri = requestBuilderService.buildSingleCityRequest(city.getName(), "metric");
		logger.info("Request uri generated: " + requestUri);
		
		//performing request
		RestTemplate template = new RestTemplate(); //Spring boot REST client
		try
		{
			ResponseEntity<String> response = template.getForEntity(requestUri, String.class);
			
			//JSON mapper to parse ResponseBody
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(response.getBody());
			//Searching for the correct key
			JsonNode main = root.get("main");
			JsonNode temp = main.get("temp");
			Double value = temp.asDouble();
			logger.info("Temperature value found " + value);
			
			//creation of the record
			WeatherInformation information = WeatherInformation.builder().city(city).value(value).type("Temperature")
					.build();
			
			persist(information);
			
			return true;
			
		} catch(JsonProcessingException e)
		{
			logger.error("Exception occurred while creating temperature information for city " + city
					.getName() + " | e -->");
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Override
	public List<WeatherInformation> findAll()
	{
		logger.info("Finding all weather information recorded");
		
		List<WeatherInformation> info = new ArrayList<>();
		weatherInformationRepository.findAll().forEach(info::add);
		
		return info;
	}
}
