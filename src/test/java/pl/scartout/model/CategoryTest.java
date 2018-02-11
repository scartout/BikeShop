package pl.scartout.model;

import static org.assertj.core.api.Assertions.assertThat;

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
	
	@Test
	public void verifyToString() {
		Category category = new Category();
		category.setId(9999999L);
		category.setName("Road");
		assertThat(category.toString()).isEqualTo("Category - 9999999 - Road");
	}
	
}
