package com.example.securityBack.restapi;

import com.example.securityBack.dto.JwtResponse;
import com.example.securityBack.dto.LoginRequest;
import com.example.securityBack.security.JwtTokenUtil;
import com.example.securityBack.security.UserDetail;
import com.example.securityBack.security.UserDetailService;
import com.example.securityBack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth")
public class AuthenticationRestAPI {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
        try{
            authenticate(request.getUsername(), request.getPassword());
            final UserDetail userDetail = userDetailService.loadUserByUsername(request.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetail,userService.getUserByUserName(request.getUsername()));
            return new ResponseEntity<>(new JwtResponse(token,userDetail.getUsername()), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Login failed.", HttpStatus.CONFLICT);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
        	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}