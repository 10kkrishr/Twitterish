package com.twitterish.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.twitterish.myapp.exception.UserNotFoundException;
import com.twitterish.myapp.model.Followers;
import com.twitterish.myapp.model.Session;
import com.twitterish.myapp.model.Tweet;
import com.twitterish.myapp.repository.EmployeeRepository;
import com.twitterish.myapp.repository.FollowerRepository;
import com.twitterish.myapp.repository.SessionRepository;
import com.twitterish.myapp.repository.TweetRepository;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	FollowerRepository followerRepository;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	TweetRepository tweetRepository;

	/**
	 * Find tweets by follower.
	 *
	 * @param followerName the follower name
	 * @return the list
	 * @throws UserNotFoundException the user not found exception
	 */
	public List<Tweet> findTweetsByFollower(String sessionToken) throws UserNotFoundException {

		List<Tweet> tweetList = new ArrayList<Tweet>();
		// If the user is logged in, there will a session entry
		Session session = sessionRepository.findBySessionToken(sessionToken);

		// If session is null , the user is unauthorized.
		if (session == null) {
			throw new UserNotFoundException();
		}

		// Get the list of users that the current user follows
		List<Followers> followedUsers = followerRepository.findByFollower(session.getUsername());
		ArrayList<String> userList = new ArrayList<String>();
		for (Followers f : followedUsers) {
			userList.add(f.getUsername());
		}

		// Make the Database call to get the tweets if the userlist is not empty
		if (!userList.isEmpty()) {
			tweetList = tweetRepository.findByUserList(userList, new PageRequest(0, 100));
		}
		return tweetList;

	}

}
