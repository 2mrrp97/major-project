package com.nsec.taskManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsec.taskManager.models.Remark;

public interface RemarkRepo extends JpaRepository<Remark , Integer> {

}
