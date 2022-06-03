package com.nsec.taskManager.models;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uploaded_assignments")
public class Assignment {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;

	@Column(name = "file_name")
    String fileName;
	
	@Column(name = "description")
    String description ;
	
	
	@Column(name = "file_type")
    String fileType;
	
	@Column(name = "uploaded_at")
    Date uploadedAt;
	
	@ManyToOne(targetEntity = Course.class , fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id" , updatable = false)
	Course course ;
	
	
	@OneToMany(mappedBy = "assignment" , targetEntity = Answer.class , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	Set<Answer> answers ;
	
	@Column(name = "assignment_file")
	@Lob
    private byte[] data;

    public Assignment() {
    	 this.fileName = "";
         this.fileType = "";
         this.data = null;
         this.course = null;
         this.description = null;
    }

    public Assignment(String fileName, String fileType, byte[] data , Course c) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.course = c;
        this.description = null;
    }

    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	
	public Date getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(Date uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public void addAnswer(Answer a) {
		if(answers == null) answers = new HashSet<>();
		answers.add(a);
	}

	@Override
	public String toString() {
		return "Assignment [id=" + id + ", fileName=" + fileName + ", description=" + description + ", fileType="
				+ fileType + ", answers=" + answers + "]";
	}
	
	@PrePersist
    public void onPrePersist() { 
		this.uploadedAt = new Date();
	}
}
