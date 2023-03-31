package org.sample.moviedb.repository.support;

import org.sample.moviedb.entity.movies.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupportMovieRepository {

	Page<Movie> search(String type, String keyword, Pageable pageable);
	
}
