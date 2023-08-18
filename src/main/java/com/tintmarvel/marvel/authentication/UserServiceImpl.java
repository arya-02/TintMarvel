package com.tintmarvel.marvel.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String password = doHashing(user.getPassword());
		user.setPassword(password);
		User newuser = userRepository.save(user);
		return newuser;

	}

	@Override
	public String doHashing(String pass) {
		// TODO Auto-generated method stub
		try { 
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(pass.getBytes());
			byte[] resultByteArray = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : resultByteArray) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString(); 
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
