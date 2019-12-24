package com.djinggoo.bigdataanalytic.bluetweets.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

public class DataProcessor {
	
//	@Autowired private AccountTweetRepository accountTweetRepository;

	public static ArrayList<Status> queryData(Twitter twitterAuth, String topic) {
		ArrayList<Status> tweetList = new ArrayList<>();
		try {
			Query query = new Query();
			query.setQuery(topic);
			QueryResult result = null;
			do {
				result = twitterAuth.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					tweetList.add(tweet);
				}
			} while (result.hasNext() == true);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tweetList;
	}
	
//	 public static void main(String[] args) {
//		 
//		 System.out.println("start run streamer twitter");
//		 
//		 final String consumerKey = "n4vnXJS4f0MLiPnSH5LNDVPJF";
//		 final String consumerSecret= "F0bBeG02yHGRvYje4LMtiRnaDNn0qrZ9wXnHktO1ctB9tJ9OAY";
//		 final String accessToken = "90856614-Lt372Q2T1Hh8E4ReTmwGlutAtvEiYHBsD90KUmAJK";
//		 final String accessTokenSecret = "P0ormr2GWURIl4NgHpzdFeNLvi6vwGJh2zIshljzi9bK2";
//		 
//		 SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("spark2");
//		 JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(30000));
//		 
//		 System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
//		 System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
//		 System.setProperty("twitter4j.oauth.accessToken", accessToken);
//		 System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);
//		 
//		 JavaReceiverInputDStream<Status> twitterStream = TwitterUtils.createStream(jssc);
//
//		 // Without filter: Output text of all tweets
//		 JavaDStream<String> statuses = twitterStream.map(
//				 new Function<Status, String>() {
//					 public String call(Status status) { return status.getText(); }
//	                }
//	        );	
//		 statuses.print();
//		 jssc.start();
//		 
//		 System.out.println("end of run streamer twitter");
//	 }
}
