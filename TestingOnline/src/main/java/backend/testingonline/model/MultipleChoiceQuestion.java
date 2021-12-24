package backend.testingonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mc_question")
public class MultipleChoiceQuestion extends Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "istrue")
	private int isTrue;
	@Column
	private String answer;
	@Column
	private String img;
	
	@ManyToOne
	@JoinColumn(name = "id_question")
	private Question question;
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public MultipleChoiceQuestion() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsTrue() {
		return isTrue;
	}

	public void setIsTrue(int isTrue) {
		this.isTrue = isTrue;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
