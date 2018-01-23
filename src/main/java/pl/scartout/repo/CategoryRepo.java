package pl.scartout.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Category;

@Transactional
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	Category findById(Long id);
	
	Category findByName(String string);
	
	@Query("SELECT id FROM Category c WHERE c.name=:name")
	Long idByName(@Param("name") String name);

}