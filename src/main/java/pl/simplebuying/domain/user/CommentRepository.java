package pl.simplebuying.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByReceiver_id(Long id);
	
	List<Comment> findByPositive(boolean condition);
}
