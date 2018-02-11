package pl.scartout.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

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
	
	@Test
	public void verifyToString() {
		Comment comment = new Comment();
		comment.setId(9999999L);
		assertThat(comment.toString()).isEqualTo("Comment - 9999999");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotCreateWithVoteToSmall() {
		Comment comment = new Comment("description1", new Date(), 0.5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotCreateWithVoteToBig() {
		Comment comment = new Comment("description1", new Date(), 5.5);
	}
	
	@Test
	public void shouldCreateWithLowerLimit() {
		Comment comment = new Comment("description1", new Date(), 1);
	}
	
	@Test
	public void shouldCreateWithUpperLimit() {
		Comment comment = new Comment("description1", new Date(), 5);
	}
	
}
