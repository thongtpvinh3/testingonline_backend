package backend.testingonline.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "question")
@JsonIgnoreProperties(value = {"tests"})
@SuppressWarnings("serial")
public class Question implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	protected int id;
	@Column
	protected int type; // 0 multichoice , 1 essay
	@Column
	protected int subject; // 1 english, 2 coding, 3 knowledge
	@Column
	protected String content; // noi dung
	@Column
	protected int level; // 1 Fresher, 2 Junior, 3 Senior
	@Column(name = "img")
	protected String image;
	
	@ManyToMany
	@Fetch(value = FetchMode.SUBSELECT)
	@Cascade(value = {CascadeType.MERGE})
	@JoinTable(name = "test_question",
	joinColumns = {@JoinColumn(name = "id_question")}, inverseJoinColumns = {@JoinColumn(name = "id_test")})
	protected List<Test> tests = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question", orphanRemoval = true, cascade = javax.persistence.CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private List<MultipleChoiceQuestion> multipleChoiceQuestions = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "question")
	@Cascade(CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private EssayQuestion essayQuestion;
	
	public Question() {
		super();
	}
	
	public Question(int id, int subject, String content, int level) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	
	public String getTypeToString() {
		if (type == 0) {
			return "Multiple Choice Question";
		}
		return "Essay Question";
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
	
	@Override
	public String toString() {
		return "Question "
				+ "[id=" + id 
				+ ", type=" + type 
				+ ", subject=" + subject 
				+ ", content=" + content 
				+ ", level=" + level 
				+ ", tests=" + tests 
				+ ", multipleChoiceQuestions=" + multipleChoiceQuestions
				+ ", essayQuestion=" + essayQuestion + "]";
	}
	
}
