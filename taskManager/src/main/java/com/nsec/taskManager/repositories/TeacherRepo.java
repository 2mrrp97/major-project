package com.nsec.taskManager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsec.taskManager.models.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher , Integer> {
	public Optional<Teacher> findByEmailId(String email);
}
