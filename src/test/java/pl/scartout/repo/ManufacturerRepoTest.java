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

import pl.scartout.model.Manufacturer;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManufacturerRepoTest {

	@Autowired
	ManufacturerRepo manufacturerRepo;
	
	static Manufacturer producerNull;
	static Manufacturer manufacturer;
	
	@BeforeClass
	public static void before() {
		producerNull = new Manufacturer();
		manufacturer = new Manufacturer(1L, "Rose");
	}

	@Test(expected = ConstraintViolationException.class)
	public void t1shouldNotCreateWithValidation() {
		manufacturerRepo.save(producerNull);
	}
	
	@Test
	public void t2shouldSave() {
		manufacturerRepo.save(manufacturer);
	}
	
	@Test
	public void t3shouldFindByName() {
		Manufacturer manufacturer = manufacturerRepo.findByName("Rose");
		assertThat(manufacturer.getId()).isEqualTo(1L);
		assertThat(manufacturer.getName()).isEqualTo("Rose");
	}

}
