package com.nsec.taskManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsec.taskManager.models.Answer;

public interface AnswerFileRepo extends JpaRepository<Answer , Integer> {

}
