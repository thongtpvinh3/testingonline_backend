package backend.testingonline.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "test")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@SuppressWarnings("serial")
public class Test implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Subject subject;
	@ManyToOne
	private Levels level;

	@Column(name = "time")
	@DateTimeFormat(pattern = "HH:mm:ss", iso = ISO.TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime times;

	@Column
	private String name;

	@Column(name = "code_test", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String codeTest; // code join vao

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "candidate_Test", joinColumns = { @JoinColumn(name = "id_test") }, inverseJoinColumns = {
			@JoinColumn(name = "id_candidate") })
	private List<Candidate> candidates = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "test_question", joinColumns = { @JoinColumn(name = "id_test") }, inverseJoinColumns = {
			@JoinColumn(name = "id_question") })
	private Set<Question> questions = new HashSet<>(); // Bo cau hoi

	public Test() {
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Levels getLevel() {
		return level;
	}

	public void setLevel(Levels level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getTimes() {
		return times;
	}

	public void setTimes(LocalTime times) {
		this.times = times;
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

	public int timeToSecond() {
		return LocalTime.of(this.times.getHour(), this.times.getMinute(), this.times.getSecond()).toSecondOfDay();
	}
}
