package it.spalla.openWeather.service.utility;

public interface RequestBuilderService
{
	/**
	 * Build a request to call openWeatherMap API to retrieve information about a single city
	 *
	 * @param cityName The name of the city to be put as a request parameter
	 * @param unit   The measure unit wanted to be put as a request parameter
	 * @return The String representation of the built request
	 */
	String buildSingleCityRequest(String cityName,
								  String unit);
}
