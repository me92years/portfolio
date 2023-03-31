package org.sample.moviedb.service.interfaces;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;

import org.sample.moviedb.dto.MovieParams;
import org.sample.moviedb.dto.PageResponses;
import org.sample.moviedb.entity.movies.Movie;
import org.springframework.data.domain.Pageable;

public interface MovieService {

	PageResponses<Movie, MovieParams> fetchRecentsList(Pageable pageable);
	
	PageResponses<Movie, MovieParams> fetchSearchData(String type, String keyword, Pageable pageable);
	
	MovieParams fetchDetailData(Long no);
	
	default MovieParams convert1To3(Movie en) throws UnsupportedEncodingException {
		return MovieParams.builder()
				.no(en.getNo())
				.title(en.getTitle())
				.poster(URLEncoder.encode(en.getPoster(), "utf-8"))
				.openingDate(en.getOpeningDate().format(DateTimeFormatter.ofPattern("MM월 dd, yyyy")))
				.build();
	}
	
	default MovieParams convert1To4(Movie en) throws UnsupportedEncodingException {
		return MovieParams.builder()
				.no(en.getNo())
				.title(en.getTitle())
				.poster(URLEncoder.encode(en.getPoster(), "utf-8"))
				.openingDate(en.getOpeningDate().format(DateTimeFormatter.ofPattern("MM월 dd, yyyy")))
				.synopsis(en.getSynopsis())
				.build();
	}
	
	default MovieParams convertToAll(Movie en) throws UnsupportedEncodingException {
		return MovieParams.builder()
				.no(en.getNo())
				.title(en.getTitle())
				.poster(URLEncoder.encode(en.getPoster(), "utf-8"))
				.openingDate(en.getOpeningDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
				.synopsis(en.getSynopsis())
				.rRate(en.getRRate())
				.genre(en.getGenre())
				.status(en.getStatus())
				.originalLauguage(en.getOrginalLanguage())
				.originalTitle(en.getOriginalTitle())
				.productionCosts(en.getProductionCosts())
				.revenue(en.getRevenue())
				.actors(en.getActors())
				.creators(en.getCreators())
				.build();
	}
	
}
