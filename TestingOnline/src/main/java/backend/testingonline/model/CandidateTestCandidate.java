package backend.testingonline.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "candidate")
public class CandidateTestCandidate implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
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
	@Column(name = "date_test")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dates;
	@Column(name = "is_done", columnDefinition = "INT DEFAULT 0")
	private int isDone;
	@Column
	private String avatar;
	
	@OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
	private List<CandidateTest> candidateTests;

	public int CalculatorTotalTime(Set<Test> tests) {
		int time = 0;
		for (Test t: tests) {
			time+=t.timeToSecond();
		}
		return time;
	}
	
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public LocalDateTime getDates() {
		return dates;
	}

	public void setDates(LocalDateTime dates) {
		this.dates = dates;
	}

	public int getIsDone() {
		return isDone;
	}

	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
