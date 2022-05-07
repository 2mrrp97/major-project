package com.nsec.taskManager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.nsec.taskManager.models.RegisteredUser;

@Service
public interface RegisteredUserRepo extends JpaRepository<RegisteredUser , Integer>{
	public Optional<RegisteredUser> findByEmailId(String emailId);
}
