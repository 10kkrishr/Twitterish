package com.twitterish.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.twitterish.myapp.model.Session;

@Repository("sessionRepository")
public interface SessionRepository extends JpaRepository<Session, Long> {
	@Query
	Session findBySessionToken(String SessionToken);
}
