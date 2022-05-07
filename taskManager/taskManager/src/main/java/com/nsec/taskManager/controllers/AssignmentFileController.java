package com.nsec.taskManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;

import com.nsec.taskManager.models.Assignment;
import com.nsec.taskManager.services.AssignmentFileService;
import com.nsec.taskManager.utils.Constants;

@RestController
public class AssignmentFileController {
	
	@Autowired
	private AssignmentFileService fileStorageService;
	
	@PostMapping(value = "/upload")
	public ModelAndView uploadFile(@RequestParam("file") MultipartFile file , RedirectAttributes redirectAttributes) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/");
		
		long size = file.getSize();
		if(size > Constants.MAX_PERMISSIBLE_FILE_SIZE) {
			redirectAttributes.addFlashAttribute("message", "File Size too Large! Cannot Add.");
			return mav;
		}
		
		Assignment dbFile = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        
        redirectAttributes.addFlashAttribute("fileDownloadUri" , fileDownloadUri);
        redirectAttributes.addFlashAttribute("fileId" , dbFile.getId());
        return mav;
	}
	
	@GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception{
        // Load file from database
		Assignment file = fileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename='" + file.getFileName() + "'")
                .body(new ByteArrayResource(file.getData()));
    }
}
