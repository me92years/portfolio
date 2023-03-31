package org.sample.moviedb.repository;

import java.util.Optional;

import org.sample.moviedb.entity.movies.Movie;
import org.sample.moviedb.repository.support.SupportMovieRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long>, SupportMovieRepository {

	@EntityGraph(value = "Movie.all", type = EntityGraphType.FETCH)
	@Query("SELECT m FROM Movie m WHERE m.no = :no")
	Optional<Movie> findById(@Param("no") Long no);
	
}
