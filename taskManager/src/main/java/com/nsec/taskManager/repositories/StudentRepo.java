package com.nsec.taskManager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsec.taskManager.models.Student;

public interface StudentRepo extends JpaRepository<Student , Integer> {
	public Optional<Student> findByEmailId(String email);
}
