package pl.scartout.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {

	@Test
	public void create() {
		Role role = new Role();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Role.class).verify();
	}
	
	@Test
	public void verifyToString() {
		Role role = new Role();
		role.setId(9999999L);
		role.setRole("user");
		assertThat(role.toString()).isEqualTo("Role - 9999999 - user");
	}
}
