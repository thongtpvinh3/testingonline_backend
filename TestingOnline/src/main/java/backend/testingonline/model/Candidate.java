package backend.testingonline.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "candidate")
public class Candidate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String name;// Ten cua bai test
	@Column(nullable = true)
	private int level; // 1 Fresher, 2 Junior, 3 Senior
	@Column(unique = true)
	private String phone; // SDT
	@Column(unique = true)
	private String email;
	@Column(nullable = true)
	private String position;// Vi tri ???
	@Column(name = "english_mark", columnDefinition = "DOUBLE DEFAULT 0")
	private Double englishMark; // ?? Diem english
	@Column(name = "coding_mark", columnDefinition = "DOUBLE DEFAULT 0")
	private Double codingMark;// ?? Diem Code
	@Column(name = "knowledge_mark", columnDefinition = "DOUBLE DEFAULT 0")
	private Double knowledgeMark;// ?? Diem KnowL

//	@OneToMany(mappedBy = "candidate")// sua thanh Many to Many
//	@Fetch(value = FetchMode.SUBSELECT)
//	@Cascade(value = {CascadeType.ALL})
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@Cascade(value = CascadeType.)
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinTable(name = "candidate_Test", joinColumns = {@JoinColumn(name = "id_candidate")}, 
				inverseJoinColumns = {@JoinColumn(name = "id_test")})
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Set<Test> tests = new HashSet<>();
	
	@Column
	private String avatar;
	
	public String getPhone() {
		return phone;
	}

	public Candidate(String name, int level) {
		super();
		this.name = name;
		this.level = level;
	}
	
	public Candidate(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Candidate() {
		super();
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getEnglishMark() {
		return englishMark;
	}

	public void setEnglishMark(Double englishMark) {
		this.englishMark = englishMark;
	}

	public Double getCodingMark() {
		return codingMark;
	}

	public void setCodingMark(Double codingMark) {
		this.codingMark = codingMark;
	}

	public Double getKnowledgeMark() {
		return knowledgeMark;
	}

	public void setKnowledgeMark(Double knowledgeMark) {
		this.knowledgeMark = knowledgeMark;
	}
	
	

	public Set<Test> getTests() {
		return tests;
	}

	public void setTests(Set<Test> tests) {
		this.tests = tests;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "Candidate "
				+ "{id=" + id + ","
				+ ", name=" + name 
				+ ", level=" + level 
				+ ", phone=" + phone
				+ ", email=" + email 
				+ ", position=" + position 
				+ ", englishMark=" + englishMark 
				+ ", codingMark=" + codingMark 
				+ ", knowledgeMark=" + knowledgeMark 
				+ "}";
	}
	

}
