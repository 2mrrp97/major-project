package com.nsec.taskManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uploaded_answers")
public class Answer {
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	String id ;
	
	@Column(name = "file_name")
    String fileName;
	
	@Column(name = "file_type")
    String fileType;
	
	@Column(name = "answer_file")
	@Lob
    private byte[] data;
	
	@ManyToOne(targetEntity = Assignment.class , fetch = FetchType.LAZY)
	@JoinColumn(name = "assignment_id" , nullable = false , updatable = false)
	Assignment assignment ;
	
	@ManyToOne(targetEntity = Student.class , fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id" , nullable = false , updatable = false)
	Student student ;
	
	@OneToOne(targetEntity = Remark.class , fetch = FetchType.EAGER)
	@JoinColumn(name = "remark_id")
	Remark remark;
	
	public Answer(String fileName, String fileType , byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Answer() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	
	public Remark getRemark() {
		return remark;
	}

	public void setRemark(Remark remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + " , remark=" + remark +"]";
	}
}
