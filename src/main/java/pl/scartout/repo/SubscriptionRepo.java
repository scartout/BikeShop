package pl.scartout.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Subscription;

@Transactional
@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {	
}