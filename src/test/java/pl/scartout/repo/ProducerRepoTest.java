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

import pl.scartout.model.Producer;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProducerRepoTest {

	@Autowired
	ProducerRepo producerRepo;
	
	static Producer producerNull;
	static Producer producer;
	
	@BeforeClass
	public static void before() {
		producerNull = new Producer();
		producer = new Producer(1L, "Rose");
	}

	@Test(expected = ConstraintViolationException.class)
	public void t1shouldNotCreateWithValidation() {
		producerRepo.save(producerNull);
	}
	
	@Test
	public void t2shouldSave() {
		producerRepo.save(producer);
	}
	
	@Test
	public void t3shouldFindByName() {
		Producer producer = producerRepo.findByName("Rose");
		assertThat(producer.getId()).isEqualTo(1L);
		assertThat(producer.getName()).isEqualTo("Rose");
	}

}
