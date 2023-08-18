package com.tintmarvel.marvel.authentication;

public interface UserService {

	User saveUser(User user);
	String doHashing(String pass);

}
