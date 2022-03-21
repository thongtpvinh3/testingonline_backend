package backend.testingonline.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@SuppressWarnings("serial")
@Table(name = "candidate_test")
public class CandidateTest implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "id_candidate")
	private int candidateId;
	@Column(name = "id_test")
	private int testId;
	@Column(name = "marks", columnDefinition = "DOUBLE DEFAULT 0")
	private Double marks;
	
	@ManyToOne
	@JoinColumn(name = "id_candidate", insertable = false, updatable = false)
	private CandidateTestCandidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "id_test", insertable = false, updatable = false)
	private CandidateTestTest test;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public Double getMarks() {
		return marks;
	}
	public void setMarks(Double marks) {
		this.marks = marks;
	}
	
	public CandidateTestCandidate getCandidate() {
		return candidate;
	}
	public void setCandidate(CandidateTestCandidate candidate) {
		this.candidate = candidate;
	}
	public CandidateTestTest getTest() {
		return test;
	}
	public void setTest(CandidateTestTest test) {
		this.test = test;
	}
	
}
