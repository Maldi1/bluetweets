package com.djinggoo.bigdataanalytic.bluetweets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djinggoo.bigdataanalytic.bluetweets.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query(value = "SELECT COUNT(id) FROM account WHERE twitter_id = :twitterId", 
		   nativeQuery = true)
	public Integer getTotalDataByTwitterId(@Param("twitterId") long twitterId);
	
	
	@Query(value = "SELECT id FROM account WHERE twitter_id = :twitterId", 
			   nativeQuery = true)
	public String getIdByTwitterId(@Param("twitterId") long twitterId);
	
	@Query(value = "SELECT COUNT(*) FROM account", nativeQuery = true)
	public Integer getTotal();
	
	@Query(value = "SELECT * FROM fn_removeDataStreamer()", nativeQuery = true)
	public Integer fn_removeDataStreamer();
	
}
