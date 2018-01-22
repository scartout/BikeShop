package pl.scartout.repo;
 
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.scartout.model.User;
 
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
	User findById(Long id);

	String findUsernameById(long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query("UPDATE User c SET c.password=:password, c.email=:email, c.firstName=:firstName, "
    		+ "c.lastName=:lastName WHERE c.id = :id")
    int updateUser(@Param("id") Long id, @Param("password") String password, @Param("email") String email, 
    		@Param("firstName") String firstName, @Param("lastName") String lastName);

}