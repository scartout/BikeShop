package pl.scartout.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Manufacturer;

@Transactional
@Repository
public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long> {

	Manufacturer findByName(String manufacturerName);
	
}