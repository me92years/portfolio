package org.sample.moviedb.entity.movies;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@ToString(exclude = { "movie" })
@Table(name = "movie_actors")
public class Actor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	private String realName;
	
	private String nickName;
	
	private String portrait;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
		name = "movies_no", 
		foreignKey = @ForeignKey(name = "fk_movie_actors_movies_no"))
	private Movie movie;
	
}
