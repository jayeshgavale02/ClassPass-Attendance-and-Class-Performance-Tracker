package com.classpass.entity;


import jakarta.persistence.*;

@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student child;

	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parent(Long id, User user, String name, String phone, Student child) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.phone = phone;
		this.child = child;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Student getChild() {
		return child;
	}

	public void setChild(Student child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "Parent [id=" + id + ", user=" + user + ", name=" + name + ", phone=" + phone + ", child=" + child + "]";
	}
    

}
