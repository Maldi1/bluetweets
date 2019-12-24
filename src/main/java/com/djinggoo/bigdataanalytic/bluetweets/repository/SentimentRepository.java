package com.djinggoo.bigdataanalytic.bluetweets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djinggoo.bigdataanalytic.bluetweets.model.Sentiment;

@Repository
public interface SentimentRepository extends JpaRepository<Sentiment, String>{

}
