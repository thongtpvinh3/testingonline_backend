package backend.testingonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.testingonline.model.Levels;

@Repository
public interface LevelRepository extends JpaRepository<Levels, Integer> {
	
}
