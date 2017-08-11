package pl.simplebuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.simplebuying.model.Comment;
import pl.simplebuying.model.User;
import pl.simplebuying.repository.CommentRepository;
import pl.simplebuying.repository.UserRepository;

@Service
public class CommentService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;

	private String username;

	public void setUsername(String username) {
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

	public List<Comment> getSelectComments(boolean positive) {
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
