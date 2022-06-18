package com.nsec.taskManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Remark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "remark_id")
	Integer id ;
	
	@Column(name = "content" , columnDefinition = "mediumtext")
	String content;
	
	@OneToOne(mappedBy = "remark" , targetEntity = Answer.class , fetch = FetchType.LAZY)
	Answer ans ;
	
	public Remark() {
		super();
	}

	public Remark(String content) {
		super();
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Answer getAns() {
		return ans;
	}

	public void setAns(Answer ans) {
		this.ans = ans;
	}

	@Override
	public String toString() {
		return "Remark [id=" + id + ", content=" + content + "]";
	}
}
