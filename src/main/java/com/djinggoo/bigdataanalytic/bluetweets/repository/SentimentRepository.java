package com.djinggoo.bigdataanalytic.bluetweets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.djinggoo.bigdataanalytic.bluetweets.model.Sentiment;

public interface SentimentRepository extends JpaRepository<Sentiment, String>{

}
