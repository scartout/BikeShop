package pl.scartout.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Manufacturer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManufacturerTest {

	@Test
	public void create() {
		Manufacturer manufacturer = new Manufacturer();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Manufacturer.class).verify();
	}
	
	@Test
	public void verifyToString() {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setId(9999999L);
		manufacturer.setName("Rose");
		assertThat(manufacturer.toString()).isEqualTo("Manufacturer - 9999999 - Rose");
	}
	
}
