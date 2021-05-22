package it.spalla.openWeather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class City
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "city")
	private Set<WeatherInformation> weatherInformationSet;
}
