package pl.scartout.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import pl.scartout.model.Subscription;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SubscriptionRepoTest {

	@Autowired
	SubscriptionRepo subscriptionRepo;
	
	static Subscription subscriptionNull;
	static Subscription subscription;
	
	@BeforeClass
	public static void before() {
		subscriptionNull = new Subscription();
		subscription = new Subscription("email@gmail.com");
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void t1shouldNotCreateWithValidation() {
		subscriptionRepo.save(subscriptionNull);
	}
	
	@Test
	public void t2shouldSave() {
		subscriptionRepo.save(subscription);
	}
	
	@Test
	public void deleteAll() {
		subscriptionRepo.deleteAll();
		assertThat(subscriptionRepo.findAll()).isEmpty();
	}

}
