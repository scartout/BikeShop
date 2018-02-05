package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {

	@Test
	public void create() {
		Category category = new Category();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Category.class).verify();
	}
	
}
