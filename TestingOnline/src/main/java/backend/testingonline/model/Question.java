package backend.testingonline.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	protected int id;
	@Column
	protected int type;
	@Column
	protected int subject;
	@Column
	protected String content;
	@Column
	protected int level;
	
	@ManyToMany
	protected List<Test> tests;
	
	@OneToMany
	protected List<MultipleChoiceQuestion> multipleChoiceQuestions;
	
	@OneToOne
	protected EssayQuestion essayQuestion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public List<MultipleChoiceQuestion> getMultipleChoiceQuestions() {
		return multipleChoiceQuestions;
	}

	public void setMultipleChoiceQuestions(List<MultipleChoiceQuestion> multipleChoiceQuestions) {
		this.multipleChoiceQuestions = multipleChoiceQuestions;
	}

	public EssayQuestion getEssayQuestion() {
		return essayQuestion;
	}

	public void setEssayQuestion(EssayQuestion essayQuestion) {
		this.essayQuestion = essayQuestion;
	}
	
}
