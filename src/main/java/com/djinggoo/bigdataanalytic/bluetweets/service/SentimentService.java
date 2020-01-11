package com.djinggoo.bigdataanalytic.bluetweets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.djinggoo.bigdataanalytic.bluetweets.model.Sentiment;
import com.djinggoo.bigdataanalytic.bluetweets.repository.SentimentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SentimentService {
	
    @Autowired private SentimentRepository sentimentRepository;

    public Sentiment getSentiment(String id){
        return sentimentRepository.getOne(id);
    }

    public List<Sentiment> getSentiments(){
        return sentimentRepository.findAll();
    }
    
	@Scheduled(fixedRate = 1200000) //running tiap 20 menit
	public void removedata() {
		
//	    final String uri = "https://bluetweets.herokuapp.com/";
	    final String uri = "http://localhost:5000/sentiment/process"; // flask sentiment process

	    try {
		    RestTemplate restTemplate = new RestTemplate();
		    String result = restTemplate.getForObject(uri, String.class);
		    log.info("result : "+result);
	    }catch (Exception e) {
			log.error(uri + " >>> connection refused !!");
		}

	}

}
