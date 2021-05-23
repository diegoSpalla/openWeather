package it.spalla.openWeather.controller;

import it.spalla.openWeather.service.entities.WeatherInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{
	//Dependency Injection
	private final WeatherInformationService weatherInformationService;
	
	public MainController(WeatherInformationService weatherInformationService)
	{
		this.weatherInformationService = weatherInformationService;
	}
	
	@GetMapping("/")
	public String showWeatherInformation(Model model)
	{
		model.addAttribute("weatherInformation", weatherInformationService.findAll());
		
		return "Table_weatherInformation";
	}
}
