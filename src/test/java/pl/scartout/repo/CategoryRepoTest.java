package pl.scartout.repo;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.scartout.model.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryRepoTest {

	@Autowired
	CategoryRepo categoryRepo;
	
	static Category categoryNull;
	static Category category;
	
	@BeforeClass
	public static void before() {
		categoryNull = new Category();
		category = new Category(1L, "Road");
	}

	@Test(expected = ConstraintViolationException.class)
	public void t1shouldNotCreateWithValidation() {
		categoryRepo.save(categoryNull);
	}
	
	@Test
	public void t2shouldSave() {
		categoryRepo.save(category);
	}
	
	@Test
	public void t3shouldFindById() {
		Category category = categoryRepo.findById(1L);
		assertThat(category.getId()).isEqualTo(1L);
		assertThat(category.getName()).isEqualTo("Road");
	}
	
	@Test
	public void t4shouldFindByName() {
		Category category = categoryRepo.findByName("Road");
		assertThat(category.getId()).isEqualTo(1L);
		assertThat(category.getName()).isEqualTo("Road");
	}

}
