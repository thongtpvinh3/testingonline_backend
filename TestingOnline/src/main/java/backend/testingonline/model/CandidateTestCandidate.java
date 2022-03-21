package backend.testingonline.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "candidate")
public class CandidateTestCandidate implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
	private List<CandidateTest> candidateTests;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CandidateTest> getCandidateTests() {
		return candidateTests;
	}

	public void setCandidateTests(List<CandidateTest> candidateTests) {
		this.candidateTests = candidateTests;
	}
	
}
