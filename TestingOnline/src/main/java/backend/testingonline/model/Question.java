package backend.testingonline.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_type")
	private QuestionType type;
	@ManyToOne
	@JoinColumn(name = "id_subject")
	private Subject subject;
	@Column
	private String content;
	@ManyToOne
	@JoinColumn(name = "id_level")
	private Levels level;
	@Column(name = "img")
	private String image;
	
	@ManyToMany
	@Fetch(value = FetchMode.SUBSELECT)
	@Cascade(value = {CascadeType.MERGE})
	@JoinTable(name = "test_question",
	joinColumns = {@JoinColumn(name = "id_question")}, inverseJoinColumns = {@JoinColumn(name = "id_test")})
	protected List<Test> tests = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = javax.persistence.CascadeType.ALL)
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, level, subject, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(content, other.content) && level == other.level && subject == other.subject
				&& type == other.type;
	}
//	
//	public static void main(String[] args) {
//		Question q = new Question(0, 2, "abc", 1);
//		Question q2 = new Question(0, 1, "abc", 1);
//		
//		System.out.println(q.equals(q2));
//	}
}
