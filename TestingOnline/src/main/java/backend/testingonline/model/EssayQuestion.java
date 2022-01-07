package backend.testingonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "e_question")
public class EssayQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
//	@Column(name = "id_type")
//	private int idType; // ???
	@Column
	private String answer;
	@Column
	private String image;
	@Column
	private double mark;
	
	@OneToOne
	@JoinColumn(name = "id_question")
	@JsonIgnore
	@Cascade(value = {CascadeType.ALL})
	private Question question;
	
	public EssayQuestion() {
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "EssayQuestion "
				+ "[id=" + id 
				+ ", answer=" + answer 
				+ ", image=" + image 
				+ ", question=" + question 
				+ "]";
	}

}
