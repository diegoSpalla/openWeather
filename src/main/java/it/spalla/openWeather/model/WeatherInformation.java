package it.spalla.openWeather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "weather_information", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WeatherInformation
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "type")
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "city")
	private City city;
}
