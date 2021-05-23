package it.spalla.openWeather.service.entities;

import it.spalla.openWeather.model.City;
import it.spalla.openWeather.model.WeatherInformation;

import java.util.List;

public interface WeatherInformationService
{
	//###########CREATE
	
	/**
	 * Persist a weatherInformation in the database,
	 * it is created in case it doesn't exists, it is updated otherwise
	 *
	 * @param information The weatherInformation entity
	 * @return The entity persisted
	 */
	WeatherInformation persist(WeatherInformation information);
	
	/**
	 * Create records with temperature information for all City
	 * entities saved in the database
	 *
	 * @return The number of records created
	 */
	int createTemperaturesInformationForAllCities();
	
	/**
	 * Create record for temperature information for a city entity
	 *
	 * @param city The city entity
	 * @return True in case of success, False otherwise
	 */
	boolean createTemperatureInformationForCity(City city);
	
	//###########READ
	
	/**
	 * Find all weather information registered
	 *
	 * @return The list of all entities
	 */
	List<WeatherInformation> findAll();
	
	//###########UPDATE
	
	//###########DELETE
}
