package com.twitterish.myapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.twitterish.myapp.model.Tweet;

@Repository("tweetRepository")
public interface TweetRepository extends JpaRepository<Tweet, Long> {
	
	@Query("Select t FROM Tweet t \n" + 
			"WHERE Username in (?1)\n" + 			
			"ORDER BY Timestamp desc")
	List<Tweet> findByUserList(List<String> userList, Pageable pageable);

}
