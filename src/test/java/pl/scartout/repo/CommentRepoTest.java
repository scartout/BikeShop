package pl.scartout.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.scartout.model.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentRepoTest {

	@Autowired
	CommentRepo commentRepo;

	static Comment commentNull;
	static Comment comment1;
	static Comment comment2;
	static Comment comment3;
	
	@BeforeClass
	public static void test() {
		commentNull = new Comment();
		comment1 = new Comment("description1", new Date(), 5);
		comment2 = new Comment("description2", new Date(), 3.5);
		comment3 = new Comment("description3", new Date(), 4);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void t1shouldNotCreateProductWithValidation() {
		commentRepo.save(commentNull);
	}
	
	@Test
	public void t2shouldSave() {
		commentRepo.save(comment1);
	}
	
	@Test
	public void t3deleteAll() {
		commentRepo.deleteAll();
		assertThat(commentRepo.findAll()).isEmpty();
	}

}
