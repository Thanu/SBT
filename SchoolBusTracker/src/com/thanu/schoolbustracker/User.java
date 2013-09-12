package com.thanu.schoolbustracker;

public class User {
	private String userName, password, email, bus_hault;
	private String fullName, gender, address, phone;

	public User() {

	}

	public User(String uname, String fname, String gender, String address,
			String phone, String pword, String email, String bus_hault) {
		this.userName = uname;
		this.fullName = fname;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.password = pword;
		this.email = email;
		this.bus_hault = bus_hault;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBus_hault() {
		return bus_hault;
	}

	public void setBus_hault(String bus_hault) {
		this.bus_hault = bus_hault;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
