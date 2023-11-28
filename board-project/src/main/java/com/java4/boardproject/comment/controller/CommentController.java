package com.java4.boardproject.comment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java4.boardproject.comment.domain.Comment;
import com.java4.boardproject.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@GetMapping
	@ResponseBody
	public List<Comment> getComment(@RequestParam Map<String, String> data) {
		return commentService.getComments(Integer.parseInt(data.get("id")));
	}
	
	@PostMapping("add")
	public String addComment(@RequestParam Map<String, String> data, HttpSession session) {
		System.out.println("user id :"+data.get("user_id"));
		System.out.println("board id :"+data.get("board_id"));
		System.out.println("comment id :"+data.get("comment_id"));
		System.out.println("comment input :"+data.get("comment-input"));
		
		String commentId = data.get("comment_id");
		if(session.getAttribute("id") != null) {
			if(commentId == null) {
				Comment comment = new Comment(
						data.get("comment-input"),
						Integer.parseInt((String)session.getAttribute("id")),
						Integer.parseInt(data.get("board_id"))
								);
				commentService.add(comment);
			}
			else {
				Comment comment = new Comment(
						data.get("comment-input"),
						Integer.parseInt((String)session.getAttribute("id")),
						Integer.parseInt(data.get("board_id")),
						Integer.parseInt(data.get("comment_id"))
								);
				commentService.add(comment);
			}
		}
		
		
		return "redirect:/content?id="+data.get("board_id");
	}
}
