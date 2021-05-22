package it.spalla.openWeather.service.daemons.scheduled;

import it.spalla.openWeather.service.entities.impl.WeatherInformationInformationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class temperaturesGetterDaemonTest
{
	@Mock
	WeatherInformationInformationServiceImpl weatherInformationInformationService;
	
	@InjectMocks
	TemperaturesGetterDaemon temperaturesGetterDaemon;
	
	@Test
	void getTemperaturesForAllCities()
	{
		temperaturesGetterDaemon.getTemperaturesForAllCities();
		
		verify(weatherInformationInformationService, times(1)).createTemperaturesInformationForAllCities();
	}
}
