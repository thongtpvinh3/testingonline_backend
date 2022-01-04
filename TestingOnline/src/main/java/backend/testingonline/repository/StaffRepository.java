package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Staff;

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
