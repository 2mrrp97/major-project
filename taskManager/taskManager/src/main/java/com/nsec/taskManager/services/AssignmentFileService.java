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

    public Assignment storeFile(MultipartFile file) throws Exception {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Assignment dbFile = new Assignment(fileName, file.getContentType(), file.getBytes());
            return assignmentRepo.save(dbFile);
        } 
        catch (Exception ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Assignment getFile(String fileId) throws Exception {
        return assignmentRepo.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id " + fileId));
    }
}
