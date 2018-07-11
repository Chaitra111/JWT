package com.bridgelabz.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.service.UserService;

/**
 * @author Chaitra Ankolekar
 * Date : 10/07/2018
 * Purpose :UserController class which gets userId from request attribute in getUser method
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestBody User user) {
		if (!userService.verifyUser(user).get().equals(null)) {
			return userService.saveUser(user) + " Welcome " + user.getUserName();
		}
		return "Username doesnt not exist";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@RequestBody User user) {
		if (!userService.verifyEmail(user)) {
			return userService.saveUser(user) + " User Details" + user.toString();

		} else
			return "Email-id already exist!!";
	}

	// @Autowired
	// UserController(UserService userService) {
	// this.userService = userService;
	// }

	@GetMapping("/get")
	public Optional<User> getUser(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userName");
		return userService.getUser(userId);
	}
}