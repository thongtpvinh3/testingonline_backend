package backend.testingonline.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Candidate;
import backend.testingonline.model.Staff;
import backend.testingonline.model.Test;
import backend.testingonline.responeexception.ResponeObject;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
	@Modifying
	@Query("SELECT staff FROM Staff staff WHERE staff.username = :username AND staff.password = :password")
	Staff findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
	
//	@Modifying
//	@Query("")s
//	ResponseEntity<ResponeObject> updateTest();
	
	// Lien quan den Candidate
//	List<Candidate> findCandidateByName(String name);
//	List<Candidate> findCandidateByEmail(String email);
//	List<Candidate> findCandidateByPhone(String phone);

	
	//Lien quan den test
//	@Query("SELECT test FROM Test test WHERE test.id_candidate = :id")
//	List<Test> findByCandidateId(@Param("id") Integer id);
	
}
