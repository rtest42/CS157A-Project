package edu.sjsu.cs157a.model;

import java.sql.Date;

/**
 * Represents a User in the movie database system.
 * A User object contains personal information, credentials, and metadata related to account creation.
 */
public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private Date joinDate;
	
	public User() {
		
	}
	
	/**
     * Parameterized constructor for creating a User object with specific details.
     *
     * @param id        The unique user ID
     * @param firstName The user's first name
     * @param lastName  The user's last name
     * @param email     The user's email address
     * @param phone     The user's phone number
     * @param password  The user's password
     * @param joinDate  The date the user joined the system
     */
	public User(int id, String firstName, String lastName, String email, String phone, String password, Date joinDate) {
		this.setUserID(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setPassword(password);
		this.setJoinDate(joinDate);
	}
	
	// Getter and setter methods for attributes
	public int getUserID() {
		return userId;
	}
	
	public void setUserID(int id) {
		this.userId = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
