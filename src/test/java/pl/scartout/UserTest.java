package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Test
	public void create() {
		Contact contact = new Contact();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Contact.class).verify();
	}
	
}
