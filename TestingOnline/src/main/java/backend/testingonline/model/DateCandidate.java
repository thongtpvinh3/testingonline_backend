package backend.testingonline.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class DateCandidate implements Serializable {

	private LocalDate date;
	private Set<Candidate> candidates = new HashSet<>();

	
	public DateCandidate() {
	}
	
	public DateCandidate(LocalDate date) {
		this.date = date;
	}
	
	public DateCandidate(LocalDate date, Set<Candidate> candidates) {
		this.date = date;
		this.candidates = candidates;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

}
