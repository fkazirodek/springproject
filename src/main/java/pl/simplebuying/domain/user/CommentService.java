package pl.simplebuying.domain.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

	private UserRepository userRepository;
	private CommentRepository commentRepository;

	private String username;
	
	@Autowired
	public CommentService(UserRepository userRepository, CommentRepository commentRepository) {
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
	}

	public void setReceiverComment(String username) {
		this.username = username;
	}

	public void addCommentToDB(Comment comment, User sender) {
		comment.setReceiver(userRepository.findByUsername(username));
		comment.setSender(userRepository.findByUsername(sender.getUsername()));
		commentRepository.save(comment);
	}

	public List<Comment> getAllReceivedComments(User user) {
		return commentRepository.findByReceiver_id(user.getId());
	}
	
	public List<Comment> getPositiveOrNegativeComments(boolean positive) {
		if(positive) {
			return getPositiveComments();
		} else {
			return getNegativeComments();
		}
	}
	
	private List<Comment> getPositiveComments() {
		return commentRepository.findByPositive(true);
	}

	private List<Comment> getNegativeComments() {
		return commentRepository.findByPositive(false);
	}
}
