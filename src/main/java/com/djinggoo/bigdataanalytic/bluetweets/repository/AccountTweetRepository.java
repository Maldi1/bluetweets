package com.djinggoo.bigdataanalytic.bluetweets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.djinggoo.bigdataanalytic.bluetweets.model.AccountTweet;

@Repository
public interface AccountTweetRepository extends JpaRepository<AccountTweet, String>{

	@Query(value = "SELECT tweet FROM account_tweet", nativeQuery = true)
	public List<String> getAllTweet();
	
	@Query(value = "SELECT COUNT(*) FROM account_tweet", nativeQuery = true)
	public Integer getTotal();
	
}
