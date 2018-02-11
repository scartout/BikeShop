package pl.scartout.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactTest {

	@Test
	public void create() {
		Contact contact = new Contact();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Contact.class).verify();
	}
	
	@Test
	public void verifyToString() {
		Contact contact = new Contact();
		contact.setId(9999999L);
		assertThat(contact.toString()).isEqualTo("Contact - 9999999");
	}
	
}
