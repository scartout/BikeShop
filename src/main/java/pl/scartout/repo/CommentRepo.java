package pl.scartout.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Comment;
import pl.scartout.model.Product;

@Transactional
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

	List<Comment> findByProductIdOrderByDateDesc(long id);
	
	@Query("SELECT count(c) FROM Comment c WHERE c.product=:product")
	Long countCommentsByProduct(@Param("product") Product product);
	
	@Query("SELECT avg(c.vote) FROM Comment c WHERE c.product=:product")
	double avgCommentsByProduct(@Param("product") Product product);
}