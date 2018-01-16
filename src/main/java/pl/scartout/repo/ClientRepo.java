package pl.scartout.repo;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import pl.scartout.model.Client;
 
@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}