package pl.scartout.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Subscription;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionTest {
	
	@Test
	public void create() {
		Subscription subscription = new Subscription();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Subscription.class).verify();
	}
	
	@Test
	public void verifyToString() {
	    Subscription subscription = new Subscription();
	    subscription.setId(9999999L);
	    subscription.setEmail("email@gmail.com");
	    assertThat(subscription.toString()).isEqualTo("Subscription - 9999999 - email@gmail.com");
	}
	
}
