package backend.testingonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToOne
	@JoinColumn(name = "id_question")
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
