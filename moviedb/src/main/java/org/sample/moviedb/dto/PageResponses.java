package org.sample.moviedb.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageResponses<EN, DTO> {

	private List<DTO> dtoList;
	private int totalPages;
	private int page;
	private int size;
	private int start, end;
	private boolean prev, next;
	private List<Integer> pageList;
	
	public PageResponses(Page<EN> result, Function<EN, DTO> fn) {
		this.totalPages = result.getTotalPages();
		this.dtoList = result.stream().map(fn).collect(Collectors.toList());
		Pageable pageable = result.getPageable();
		postprocessing(pageable);
	}

	private void postprocessing(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		int tmpEnd = (int) (Math.ceil(page / 10.0) * 10);
		this.start = tmpEnd - 9;
		this.prev = start > 1;
		this.end = totalPages > tmpEnd ? tmpEnd : totalPages;
		this.next = totalPages > tmpEnd;
		this.pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
	
}
