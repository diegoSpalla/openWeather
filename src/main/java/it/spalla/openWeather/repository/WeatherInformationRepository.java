package it.spalla.openWeather.repository;

import it.spalla.openWeather.model.WeatherInformation;
import org.springframework.data.repository.CrudRepository;

public interface WeatherInformationRepository extends CrudRepository<WeatherInformation, Long>
{
}
