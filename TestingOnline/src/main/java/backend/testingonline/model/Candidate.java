package backend.testingonline.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@Column(name = "english_mark", nullable = true)
	private double englishMark; // ?? Diem english
	@Column(name = "coding_mark",nullable = true)
	private double codingMark;// ?? Diem Code
	@Column(name = "knowledge_mark",nullable = true)
	private double knowledgeMark;// ?? Diem KnowL

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@JoinTable(name = "candidate_test",
	joinColumns = {@JoinColumn(name = "id_candidate")}, inverseJoinColumns = {@JoinColumn(name = "id_test")} )
	private List<Test> tests = new ArrayList<>();
	
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

	public double getEnglishMark() {
		return englishMark;
	}

	public void setEnglishMark(double englishMark) {
		this.englishMark = englishMark;
	}

	public double getCodingMark() {
		return codingMark;
	}

	public void setCodingMark(double codingMark) {
		this.codingMark = codingMark;
	}

	public double getKnowledgeMark() {
		return knowledgeMark;
	}

	public void setKnowledgeMark(double knowledgeMark) {
		this.knowledgeMark = knowledgeMark;
	}
	
	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
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
