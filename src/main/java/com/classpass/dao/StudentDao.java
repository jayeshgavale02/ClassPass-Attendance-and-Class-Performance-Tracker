package com.classpass.dao;

import lombok.Data;

@Data
public class StudentDao {
    private Long userId;
    private String fullName;
    private String className;
	public StudentDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentDao(Long userId, String fullName, String className) {
		this.userId = userId;
		this.fullName = fullName;
		this.className = className;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "StudentDao [userId=" + userId + ", fullName=" + fullName + ", className=" + className + "]";
	}
}
