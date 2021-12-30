package backend.testingonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AI
	@Column //Cot
	private int id;
	@Column
	private String name;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String department;
	@Column(name = "image")
	private String avatar;

	public Staff() {
		super();
	}

	public Staff(int id, String name, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public Staff(int id, String name, String username, String password, String email, String department,
			String avatar) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.department = department;
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "Staff {id=" + id + ","
				+ " name=" + name + 
				", username=" + username + 
				", password=" + password + 
				", email=" + email + 
				", department=" + department + 
				", avatar=" + avatar + "}";
	}
	
	
	
	

}
