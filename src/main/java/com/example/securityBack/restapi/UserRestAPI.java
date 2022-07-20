package com.example.securityBack.restapi;

import com.example.securityBack.models.Roles;
import com.example.securityBack.models.Users;
import com.example.securityBack.restapi.api.UserAPI;
import com.example.securityBack.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Api(value = "User Account API Doc")
public class UserRestAPI implements UserAPI {
	
	@Autowired
    private UserService userService;

	@Override
	public ResponseEntity<?> newUser(Users user) {
		try{
			Set<Roles> roles=new HashSet<>();
			user.setRoles(roles);
			user = this.userService.newUser(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}
		catch(RuntimeException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<?> getUsers(){
		List<Users> response = this.userService.getAllUsers();
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<?> getUser(long id){
		try{
			Users response = userService.getUserByID(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(RuntimeException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}
}