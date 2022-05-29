package com.nsec.taskManager.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nsec.taskManager.models.Assignment;

@Repository
public interface AssignmentFileRepo extends JpaRepository<Assignment, String> {
	
}
