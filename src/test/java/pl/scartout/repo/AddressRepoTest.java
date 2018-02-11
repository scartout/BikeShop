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

import pl.scartout.model.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressRepoTest {

	@Autowired
	AddressRepo addressRepo;
	
	static Address addressNull;
	static Address address;
	
	@BeforeClass
	public static void before() {
		addressNull = new Address();
		address = new Address("city", "voivodeship", "country", "street",
				"postcode", "streetNumber", "localNumber");
	}

	@Test(expected = ConstraintViolationException.class)
	public void t1shouldNotCreateWithValidation() {
		addressRepo.save(addressNull);
	}
	
	@Test
	public void t2shouldSave() {
		addressRepo.save(address);
	}
	
	@Test
	public void t3shouldUpdate() {
		addressRepo.updateAddress(1L, "cityUpdated", "voivodeshipUpdated", "country", "street",
				"postcode", "streetNumber", "localNumber");
		Address address = addressRepo.findById(1L);
		assertThat(address.getCity()).isEqualTo("cityUpdated");
		assertThat(address.getVoivodeship()).isEqualTo("voivodeshipUpdated");
	}
	
	@Test
	public void deleteAll() {
		addressRepo.deleteAll();
		assertThat(addressRepo.findAll()).isEmpty();
	}

}
