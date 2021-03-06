package pl.simplebuying.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.simplebuying.domain.user.Comment;
import pl.simplebuying.domain.user.CommentService;
import pl.simplebuying.domain.user.User;

@Controller
public class CommentController {

	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/comment/new")
	public String comment(@RequestParam String seller, Model model) {
		commentService.setReceiverComment(seller);
		model.addAttribute("comment", new Comment());
		return "add_comment";
	}

	@PostMapping("/comment/add")
	public String addComment(@ModelAttribute Comment comment, @SessionAttribute User user) {
		commentService.addCommentToDB(comment, user);
		return "redirect:/profile";
	}

	@GetMapping("comment/all")
	public String allComments(@SessionAttribute User user, Model model) {
		model.addAttribute("comments", commentService.getAllReceivedComments(user));
		return "comments";
	}
	
	@PostMapping("/comment/all")
	public String SelectedComments(@RequestParam boolean positive, Model model) {
		model.addAttribute("comments", commentService.getPositiveOrNegativeComments(positive));
		return "comments";
	}
}
