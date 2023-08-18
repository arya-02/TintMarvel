package com.tintmarvel.marvel.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM users u WHERE u.email = ?1", nativeQuery = true)
	User findByEmail(String username);

}
