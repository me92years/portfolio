package org.sample.moviedb.service;

import java.io.UnsupportedEncodingException;
import java.util.function.Function;

import org.sample.moviedb.dto.MovieParams;
import org.sample.moviedb.dto.PageResponses;
import org.sample.moviedb.entity.movies.Movie;
import org.sample.moviedb.repository.MovieRepository;
import org.sample.moviedb.service.interfaces.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service	
@RequiredArgsConstructor
@Log4j2
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepository;

	@Override
	public PageResponses<Movie, MovieParams> fetchRecentsList(Pageable pageable) {
		Function<Movie, MovieParams> fn = en -> {
			try {
				return convert1To3(en);
			} catch (UnsupportedEncodingException e) {
				log.error("UnsupportedEncodingException 발생!! convert2And3");
				return null;
			}
		}; 
		Page<Movie> result = movieRepository.findAll(pageable);
		return new PageResponses<>(result, fn);
	}

	@Override
	public PageResponses<Movie, MovieParams> fetchSearchData(String type, String keyword, Pageable pageable) {
		Function<Movie, MovieParams> fn = en -> {
			try {
				return convert1To4(en);
			} catch (UnsupportedEncodingException e) {
				log.error("UnsupportedEncodingException 발생!! convert2And3");
				return null;
			}
		}; 
		Page<Movie> result = movieRepository.search(type, keyword, pageable);
		return new PageResponses<>(result, fn);
	}

	@Override
	public MovieParams fetchDetailData(Long no) {
		Movie movie = movieRepository.findById(no).orElse(null);
		if (movie == null) return null;
		try {
			return convertToAll(movie);
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException 발생!! convert2And3");
			return null;
		}
	}
	
}
