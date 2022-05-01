package com.nsec.taskManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nsec.taskManager.models.AssignmentFile;
import com.nsec.taskManager.repositories.AssignmentFileRepository;

@Service
public class AssignmentFileService {
	@Autowired
    private AssignmentFileRepository assignmentRepo;

    public AssignmentFile storeFile(MultipartFile file) throws Exception {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            AssignmentFile dbFile = new AssignmentFile(fileName, file.getContentType(), file.getBytes());
            return assignmentRepo.save(dbFile);
        } 
        catch (Exception ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public AssignmentFile getFile(String fileId) throws Exception {
        return assignmentRepo.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id " + fileId));
    }
}
