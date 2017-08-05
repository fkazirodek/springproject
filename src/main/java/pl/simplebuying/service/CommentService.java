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

	public void addCommentToDB(Comment comment) {
		comment.setUser(userRepository.findByUsername(username));
		commentRepository.save(comment);
	}

	public List<Comment> getAllComments(User user) {
		return commentRepository.findByUser_id(user.getId());
	}

	public List<Comment> getPositiveComments() {
		return commentRepository.findByPositive(true);
	}
	
	public List<Comment> getNegativeComments() {
		return commentRepository.findByPositive(false);
	}
}
