package pl.scartout.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Comment;

@Transactional
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
}