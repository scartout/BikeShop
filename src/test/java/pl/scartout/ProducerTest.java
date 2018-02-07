package pl.scartout;

import static org.assertj.core.api.Assertions.assertThat;

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
	
	@Test
	public void verifyToString() {
		Producer producer = new Producer();
		producer.setId(9999999L);
		producer.setName("Rose");
		assertThat(producer.toString()).isEqualTo("Producer - 9999999 - Rose");
	}
	
}
