package backend.testingonline.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Staff;
import backend.testingonline.model.Test;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
	@Modifying
	@Query("SELECT staff FROM Staff staff WHERE staff.username = :username AND staff.password = :password")
	Staff findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
	
	@Modifying
	@Query("x")
	Test createTest();
	
//	@Modifying
//	@Query("x")
	
	
}
