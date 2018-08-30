package com.twitterish.myapp.service;

import java.util.List;

import com.twitterish.myapp.exception.UserNotFoundException;
import com.twitterish.myapp.model.Tweet;

public interface FeedService {

	List<Tweet> findTweetsByFollower(String followerName) throws UserNotFoundException;

}
