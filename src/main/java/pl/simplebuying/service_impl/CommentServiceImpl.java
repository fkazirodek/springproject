package pl.simplebuying.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Comment;
import pl.simplebuying.model.User;
import pl.simplebuying.repository.CommentRepository;
import pl.simplebuying.repository.UserRepository;
import pl.simplebuying.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private UserRepository userRepository;
	private CommentRepository commentRepository;

	private String username;
	
	@Autowired
	public CommentServiceImpl(UserRepository userRepository, CommentRepository commentRepository) {
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
	}


	@Override
	public void setReceiverComment(String username) {
		this.username = username;
	}

	@Override
	public void addCommentToDB(Comment comment, User sender) {
		comment.setReceiver(userRepository.findByUsername(username));
		comment.setSender(userRepository.findByUsername(sender.getUsername()));
		commentRepository.save(comment);
	}

	@Override
	public List<Comment> getAllReceivedComments(User user) {
		return commentRepository.findByReceiver_id(user.getId());
	}
	
	@Override
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
