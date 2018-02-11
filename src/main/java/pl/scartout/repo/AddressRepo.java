package pl.scartout.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Address;

@Transactional
@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
	
	Address findById(Long id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Address c SET c.city=:city, c.voivodeship=:voivodeship, "
    		+ "c.country=:country, c.street=:street, c.postcode=:postcode, c.streetNumber=:streetNumber, "
    		+ "c.localNumber=:localNumber WHERE c.id=:id")
    int updateAddress(@Param ("id") Long id, @Param ("city") String city, @Param ("voivodeship") String voivodeship,
    		@Param ("country") String country, @Param ("street") String street, @Param ("postcode") String postcode, 
    		@Param ("streetNumber") String streetNumber, @Param ("localNumber") String localNumber);
	
}