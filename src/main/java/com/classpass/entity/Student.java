package com.classpass.entity;

import java.util.List;


import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    private Long id; // same as user_id

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String fullName;
    private String className;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "student")
    private List<PerformanceRemark> remarks;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id, User user, String fullName, String className, List<Attendance> attendanceList,
			List<PerformanceRemark> remarks) {
		super();
		this.id = id;
		this.user = user;
		this.fullName = fullName;
		this.className = className;
		this.attendanceList = attendanceList;
		this.remarks = remarks;
	}

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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public List<PerformanceRemark> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<PerformanceRemark> remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", user=" + user + ", fullName=" + fullName + ", className=" + className
				+ ", attendanceList=" + attendanceList + ", remarks=" + remarks + "]";
	}

}
