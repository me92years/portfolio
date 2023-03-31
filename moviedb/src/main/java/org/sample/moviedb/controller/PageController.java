package org.sample.moviedb.controller;

import org.sample.moviedb.dto.MovieParams;
import org.sample.moviedb.dto.PageRequests;
import org.sample.moviedb.dto.PageResponses;
import org.sample.moviedb.entity.movies.Movie;
import org.sample.moviedb.service.interfaces.MovieService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
	
	private final MovieService movieService;
	
	@GetMapping("/search")
	public String search(PageRequests pageRequests, Model model) {
		String type = pageRequests.getType();
		String keyword = pageRequests.getKeyword();
		Pageable pageable = pageRequests.getPageable(Sort.by("no").descending());
		PageResponses<Movie, MovieParams> result = movieService.fetchSearchData(type, keyword, pageable);
		model.addAttribute("searched", result);
		return "page/search";	
	}
	
	@GetMapping("/detail/{no}")
	public String detail(@PathVariable("no") Long no, Model model) {
		MovieParams params = movieService.fetchDetailData(no);
		model.addAttribute("movie", params);
		return "page/detail";
	}
	
}
