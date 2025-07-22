package com.classpass.dao;


import com.classpass.entity.User.Role;
import lombok.Data;

@Data
public class UserDao {
	public UserDao() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserDao [username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	public UserDao(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	private String username;
	private String password;
	private Role role;
}
