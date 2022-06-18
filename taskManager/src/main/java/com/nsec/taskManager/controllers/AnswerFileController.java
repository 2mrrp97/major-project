package com.nsec.taskManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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

import com.nsec.taskManager.models.Answer;
import com.nsec.taskManager.models.Assignment;
import com.nsec.taskManager.models.Course;
import com.nsec.taskManager.models.Student;
import com.nsec.taskManager.repositories.StudentRepo;
import com.nsec.taskManager.services.AnswerFileService;
import com.nsec.taskManager.services.AssignmentFileService;
import com.nsec.taskManager.services.CourseService;
import com.nsec.taskManager.services.StudentService;


@RestController
public class AnswerFileController {
	@Autowired
	private AnswerFileService ansService;
	@Autowired
	private AssignmentFileService assService;
	@Autowired
	private StudentRepo str ;
	
	@PostMapping(value = "/answer/upload")
	public ModelAndView uploadFile(@RequestParam("file") MultipartFile file , 
			@RequestParam("assignmentId") String assignmentId , @RequestParam("username") String username , 
			RedirectAttributes redirectAttributes , @RequestParam(name = "ansId" , required = false) String ansId) throws Exception{
		ModelAndView mav = new ModelAndView("redirect: /dashboard");
		
		System.out.println(" assignmentId " + assignmentId + " file " + file + " student email " + username);
		Assignment ass = assService.getFile(assignmentId);
		Student s = str.findByEmailId(username).orElse(null);
		
		Answer ans = null; 
		 // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try { 
        	ans = ansService.getFile(ansId);
        	ans.setFileName(fileName);
        	ans.setFileType(file.getContentType());
        	ans.setData(file.getBytes());
        	System.out.println("ex not t " + ans);
        	ansService.storeFile(ans);
        }
        catch (Exception ex) {
        	ans = new Answer(fileName, file.getContentType() , file.getBytes());
    		ans.setAssignment(ass);
    		ans.setStudent(s);
    		Answer savedans = ansService.storeFile(ans); 
    		System.out.println("ex t " + savedans);
        }
		
        return mav;
	}
	
	
	

	@GetMapping("/answer/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception{
        // Load file from database
		Answer file = ansService.getFile(fileId);
		System.out.println(file.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName())
                .body(new ByteArrayResource(file.getData()));
    }
	
	
	@GetMapping("/answer/view/{fileId}")
    public ResponseEntity<Resource> view(@PathVariable String fileId) throws Exception{
        // Load file from database
		Answer file = ansService.getFile(fileId);
		System.out.println(file.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getFileName())
                .body(new ByteArrayResource(file.getData()));
    }
}
