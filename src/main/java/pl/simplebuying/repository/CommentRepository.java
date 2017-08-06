package pl.simplebuying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.simplebuying.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByReceiver_id(Long id);
	
	List<Comment> findByPositive(boolean condition);
}
