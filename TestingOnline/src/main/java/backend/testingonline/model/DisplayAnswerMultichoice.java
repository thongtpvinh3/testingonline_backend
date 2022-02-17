package backend.testingonline.model;

import java.io.Serializable;

import javax.persistence.Column;

public class DisplayAnswerMultichoice implements Serializable{
	
	@Column
	private int id;
	@Column
	private String answer;
	
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
	
}
