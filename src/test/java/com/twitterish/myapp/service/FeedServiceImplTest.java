package com.twitterish.myapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitterish.myapp.exception.UserNotFoundException;
import com.twitterish.myapp.model.Followers;
import com.twitterish.myapp.model.Session;
import com.twitterish.myapp.model.Tweet;
import com.twitterish.myapp.repository.FollowerRepository;
import com.twitterish.myapp.repository.SessionRepository;
import com.twitterish.myapp.repository.TweetRepository;

@RunWith(MockitoJUnitRunner.class)
public class FeedServiceImplTest {

	@InjectMocks
	FeedServiceImpl feedServiceImpl;
	@Mock
	SessionRepository sessionRepository;
	@Mock
	FollowerRepository followerRepository;
	@Mock
	TweetRepository tweetRepository;

	@Test
	public void testFeedInvalidUser_ThrowsException() {
		try {
			Mockito.when(sessionRepository.findBySessionToken("123")).thenReturn(null);
			feedServiceImpl.findTweetsByFollower("123");
		} catch (UserNotFoundException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void testFeedValidUser_EmptyUserList() {
		try {
			Session session = Mockito.mock(Session.class);
			List<Followers> followedUsers = new ArrayList<Followers>();

			Mockito.when(sessionRepository.findBySessionToken("123")).thenReturn(session);
			Mockito.when(session.getUsername()).thenReturn("Testuser2");
			Mockito.when(followerRepository.findByFollower(session.getUsername())).thenReturn(followedUsers);

			List<Tweet> tweetList = feedServiceImpl.findTweetsByFollower("123");
			assertEquals(0, tweetList.size());
		} catch (Exception e) {
			fail("Error executing test");
		}
	}

	@Test
	public void testFeedValidUser_ReturnsTweets() {
		try {
			Session session = Mockito.mock(Session.class);
			List<Followers> followedUsers = new ArrayList<Followers>();

			Followers follower = new Followers();
			follower.setId(1000);
			follower.setUsername("TestUser1");
			follower.setFollower("Testuser2");
			followedUsers.add(follower);

			ArrayList<String> userList = new ArrayList<String>();
			userList.add("TestUser1");

			ObjectMapper mapper = new ObjectMapper();
			List<Tweet> tweetList = mapper.readValue(new File("./src/test/resources/mockdata/tweets.json"), List.class);

			Mockito.when(sessionRepository.findBySessionToken("123")).thenReturn(session);
			Mockito.when(session.getUsername()).thenReturn("Testuser2");
			Mockito.when(followerRepository.findByFollower(session.getUsername())).thenReturn(followedUsers);
			Mockito.when(tweetRepository.findByUserList(userList, new PageRequest(0, 100))).thenReturn(tweetList);

			List<Tweet> actualTweetList = feedServiceImpl.findTweetsByFollower("123");
			assertEquals(tweetList, actualTweetList);
		} catch (Exception e) {
			fail("Error executing test");
		}
	}

}
