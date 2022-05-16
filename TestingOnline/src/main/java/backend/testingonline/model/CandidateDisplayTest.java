package backend.testingonline.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "candidate")
@SuppressWarnings("serial")
public class CandidateDisplayTest implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name = "id_level")
	private Levels level;
	@Column(unique = true)
	private String phone;
	@Column(unique = true)
	private String email;
	@Column(nullable = true)
	private String position;
	@Column(name = "english_mark", columnDefinition = "DOUBLE DEFAULT 0")
	private Double englishMark; 
	@Column(name = "coding_mark", columnDefinition = "DOUBLE DEFAULT 0")
	private Double codingMark;
	@Column(name = "knowledge_mark", columnDefinition = "DOUBLE DEFAULT 0")
	private Double knowledgeMark;
	
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dates;
	
	@Column(name = "time")
	@DateTimeFormat(pattern = "HH:mm:ss", iso = ISO.TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime times;
	
	@Column(name = "is_done", columnDefinition = "INT DEFAULT 0")
	private int isDone;

	@ManyToMany
//	@Cascade(org.hibernate.annotations.CascadeType.MERGE)
//	@JoinTable(name = "candidate_test", joinColumns = {@JoinColumn(name = "id_candidate")}, 
//				inverseJoinColumns = {@JoinColumn(name = "id_test")})
	@JsonIgnore
	private Set<Test> tests = new HashSet<>();
	
	@Column
	private String avatar;
	
	public int CalculatorTotalTime(Set<Test> tests) {
		int time = 0;
		for (Test t: tests) {
			time+=t.timeToSecond();
		}
		return time;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Levels getLevel() {
		return level;
	}

	public void setLevel(Levels level) {
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

	public LocalDate getDates() {
		return dates;
	}

	public void setDates(LocalDate dates) {
		this.dates = dates;
	}
	
	public LocalTime getTimes() {
		return times;
	}

	public void setTimes(LocalTime times) {
		this.times = times;
	}

	public int getIsDone() {
		return isDone;
	}

	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}
}
