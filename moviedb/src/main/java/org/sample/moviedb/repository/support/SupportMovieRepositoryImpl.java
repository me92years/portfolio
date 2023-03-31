package org.sample.moviedb.repository.support;

import java.util.ArrayList;
import java.util.List;

import org.sample.moviedb.entity.movies.Movie;
import org.sample.moviedb.entity.movies.QActor;
import org.sample.moviedb.entity.movies.QCreator;
import org.sample.moviedb.entity.movies.QMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

public class SupportMovieRepositoryImpl extends QuerydslRepositorySupport implements SupportMovieRepository {

	public SupportMovieRepositoryImpl() { super(Movie.class); }

	@Override
	public Page<Movie> search(String type, String keyword, Pageable pageable) {
		QMovie movie = QMovie.movie;
		QCreator creator = QCreator.creator;
		QActor actor = QActor.actor;
		
		JPQLQuery<Movie> query = from(movie)
				.leftJoin(creator).on(creator.movie.no.eq(movie.no))
				.leftJoin(actor).on(actor.movie.no.eq(movie.no))
				.select(movie);
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression booleanExpression = movie.no.gt(0L);
		booleanBuilder.and(booleanExpression);
		
		if (!type.equals("") && !keyword.equals("")) {
			BooleanBuilder conditionBuilder = new BooleanBuilder();
			if (type.equals("search")) {
				conditionBuilder.or(movie.title.contains(keyword));
				conditionBuilder.or(actor.realName.contains(keyword));
				conditionBuilder.or(actor.nickName.contains(keyword));
				conditionBuilder.or(creator.name.contains(keyword));
			}
			booleanBuilder.and(conditionBuilder);
		}
		
		query.where(booleanBuilder);
		getOrderSpecifier(pageable.getSort()).forEach(order -> {
			query.orderBy(order);
		});
		
		query.groupBy(movie);
		query.offset(pageable.getOffset());
		query.limit(pageable.getPageSize());
		
		List<Movie> result = query.fetch();
		long fetchCount = query.fetchCount();
		
		return new PageImpl<>(result, pageable, fetchCount);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
		List<OrderSpecifier> orderList = new ArrayList<>();
		sort.stream().forEach(order -> {
			Order direction = order.isAscending() ? Order.ASC : Order.DESC;
			String prop = order.getProperty();
			PathBuilder<Movie> pathBuilder = new PathBuilder<>(Movie.class, "movie");
			orderList.add(new OrderSpecifier(direction, pathBuilder.get(prop)));
		});
		return orderList;
	}

	
}
