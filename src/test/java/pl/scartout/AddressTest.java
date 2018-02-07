package pl.scartout;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressTest {

	@Test
	public void create() {
		Address address = new Address();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Address.class).verify();
	}
	
	@Test
	public void verifyToString() {
	    Address address = new Address();
	    address.setId(9999999L);
	    assertThat(address.toString()).isEqualTo("Address - 9999999");
	}
	
}
