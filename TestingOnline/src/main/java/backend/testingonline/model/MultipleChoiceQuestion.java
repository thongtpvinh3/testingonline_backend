package backend.testingonline.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mc_question")
public class MultipleChoiceQuestion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "istrue")
	private int isTrue; // Dung ko ?
	@Column
	private String answer;// cac cau tra loi
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Question question;
	
	public MultipleChoiceQuestion() {
		super();
	}
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "MultipleChoiceQuestion "
				+ "[id=" + id 
				+ ", isTrue=" + isTrue 
				+ ", answer=" + answer 
				+ ", question=" + question 
				+ "]";
	}

//	public int getIdQuestion() {
//		return idQuestion;
//	}
//
//	public void setIdQuestion(int idQuestion) {
//		this.idQuestion = idQuestion;
//	}
	
}
