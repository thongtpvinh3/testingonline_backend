package backend.testingonline.model;

import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {
	
	@Id
	@GeneratedValue
	@Column
	private int id;
	@Column
	private int subject; // 1 english, 2 coding, 3 knowledge
	@Column
	private int level;
	@Column
	private Time time;
	@Column(name = "id_question")
	private Integer idQuestion;
	@Column
	private String name;
	@Column(name = "is_done")
	private int isDone;
	@Column(name = "code_test")
	private String codeTest;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	private List<Candidate> candidates;
//	
//	@ManyToMany(fetch = FetchType.EAGER)
//	private List<Question> questions;
//	
//	public List<Question> getQuestions() {
//		return questions;
//	}
//
//	public void setQuestions(List<Question> questions) {
//		this.questions = questions;
//	}

	public Test() {
		super();
	}

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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "test level: " + this.level;
	}
}