package it.spalla.openWeather.service.daemons.scheduled;

import it.spalla.openWeather.service.entities.WeatherInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("temperaturesGetterDaemon")
public class TemperaturesGetterDaemon
{
	Logger logger = LoggerFactory.getLogger(TemperaturesGetterDaemon.class);
	
	//Dependency Injection
	private final WeatherInformationService weatherInformationService;
	
	public TemperaturesGetterDaemon(WeatherInformationService weatherInformationService)
	{
		this.weatherInformationService = weatherInformationService;
	}
	
	/**
	 * Daemon which collects temperature information for all cities at disposal.
	 * Set to start after 5 seconds from startup and repeated every 5 minutes after the last finished execution.
	 */
	@Scheduled(fixedDelay = 500000, initialDelay = 5000)
	public void getTemperaturesForAllCities()
	{
		logger.info("Starting temperatureGetterDaemon routine...");
		
		int informationCreated = weatherInformationService.createTemperaturesInformationForAllCities();
		
		logger.info("Created " + informationCreated + " information regarding cities temperatures");
	}
}
