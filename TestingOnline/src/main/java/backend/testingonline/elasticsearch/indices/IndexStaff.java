package backend.testingonline.elasticsearch.indices;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "staff")
@Setting(settingPath = "static/es-settings.json")
public class IndexStaff {

	@Id
	@Field(type = FieldType.Keyword)
	private Integer id;
	
	@Field(type = FieldType.Text)
	private String name;
	
	@Field(type = FieldType.Text)
	private String username;
	
	@Field(type = FieldType.Text)
	private String password;
	
	@Field(type = FieldType.Text)
	private String email;
	
	@Field(type = FieldType.Text)
	private String department;
	
	@Field(type = FieldType.Text)
	private String avatar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
}
