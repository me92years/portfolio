package org.sample.moviedb.entity.movies;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.sample.moviedb.entity.Base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = { "creators", "actors" })
@Table(name = "movies")
@NamedEntityGraph(name = "Movie.all", attributeNodes = {
		@NamedAttributeNode("creators"),
		@NamedAttributeNode("actors")
})
public class Movie extends Base {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(nullable = false, unique = true)
	private String title;
	
	@Column(nullable = false)
	private String poster;
	
	private LocalDateTime openingDate;
	
	private String synopsis;
	
	private String rRate;
	
	private String genre;
	
	private String originalTitle;
	
	private String status;
	
	private String orginalLanguage;
	
	private String productionCosts;
	
	private String revenue;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
	@OrderBy(value = "movies_no")
	private Set<Creator> creators;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
	@OrderBy(value = "movies_no")
	private Set<Actor> actors;
	
}
