package com.ghag.rnd.google.appengine.tecgdemo;

import javax.servlet.http.HttpServletRequest;



public class UserProfile {
	
	private long userId;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String voiceContact;
	
	
	public UserProfile(){
		this.userId = System.currentTimeMillis();
		this.firstName = "Ganesh";
		this.lastName = "Ghag";
		this.address = "Flower Valley";
		this.email = "ganyaghag@gmail.com";
		this.voiceContact = "93923993";
	}
	
	public UserProfile(HttpServletRequest request){
		
		this.userId = System.currentTimeMillis();
		this.firstName = request.getParameter("FIRST_NAME");
		this.lastName = request.getParameter("LAST_NAME");
		this.address = request.getParameter("ADDRESS");
		this.email = request.getParameter("EMAIL");
		this.voiceContact = request.getParameter("VOICE_CONTACT");
		
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVoiceContact() {
		return voiceContact;
	}
	public void setVoiceContact(String voiceContact) {
		this.voiceContact = voiceContact;
	}
	
	
	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address
				+ ", email=" + email + ", voiceContact=" + voiceContact + "]";
	}
	
	
	
	

}
