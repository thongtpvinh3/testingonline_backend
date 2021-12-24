package backend.testingonline.service;

import java.util.Optional;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Staff;

public interface StaffService {
	
	Optional<Candidate> findById(int id);
	Staff findByUsernameAndPassword(String username, String password);
	boolean login(String username, String password);
}
