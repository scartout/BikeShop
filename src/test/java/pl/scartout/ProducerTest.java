package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Producer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {

	@Test
	public void create() {
		Producer producer = new Producer();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Producer.class).verify();
	}
	
}
