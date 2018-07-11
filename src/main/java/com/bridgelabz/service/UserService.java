package com.bridgelabz.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.model.User;
import com.bridgelabz.repository.UserRepository;

/**
 * @author Chaitra Ankolekar
 * Date : 10/07/2018
 * Purpose :UserServiceto get user from database and save user to database
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userDao;
	private TokenService tokenService;

	public Optional<User> verifyUser(User user) {
		return userDao.findById(user.getUserName());
	}

	public boolean verifyEmail(User user) {
		if (userDao.existsById(user.getEmail())) {
			return true;
		}
		return false;
	}

	public void save(User user) {
		userDao.insert(user);
	}

	UserService(UserRepository userRep, TokenService tokenService) {
		this.userDao = userRep;
		this.tokenService = tokenService;
	}

	public Optional<User> getUser(String id) {
		return userDao.findById(id);
	}

	public String saveUser(User user) {
		User savedUser = userDao.save(user);
		return tokenService.createToken(savedUser.getUserName());
	}
}