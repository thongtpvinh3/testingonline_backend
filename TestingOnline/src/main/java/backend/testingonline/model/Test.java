package backend.testingonline.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "test")
public class Test { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private int subject; // 1 english, 2 coding, 3 knowledge
	@Column
	private int level; // 1 FR, 2 JR, 3 SR
	@Column
	private LocalTime time;
//	@Column(name = "id_question")
//	private Integer idQuestion;
	@Column
	private String name;
	@Column(name = "is_done")
	private int isDone; // xong chua ?
	@Column(name = "code_test", unique = true)
	private String codeTest; // code join vao
	@Column(name = "id_candidate")
	private int idCandidate;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	private List<Candidate> candidates;

	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(value = {CascadeType.ALL})
//	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
//	@JoinTable(name = "test_question",
//	joinColumns = {@JoinColumn(name = "id_test")}, inverseJoinColumns = {@JoinColumn(name = "id")})
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
	
	public Test(int subject, int level, String name, int isDone, String codeTest) {
		super();
		this.subject = subject;
		this.level = level;
		this.name = name;
		this.isDone = isDone;
		this.codeTest = codeTest;
	}
	
//	public Test(int subject, int level, String name, int isDone, String codeTest,Question question,List<Question> questions) {
//		super();
//		this.subject = subject;
//		this.level = level;
//		this.name = name;
//		this.isDone = isDone;
//		this.codeTest = codeTest;
//		questions.add(question);
//		this.questions = questions;
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
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsDone() {
		return isDone;
	}

	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}

//	public List<Candidate> getCandidates() {
//		return candidates;
//	}
//
//	public void setCandidates(List<Candidate> candidates) {
//		this.candidates = candidates;
//	}

	public String getCodeTest() {
		return codeTest;
	}

	public void setCodeTest(String codeTest) {
		this.codeTest = codeTest;
	}
	
	public int getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(int idCandidate) {
		this.idCandidate = idCandidate;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", subject=" + subject + ", level=" + level + ", time=" + time + ", name=" + name
				+ ", isDone=" + isDone + ", codeTest=" + codeTest + ", idCandidate=" + idCandidate + ", questions="
				+ questions + "]";
	}
	
	
	
	
}
