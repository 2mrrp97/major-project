package com.nsec.taskManager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsec.taskManager.models.Answer;
import com.nsec.taskManager.models.Assignment;
import com.nsec.taskManager.models.Student;

@Repository 
public interface AnswerFileRepo extends JpaRepository<Answer , String> {
	public Optional<Answer> findByStudentAndAssignment(Student s, Assignment a);
}
