package com.nsec.taskManager.services;

import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nsec.taskManager.models.RegisteredUser;
import com.nsec.taskManager.repositories.RegisteredUserRepo;

@Service
@Transactional
public class UserService implements UserDetailsService{
	@Autowired
	RegisteredUserRepo userRepo;
	@Autowired
	PasswordEncoder encoder ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		RegisteredUser u = null;
		try {
			u = this.getUserByEmailId(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("user " + u + " username " + username);
		if(u == null)
			return new User("default" , "default" , new ArrayList<>());
		else {
			String role = u.getRole();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			return new User(u.getEmailId() , u.getPassword() , Arrays.asList(authority));
		}
	}
	
	public RegisteredUser addNewUser(RegisteredUser u) {
		u.setPassword(encoder.encode(u.getPassword()));
		return userRepo.save(u);
	}
	
	public RegisteredUser getUserByEmailId(String emailId) throws Exception {
		return userRepo.findByEmailId(emailId).orElseThrow(() -> new Exception("User not found with mail id " + emailId));
	} 
}
