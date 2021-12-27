package backend.testingonline.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "id_test")
	private String idTest; // Map bai test
	@Column
	private String name;// Ten cua bai test
	@Column
	private int level; // 1 Fresher, 2 Junior, 3 Senior
	@Column(unique = true)
	private String phone; // SDT
	@Column(unique = true)
	private String email;
	@Column
	private String position;// Vi tri ???
	@Column(name = "test_time")
	private Date testTime; // Thoi gian lam bai
	@Column(name = "english_mark")
	private float englishMark; // ??
	@Column(name = "coding_mark")
	private float codingMark;// ?
	@Column(name = "knowledge_mark")
	private float knowledgeMark;// ?

//	@ManyToMany
//	private List<Test> tests;
//	
//	@ManyToMany
//	private List<Levels> levels;

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

//	public List<Levels> getLevels() {
//		return levels;
//	}
//
//	public void setLevels(List<Levels> levels) {
//		this.levels = levels;
//	}

	public Candidate() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdTest() {
		return idTest;
	}

	public void setIdTest(String idTest) {
		this.idTest = idTest;
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

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public float getEnglishMark() {
		return englishMark;
	}

	public void setEnglishMark(float englishMark) {
		this.englishMark = englishMark;
	}

	public float getCodingMark() {
		return codingMark;
	}

	public void setCodingMark(float codingMark) {
		this.codingMark = codingMark;
	}

	public float getKnowledgeMark() {
		return knowledgeMark;
	}

	public void setKnowledgeMark(float knowledgeMark) {
		this.knowledgeMark = knowledgeMark;
	}

//	public List<Test> getTests() {
//		return tests;
//	}
//
//	public void setTests(List<Test> tests) {
//		this.tests = tests;
//	}
}