package com.classpass.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String fullName;
    private String department;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "teacher")
    private List<PerformanceRemark> remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<PerformanceRemark> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<PerformanceRemark> remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", user=" + user + ", fullName=" + fullName + ", department=" + department
				+ ", subjects=" + subjects + ", remarks=" + remarks + "]";
	}

	public Teacher(Long id, User user, String fullName, String department, List<Subject> subjects,
			List<PerformanceRemark> remarks) {
		super();
		this.id = id;
		this.user = user;
		this.fullName = fullName;
		this.department = department;
		this.subjects = subjects;
		this.remarks = remarks;
	}

	public Teacher() {

	}

}
