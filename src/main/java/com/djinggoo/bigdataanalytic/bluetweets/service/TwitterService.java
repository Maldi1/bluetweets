package com.djinggoo.bigdataanalytic.bluetweets.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.djinggoo.bigdataanalytic.bluetweets.config.TwitterAuth;
import com.djinggoo.bigdataanalytic.bluetweets.model.Account;
import com.djinggoo.bigdataanalytic.bluetweets.model.AccountTweet;
import com.djinggoo.bigdataanalytic.bluetweets.processor.DataProcessor;
import com.djinggoo.bigdataanalytic.bluetweets.repository.AccountRepository;
import com.djinggoo.bigdataanalytic.bluetweets.repository.AccountTweetRepository;

import lombok.extern.slf4j.Slf4j;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;

@Slf4j
@Service
public class TwitterService {
	
	@Autowired private AccountRepository accountRepository;
	@Autowired private AccountTweetRepository accountTweetRepository;
	
	/**
	 * Query tweet by some word
	 * @return list of tweet with some word
	 */
	public ArrayList<Status> getTweet() {
		Twitter twitter = null;
		ArrayList<Status> tweets = new ArrayList<>();
		
		twitter = new TwitterAuth().getTwitterAuthentication();
		tweets = DataProcessor.queryData(twitter, "jokowi");
		
		return tweets;
		
	}
	
	/**
	 * running stream to crawl data in twitter
	 */
	public void runTwitterStream() {
//		accountTweetRepository.deleteAll();
//		accountRepository.deleteAll();
				
		final Set<String> keywords = new HashSet<String>();
		keywords.add("jokowi");
		 
		StatusListener statusListener = new StatusListener() {
			
			@Override
			public void onException(Exception ex) {
				log.error("FAILED : "+ex.getMessage());
				ex.printStackTrace();
			}
			
			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {System.out.println("hello");}
			
			@Override
			public void onStatus(Status status) {
				final String statusText = status.getText();				
				
				for (String keyword : keywords) {
					if (statusText.contains(keyword)) {					
						Account account = null;
						String id = "";
						if (accountRepository.getTotalDataByTwitterId(status.getUser().getId()) == 0) {
							account = accountRepository.save(buildAccount(status.getUser()));
							log.info("SUCCESS INSERT DATA USER\t\t>> ["+account.getTwitterId()+", "+account.getName()+"]");
						}else {
							id = accountRepository.getIdByTwitterId(status.getUser().getId());
							account = new Account();
							account.setId(id);
							log.info("SAME USER WITH ANOTHER TWEET\t>> ["+status.getUser().getId()+", "+status.getUser().getName()+"]");
						}
						
						AccountTweet tweet = new AccountTweet(account, status.getId(), statusText, status.getCreatedAt(), new Date());
						accountTweetRepository.save(tweet);
						log.info("SUCCESS INSERT DATA TWEET\t\t>> ["+status.getUser().getId()+", "+status.getUser().getName()+"]");					
					}
				}
			}
			
			@Override
			public void onStallWarning(StallWarning warning) {
//				log.info("ON STALL WARNING [" + warning.getCode() +" : "+warning.getMessage()+"]");
			}
			
			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
//				log.info("ON SCRUB GEO");
			}
			
			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
		};

	    TwitterStream twitterStream = new TwitterStreamFactory().getInstance(new TwitterAuth().getTwitterAuthentication().getAuthorization());
	    FilterQuery filterQuery = new FilterQuery(keywords.toArray(new String[0]));

	    twitterStream.addListener(statusListener);
	    twitterStream.filter(filterQuery);
	}
	
	/**
	 * update status wkwkwkw :p
	 */
	public void setStatus() {
		Twitter twitter = new TwitterAuth().getTwitterAuthentication();
		try {
			twitter.updateStatus("halo malik :p");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
//	@Scheduled(fixedDelay = 60000)
//	@Transactional
	@Scheduled(fixedRate = 300000)
	public void removedata() {
		Integer totalRows = accountRepository.getTotal() + accountTweetRepository.getTotal();
		
		if (totalRows >= 9000) {
			accountRepository.fn_removeDataStreamer();
			log.info("Remove Data >> Success");
		}
	}
	
	/**
	 * Account data builder
	 * @param twitterUser user twitter
	 * @return account model
	 */
	private Account buildAccount(User twitterUser) {
		Account account = new Account(twitterUser.getId(), 
									  twitterUser.getName(), 
									  twitterUser.getScreenName(), 
									  twitterUser.getEmail(), 
									  twitterUser.getDescription(), 
									  twitterUser.getLocation(), 
									  twitterUser.getLang(), 
									  twitterUser.getCreatedAt(), 
									  new Date());
		return account;
	}
	
}
