package org.sample.moviedb.controller;

import org.sample.moviedb.annotation.CheckUser;
import org.sample.moviedb.dto.LoginedUser;
import org.sample.moviedb.dto.MovieParams;
import org.sample.moviedb.dto.PageRequests;
import org.sample.moviedb.dto.PageResponses;
import org.sample.moviedb.entity.movies.Movie;
import org.sample.moviedb.service.interfaces.MovieService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	private final MovieService movieService;
	
	@GetMapping("/")
	public String index(@CheckUser LoginedUser loginedUser, Model model, PageRequests pageRequest) {
		if (loginedUser != null) model.addAttribute("user", loginedUser.toJSON());
		PageResponses<Movie, MovieParams> result = movieService.fetchRecentsList(pageRequest.getPageable(Sort.by("no").descending()));
		model.addAttribute("movies", result);
		return "index";
	}
	
}
