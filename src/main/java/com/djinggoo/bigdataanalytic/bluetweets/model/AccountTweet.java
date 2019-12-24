package com.djinggoo.bigdataanalytic.bluetweets.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "account_tweet")
public class AccountTweet {

	@Id 
	private String id = UUID.randomUUID().toString();
	
	@JoinColumn(name = "account_id") 
	@ManyToOne(targetEntity = Account.class)
	private Account account;
	
	@Column(name = "twitter_id")
	private Long twitterId;
	
	@Column(name = "tweet")
	private String tweet;
	
	@Column(name = "tweet_created_at")
	private Date tweetCreatedAt;
	
	@Column(name = "create_at")
	private Date createAt;

	public AccountTweet() {}
	
	public AccountTweet(Account account, Long twitterId, String tweet, Date tweetCreatedAt, Date createAt) {
		super();
		this.account = account;
		this.twitterId = twitterId;
		this.tweet = tweet;
		this.tweetCreatedAt = tweetCreatedAt;
		this.createAt = createAt;
	}
		
}
