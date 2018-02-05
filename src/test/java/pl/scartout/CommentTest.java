package pl.scartout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.jqno.equalsverifier.EqualsVerifier;

import pl.scartout.model.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {

	@Test
	public void create() {
		Comment comment = new Comment();
	}
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.forClass(Comment.class).verify();
	}
	
}
