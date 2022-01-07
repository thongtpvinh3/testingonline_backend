package backend.testingonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "result_candidate")
public class TempResultOfCandidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "id_answer")
	private int idAnswer;
	@Column
	private String answer;
	@Column(name = "id_candidate")
	private int idCandidate;
	
	public TempResultOfCandidate() {
		super();
	}

	public TempResultOfCandidate(int idAnswer, String answer,int idCandidate) {
		super();
		this.idAnswer = idAnswer;
		this.answer = answer;
		this.idCandidate = idCandidate;
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

	public int getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(int idCandidate) {
		this.idCandidate = idCandidate;
	}

	public int getIdAnswer() {
		return idAnswer;
	}

	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

	@Override
	public String toString() {
		return "TempResultOfCandidate {id=" + id + ", idAnswer=" + idAnswer + ", answer=" + answer + ", idCandidate="
				+ idCandidate + "}";
	}
	
	
}
