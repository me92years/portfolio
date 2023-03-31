package org.sample.moviedb.dto;

import java.util.Set;

import org.sample.moviedb.entity.movies.Actor;
import org.sample.moviedb.entity.movies.Creator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MovieParams {

	private Long no;
	
	private String title;
	
	private String poster;
	
	private String synopsis;
	
	private String openingDate;
	
	private String rRate;
	
	private String genre;
	
	private String status;
	
	private String originalTitle;
	
	private String originalLauguage;
	
	private String productionCosts;
	
	private String revenue;
	
	private Set<Actor> actors;
	
	private Set<Creator> creators;
	
}
