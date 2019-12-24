package com.djinggoo.bigdataanalytic.bluetweets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.djinggoo.bigdataanalytic.bluetweets.model.Sentiment;
import com.djinggoo.bigdataanalytic.bluetweets.repository.SentimentRepository;

public class SentimentService {
	
    @Autowired private SentimentRepository sentimentRepository;

    public Sentiment getSentiment(String id){
        return sentimentRepository.getOne(id);
    }

    public List<Sentiment> getSentiments(){
        return sentimentRepository.findAll();
    }

}
