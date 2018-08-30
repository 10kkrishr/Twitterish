package com.twitterish.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.twitterish.myapp.model.Followers;

@Repository("followerRepository")
public interface FollowerRepository extends JpaRepository<Followers, Long> {
	@Query
	List<Followers> findByFollower(String follower);
}
