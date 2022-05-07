package com.nsec.taskManager.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nsec.taskManager.models.Teacher;
import com.nsec.taskManager.repositories.TeacherRepo;

@Service
@Transactional
public class TeacherService {
	@Autowired
	TeacherRepo teacherRepo ;
	
	@Autowired
	PasswordEncoder encoder ;
	
	public Teacher addNewTeacher(Teacher t) {
		t.setPassword(encoder.encode(t.getPassword()));
		return teacherRepo.save(t);
	}
	
	public List<Teacher> all() {
		return teacherRepo.findAll();
	}
	
	public Teacher getById(Integer id) {
		return teacherRepo.findById(id).orElse(null);
	}
}
