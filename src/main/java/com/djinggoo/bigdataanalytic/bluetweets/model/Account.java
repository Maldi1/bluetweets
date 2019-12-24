package com.djinggoo.bigdataanalytic.bluetweets.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "account")
public class Account {

	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "twitter_id", unique = true) 
	private Long twitterId;
	
	@Column(name= "name") 
	private String name;
	
	@Column(name = "screen_name") 
	private String screenName;
	
	@Column(name = "email") 
	private String email;
	
	@Column(name = "description") 
	private String description;
	
	@Column(name = "loc") 
	private String loc;
	
	@Column(name = "lang") 
	private String lang;
	
	@Column(name = "joined_at") 
	private Date joinedAt;
	
	@Column(name = "create_at") 
	private Date createAt;
	
	public Account() {}

	public Account(Long twitterId, String name, String screenName, String email, String description, String loc, String lang, Date joinedAt, Date createAt) {
		this.twitterId = twitterId;
		this.name = name;
		this.screenName = screenName;
		this.email = email;
		this.description = description;
		this.loc = loc;
		this.lang = lang;
		this.joinedAt = joinedAt;
		this.createAt = createAt;
	}	
	
}
