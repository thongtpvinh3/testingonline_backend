package backend.testingonline.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "test")
public class CandidateTestTest implements Serializable {
	
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
	
	@Column
	private String name;
	
	@Column(name = "code_test", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String codeTest; // code join vao
	
	@OneToMany(mappedBy = "test")
	private List<CandidateTest> candidateTests;

	public int getId() {	
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CandidateTest> getCandidateTests() {
		return candidateTests;
	}

	public void setCandidateTests(List<CandidateTest> candidateTests) {
		this.candidateTests = candidateTests;
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

	public LocalTime getTimes() {
		return times;
	}

	public void setTimes(LocalTime times) {
		this.times = times;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeTest() {
		return codeTest;
	}

	public void setCodeTest(String codeTest) {
		this.codeTest = codeTest;
	}
}
