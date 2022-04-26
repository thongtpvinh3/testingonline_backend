package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
	@Query("SELECT staff FROM Staff staff WHERE staff.username = :username AND staff.password = :password")
	Staff findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

	Staff findByUsername(String username);

//	UserDetails findByUsername(String username);
}
