package backend.testingonline.model;

import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column(name = "id_test")
	private String idTest;
	@Column
	private String name;
	@Column
	private int level;
	@Column
	private String phone;
	@Column
	private int email;
	@Column
	private int position;
	@Column(name = "test_time")
	private Time testTime;
	@Column(name = "english_mark")
	private float englishMark;
	@Column(name ="coding_mark")
	private float codingMark;
	@Column(name = "knowledge_mark")
	private float knowledgeMark;
	
	@ManyToMany
	private List<Test> tests;
	
	@ManyToMany
	private List<Levels> levels;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Levels> getLevels() {
		return levels;
	}

	public void setLevels(List<Levels> levels) {
		this.levels = levels;
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

	public int getEmail() {
		return email;
	}

	public void setEmail(int email) {
		this.email = email;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Time getTestTime() {
		return testTime;
	}

	public void setTestTime(Time testTime) {
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

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
}
