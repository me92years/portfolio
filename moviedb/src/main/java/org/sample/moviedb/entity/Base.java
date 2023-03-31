package org.sample.moviedb.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class Base {
	
	@CreatedDate
	@Column(insertable = true)
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	private LocalDateTime modifiedDate;
	
}
