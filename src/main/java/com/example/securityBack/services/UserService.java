package com.example.securityBack.services;

import com.example.securityBack.models.Users;
import com.example.securityBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public List<Users> getAllUsers() {
		return this.userRepo.findAll();
	}

	public Users getUserByUserName(String username) {
		return userRepo.findUsersByUserName(username)
				.orElseThrow(() -> new RuntimeException("This User does not exist."));
	}

	public Users getUserByID(long id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("This User does not exist."));
	}

	public Users newUser(Users user) {
		if(userRepo.findUsersByUserName(user.getUserName()).isPresent()){
			throw new RuntimeException("User with name ("+user.getUserName()+") already exist.");
		}
		user.setActive(true);
		user.setLastLogin(new Timestamp(new Date().getTime()));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getUserName());
		user.setPassword(encodedPassword);
		return this.userRepo.save(user);
	}

	public Users changePassword(long id, String oldPass,String newPass) {
		Users user = userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("This User does not exist."));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(oldPass, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPass));
			return this.userRepo.save(user);
		}
		else{
			throw(new RuntimeException("Current password is not valid."));
		}
	}
}