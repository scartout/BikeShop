package pl.scartout.repo;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runners.MethodSorters;

import pl.scartout.model.Role;
import pl.scartout.repo.RoleRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleRepoTest {

	@Autowired
	RoleRepo roleRepo;
	
	static Role roleNull;
	static Role role1;
	static Role role2;
	
	@BeforeClass
	public static void before() {
		roleNull = new Role();
		role1 = new Role(1L, "admin");
		role2 = new Role(2L, "user");
	}
	
	@Test(expected = JpaSystemException.class)
	public void t1shouldNotCreateWithValidation() {
		roleRepo.save(roleNull);
	}
	
	@Test
	public void t2shouldSave() {
		roleRepo.save(role1);
		roleRepo.save(role2);
	}
	
	@Test
	public void t3shouldFindByRole() {
		Role role = roleRepo.findByRole("user");
		assertThat(role.getRole()).isEqualTo("user");
	}

}
