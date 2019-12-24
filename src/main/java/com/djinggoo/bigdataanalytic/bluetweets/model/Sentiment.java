package com.djinggoo.bigdataanalytic.bluetweets.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "SENTIMENT")
public class Sentiment {
	
	@Id
	private String id;
	
	@Column(name = "WORD")
	private String word;
	
	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "CREATE_AT")
	private Date date;

}
