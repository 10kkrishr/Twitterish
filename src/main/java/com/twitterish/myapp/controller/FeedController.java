package com.twitterish.myapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitterish.myapp.exception.UserNotFoundException;
import com.twitterish.myapp.model.Tweet;
import com.twitterish.myapp.service.FeedService;


@RestController
@RequestMapping("/")
public class FeedController{
	
	@Autowired
    FeedService feedService;

    /**
     * Gets the user feed.
     *
     * @param sessionToken the session token
     * @return List<Tweet>
     */
    @GetMapping("feed")
    public ResponseEntity<List<Tweet>> getUserFeed(@RequestHeader(value="Authorization") String sessionToken){
    	
    	try {
    		List<Tweet> tweetList = feedService.findTweetsByFollower(sessionToken);
        	if(!tweetList.isEmpty()) {
        		return new ResponseEntity<List<Tweet>>(tweetList, HttpStatus.OK);
        	} else {
        		return new ResponseEntity<List<Tweet>>(tweetList, HttpStatus.NO_CONTENT);
        	}
    	} catch(RuntimeException e) {
    		return new ResponseEntity<List<Tweet>>(HttpStatus.INTERNAL_SERVER_ERROR);
    	} catch (UserNotFoundException e) {
    		return new ResponseEntity<List<Tweet>>(HttpStatus.UNAUTHORIZED);
    	} 
    	
    }

    

}
