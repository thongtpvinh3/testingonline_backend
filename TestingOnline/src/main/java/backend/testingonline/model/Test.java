package backend.testingonline.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "test")
@JsonIgnoreProperties(value = {"candidates"})
public class Test implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private int subject; // 1 english, 2 coding, 3 knowledge
	@Column
	private int level; // 1 FR, 2 JR, 3 SR

	@Column(name = "time")
	@DateTimeFormat(pattern = "HH:mm:ss", iso = ISO.TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
	private LocalTime times;
	

	@Column(name = "date_test")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dates;
	
	@Column
	private String name;
//	@Column(name = "is_done")
//	private int isDone; // xong chua ? 
	@Column(name = "code_test", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String codeTest; // code join vao
//	@Column(name = "id_candidate")
//	private int idCandidate;

	@ManyToMany
	@JoinTable(name = "candidate_test", joinColumns = {@JoinColumn(name = "id_test")}, inverseJoinColumns = {@JoinColumn(name = "id_candidate")})
	private List<Candidate> candidates = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "test_question",
	joinColumns = {@JoinColumn(name = "id_test")}, inverseJoinColumns = {@JoinColumn(name = "id_question")})
	private List<Question> questions = new ArrayList<>(); // Bo cau hoi

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Test() {
		super();
	}

//	public Test(int subject, int level, String name, int isDone, String codeTest) {
//		super();
//		this.subject = subject;
//		this.level = level;
//		this.name = name;
//		this.isDone = isDone;
//		this.codeTest = codeTest;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public LocalTime getTime() {
		return times;
	}

	public void setTime(LocalTime time) {
		this.times = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public int getIsDone() {
//		return isDone;
//	}
//
//	public void setIsDone(int isDone) {
//		this.isDone = isDone;
//	}

	public LocalTime getTimes() {
		return times;
	}

	public void setTimes(LocalTime times) {
		this.times = times;
	}

	public LocalDateTime getDates() {
		return dates;
	}

	public void setDates(LocalDateTime dates) {
		this.dates = dates;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public String getCodeTest() {
		return codeTest;
	}

	public void setCodeTest(String codeTest) {
		this.codeTest = codeTest;
	}

//	public double getMarks() {
//		return marks;
//	}
//
//	public void setMarks(double marks) {
//		this.marks = marks;
//	}
	
	public LocalDateTime getDateTest() {
		return dates;
	}

	public void setDateTest(LocalDateTime dateTest) {
		this.dates = dateTest;
	}
	
	public int timeToSecond() {
		return LocalTime.of(this.times.getHour(), this.times.getMinute(), this.times.getSecond()).toSecondOfDay();
	}

	public String toString1() {
		return "Test [id=" + id + ", subject=" + subject + ", level=" + level + ", time=" + times + ", name=" + name
				 + ", codeTest=" + "]";
	}

}
