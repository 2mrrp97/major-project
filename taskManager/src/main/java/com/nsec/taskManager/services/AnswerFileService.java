package com.nsec.taskManager.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsec.taskManager.models.Answer;
import com.nsec.taskManager.repositories.AnswerFileRepo;


@Service
@Transactional
public class AnswerFileService {
	@Autowired
    private AnswerFileRepo ansRepo;

    public Answer storeFile(Answer dbFile) throws Exception {
        try {
            return ansRepo.save(dbFile);
        } 
        catch (Exception ex) {
            throw new Exception("Could not store file " + dbFile.getFileName() + ". Please try again!", ex);
        }
    }

    public Answer getFile(String fileId) throws Exception {
        return ansRepo.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id " + fileId));
    }
}
