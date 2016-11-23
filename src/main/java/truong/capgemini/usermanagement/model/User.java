package truong.capgemini.usermanagement.model;

import java.util.Date;

public class User {

	private long id;
	private String name, email;
	private Date dateOfBirth;
	private int age;
	private String phone;

	public User() {
		super();
	}

	public User(long id, String name, String email, int age, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
