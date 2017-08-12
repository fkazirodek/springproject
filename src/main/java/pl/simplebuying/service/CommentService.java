package pl.simplebuying.service;

import java.util.List;

import pl.simplebuying.model.Comment;
import pl.simplebuying.model.User;

public interface CommentService {

	public void addCommentToDB(Comment comment, User sender);
	public List<Comment> getAllReceivedComments(User user);
	public List<Comment> getPositiveOrNegativeComments(boolean positive);
	public void setReceiverComment(String username);
}
