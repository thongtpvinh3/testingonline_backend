package backend.testingonline.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Result implements Serializable {

	private Double englishMark;
	private Double codingMark;
	private Double knowledgeMark;

	public Double getEnglishMark() {
		return englishMark;
	}

	public void setEnglishMark(Double englishMark) {
		this.englishMark = englishMark;
	}

	public Double getCodingMark() {
		return codingMark;
	}

	public void setCodingMark(Double codingMark) {
		this.codingMark = codingMark;
	}

	public Double getKnowledgeMark() {
		return knowledgeMark;
	}

	public void setKnowledgeMark(Double knowledgeMark) {
		this.knowledgeMark = knowledgeMark;
	}

}
