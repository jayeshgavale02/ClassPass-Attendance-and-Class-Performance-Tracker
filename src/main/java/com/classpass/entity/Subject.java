package com.classpass.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String className; // e.g., "Class 5"

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "subject")
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "subject")
    private List<PerformanceRemark> remarks;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(Long id, String name, String className, Teacher teacher, List<Attendance> attendanceList,
			List<PerformanceRemark> remarks) {
		super();
		this.id = id;
		this.name = name;
		this.className = className;
		this.teacher = teacher;
		this.attendanceList = attendanceList;
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
		return "Subject [id=" + id + ", name=" + name + ", className=" + className + ", teacher=" + teacher
				+ ", attendanceList=" + attendanceList + ", remarks=" + remarks + "]";
	}

}
