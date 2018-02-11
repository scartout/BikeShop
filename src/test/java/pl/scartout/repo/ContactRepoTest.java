package pl.scartout.repo;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.runners.MethodSorters;

import pl.scartout.model.Contact;
import pl.scartout.repo.ContactRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactRepoTest {

	@Autowired
	ContactRepo contactRepo;
	
	static Contact contactNull;
	static Contact contact;
	
	@BeforeClass
	public static void before() {
		contactNull = new Contact();
		contact = new Contact("555-555-555", null, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void t1shouldNotCreateWithValidation() {
		contactRepo.save(contactNull);
	}
	
	@Test
	public void t2shouldCreate() {
		contactRepo.save(contact);
	}
	
	@Test
	public void t3shouldUpdate() {
		contactRepo.updateContact(1L, "666-666-666", null, null);
		Contact contact = contactRepo.findById(1L);
		assertThat(contact.getPhoneNumberFirst()).isEqualTo("666-666-666");
	}

}
