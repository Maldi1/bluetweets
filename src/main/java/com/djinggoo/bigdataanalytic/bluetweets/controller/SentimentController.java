package com.djinggoo.bigdataanalytic.bluetweets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djinggoo.bigdataanalytic.bluetweets.model.Sentiment;
import com.djinggoo.bigdataanalytic.bluetweets.service.SentimentService;

@RestController
@CrossOrigin(origins = {"*", "http://localhost:3005"})
public class SentimentController {

	@Autowired private SentimentService sentimentService;
	
    @GetMapping(value = "/sentiment")
    public List<Sentiment> getRawDatas(){
        return sentimentService.getSentiments();
    }
	
}
