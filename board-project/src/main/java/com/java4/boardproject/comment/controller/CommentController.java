package com.java4.boardproject.comment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java4.boardproject.comment.domain.Comment;
import com.java4.boardproject.comment.domain.ResponeseComment;
import com.java4.boardproject.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@GetMapping
	@ResponseBody
	public ResponeseComment getComment(@RequestParam Map<String, String> data) {
		ResponeseComment res = new ResponeseComment(
				 commentService.getComments(
						 Integer.parseInt(data.get("id")),
						 Integer.parseInt(data.get("start"))
						 ), commentService.getCount(Integer.parseInt(data.get("id "))) <= Integer.parseInt(data.get("start"))+5);
		return res;
	}
	
	@GetMapping("th")
	public String getComment(@RequestParam Map<String, String> data, Model model) {
		
		return "comment/list";
	}
	
	
	@PostMapping("add")
	public String addComment(@RequestParam Map<String, String> data, HttpSession session) {
		
		String id = String.valueOf(session.getAttribute("id"));
		
		String commentId = data.get("comment_id");
		if(session.getAttribute("id") != null) {
			if(commentId == null) {
				Comment comment = new Comment(
						data.get("comment-input"),
						Integer.parseInt(id),
						Integer.parseInt(data.get("board_id"))
								);
				commentService.add(comment);
			}
			else {
				Comment comment = new Comment(
						data.get("comment-input"),
						Integer.parseInt(id),
						Integer.parseInt(data.get("board_id")),
						Integer.parseInt(data.get("comment_id"))
								);
				commentService.add(comment);
			}
		}
		
		
		return "redirect:/content?id="+data.get("board_id");
	}
}
