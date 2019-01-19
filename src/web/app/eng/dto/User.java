package web.app.eng.dto;

public class User {
	private String firstname;
	private String surname;
	private String email;
	private String username;
	private String password;
	private int birthdate;
	private int birthmonth;
	private int birthyear;
	private String gender;
	private boolean banned;
	private boolean verified;
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public int getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}
	
	public int getBirthmonth() {
		return birthmonth;
	}
	
	public void setBirthmonth(int birthmonth) {
		this.birthmonth = birthmonth;
	}
	
	public int getBirthyear() {
		return birthyear;
	}
	
	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public boolean isBanned() {
		return banned;
	}
	
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	
	public boolean isVerified() {
		return verified;
	}
	
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
}
