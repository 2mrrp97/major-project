package com.nsec.taskManager.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nsec.taskManager.models.Assignment;
import com.nsec.taskManager.repositories.AssignmentFileRepo;


@Service
@Transactional
public class AssignmentFileService {
	@Autowired
    private AssignmentFileRepo assignmentRepo;

    public Assignment storeFile(Assignment dbFile) throws Exception {
        try {
            return assignmentRepo.save(dbFile);
        } 
        catch (Exception ex) {
            throw new Exception("Could not store file " + dbFile.getFileName() + ". Please try again!", ex);
        }
    }

    public Assignment getFile(String fileId) throws Exception {
        return assignmentRepo.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id " + fileId));
    }
}
