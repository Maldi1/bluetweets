package com.djinggoo.bigdataanalytic.bluetweets.config;

import org.springframework.context.annotation.Configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterAuth {

//	private final String consmerKey = "rcTqI3XfGxgHP9BivYL88m0i6";
//	private final String consumerSecret = "sx0nnCBqILlHvWaGAGjRgDawMwvm0QbTHqsd1O0xDjlJ15U2Wf";
//	private final String accessToken = "1190207367528321024-PydNt1lA5zOCIuqRnWj3hbma8mkFRZ";
//	private final String accessTokenSecret = "ajwS4faGAKx0mOKkgIw3oKIILJdCYY5YxqmPH76NIuu28";
	private final String consumerKey = "n4vnXJS4f0MLiPnSH5LNDVPJF";
	private final String consumerSecret= "F0bBeG02yHGRvYje4LMtiRnaDNn0qrZ9wXnHktO1ctB9tJ9OAY";
	private final String accessToken = "90856614-Lt372Q2T1Hh8E4ReTmwGlutAtvEiYHBsD90KUmAJK";
	private final String accessTokenSecret = "P0ormr2GWURIl4NgHpzdFeNLvi6vwGJh2zIshljzi9bK2";
	
	public Twitter getTwitterAuthentication() {
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();
		confBuilder.setDebugEnabled(true)
				   .setOAuthConsumerKey(this.consumerKey)
				   .setOAuthConsumerSecret(this.consumerSecret)
				   .setOAuthAccessToken(this.accessToken)
				   .setOAuthAccessTokenSecret(this.accessTokenSecret)
				   .setHttpConnectionTimeout(100000);
		
		TwitterFactory twtFactory = new TwitterFactory(confBuilder.build());				
		return twtFactory.getInstance();
	}
	
}
