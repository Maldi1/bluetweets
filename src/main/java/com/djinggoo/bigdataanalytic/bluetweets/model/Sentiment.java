package com.djinggoo.bigdataanalytic.bluetweets.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "sentiment")
public class Sentiment {
	
	@Id
	private String id;
	
	@Column(name = "word")
	private String word;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "create_at")
	private Date date;

	public Sentiment() {}
	
}
