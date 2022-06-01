package com.nsec.taskManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
import com.nsec.taskManager.models.Course;
import com.nsec.taskManager.services.AssignmentFileService;
import com.nsec.taskManager.services.CourseService;


@RestController
public class AssignmentFileController {
	@Autowired
	private AssignmentFileService fileStorageService;
	@Autowired
	private CourseService courseService;
	
	@PostMapping(value = "/uploadassignment")
	public ModelAndView uploadFile(@RequestParam("file") MultipartFile file , 
			@RequestParam("courseId") Integer courseId , RedirectAttributes redirectAttributes) throws Exception{
		ModelAndView mav = new ModelAndView("redirect: coursedetails/"+courseId);
		Course course = courseService.getCourseById(courseId);
		
		 // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename().replace(',', '_').replace(' ', '_'));
		Assignment assignment = new Assignment(fileName, file.getContentType() , file.getBytes() , course);
		Assignment dbFile = fileStorageService.storeFile(assignment);
		
        @SuppressWarnings("unused")
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        redirectAttributes.addFlashAttribute("fileId" , dbFile.getId());
        return mav;
	}
	
	@GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception{
        // Load file from database
		Assignment file = fileStorageService.getFile(fileId);
		System.out.println(file.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName())
                .body(new ByteArrayResource(file.getData()));
    }
	
	@GetMapping("/view/{fileId}")
    public ResponseEntity<Resource> view(@PathVariable String fileId) throws Exception{
        // Load file from database
		Assignment file = fileStorageService.getFile(fileId);
		System.out.println(file.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getFileName())
                .body(new ByteArrayResource(file.getData()));
    }
}
