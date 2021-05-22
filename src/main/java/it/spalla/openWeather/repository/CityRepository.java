package it.spalla.openWeather.repository;

import it.spalla.openWeather.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long>
{
}
