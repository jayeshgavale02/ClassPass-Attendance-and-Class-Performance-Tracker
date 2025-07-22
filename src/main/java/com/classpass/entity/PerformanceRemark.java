package com.classpass.entity;


import jakarta.persistence.*;

@Entity
public class PerformanceRemark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String remark;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

	public PerformanceRemark() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerformanceRemark(Long id, String remark, Student student, Subject subject, Teacher teacher) {
		super();
		this.id = id;
		this.remark = remark;
		this.student = student;
		this.subject = subject;
		this.teacher = teacher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "PerformanceRemark [id=" + id + ", remark=" + remark + ", student=" + student + ", subject=" + subject
				+ ", teacher=" + teacher + "]";
	}

}
