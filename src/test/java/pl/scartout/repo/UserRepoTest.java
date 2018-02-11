package pl.scartout.repo;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.runners.MethodSorters;

import pl.scartout.model.User;
import pl.scartout.repo.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepoTest {

	@Autowired
	UserRepo userRepo;
	
	static User userNull;
	
	@BeforeClass
	public static void before() {
		userNull = new User();
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void t1shouldNotCreateWithValidation() {
		userRepo.save(userNull);
	}

}
