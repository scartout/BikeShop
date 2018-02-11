package pl.scartout.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Contact;

@Transactional
@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
	
	Contact findById(Long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Contact c SET c.phoneNumberFirst=:phoneNumberFirst, c.phoneNumberSecond=:phoneNumberSecond, c.fax=:fax "
    		+ "WHERE c.id=:id")
    int updateContact(@Param ("id") Long id, @Param ("phoneNumberFirst") String phoneNumberFirst,
    		@Param ("phoneNumberSecond") String phoneNumberSecond,	@Param ("fax") String fax);
	
}