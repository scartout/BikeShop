package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

	@Test
	public void create() {
		Order order = new Order();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Order.class).verify();
	}
	
}
