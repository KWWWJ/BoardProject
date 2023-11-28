package com.java4.boardproject.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java4.boardproject.board.domain.Board;
import com.java4.boardproject.board.service.BoardService;
import com.java4.boardproject.comment.domain.Comment;
import com.java4.boardproject.comment.service.CommentService;
import com.java4.boardproject.user.service.UserService;


import jakarta.servlet.http.HttpSession;


@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;
	@Autowired
	CommentService commentService;
	
	@GetMapping("/")
	public String boardMainPage(@RequestParam(required = false) Integer page, Model model, HttpSession session) {
		if(page == null) {
			page = 1;
		}
		
		if(session.getAttribute("userName") == null) {
			model.addAttribute("headLink", "basic/header");
			model.addAttribute("headerHead", "headerFragment");
			model.addAttribute("headerHead", "headerFragmentHead");
		}
		else {
			String userName = (String)session.getAttribute("userName");
			model.addAttribute("headLink", "user/login-box");
			model.addAttribute("headerHead", "loginBoxFragment");
			model.addAttribute("headerHead", "loginBoxFragmentHead");
			model.addAttribute("userName", userName);
		}
		model.addAttribute("title", "게시판");
		model.addAttribute("path", "/board/index");
		model.addAttribute("content", "boardFragment");
		model.addAttribute("contentHead", "boardFragmentHead");
		
		model.addAttribute("pageLength", boardService.getPageCount(10));
		
		model.addAttribute("list", boardService.getPage((page-1)*10));
		
		
		
		if(session.getAttribute("login") == null) {
			model.addAttribute("isLogin", 2);
		}else {
			model.addAttribute("isLogin", (Integer)session.getAttribute("login"));
		}

		session.setAttribute("login", null);
	
		return "/basic/layout";
	}
	
	@GetMapping("/add")
	public String boardAdd(Model model, HttpSession session) {
		String userName = (String)session.getAttribute("userName");
		model.addAttribute("userName", userName);
		model.addAttribute("headLink", "user/login-box");
		model.addAttribute("headerHead", "loginBoxFragment");
		model.addAttribute("headerHead", "loginBoxFragmentHead");
		model.addAttribute("title", "작성하기");
		model.addAttribute("path", "/board/add");
		model.addAttribute("content", "addFragment");
		model.addAttribute("contentHead", "addFragmentHead");
		
		return "/basic/layout";
	}
	
	@GetMapping("/edit")
	public String boardEdit(Model model, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/";
		}
		else {
			if(session.getAttribute("userName") != null) {
				String userName = (String)session.getAttribute("userName");
				model.addAttribute("headLink", "user/login-box");
				model.addAttribute("headerHead", "loginBoxFragment");
				model.addAttribute("headerHead", "loginBoxFragmentHead");
				model.addAttribute("userName", userName);
				model.addAttribute("title", "수정하기");
				model.addAttribute("path", "/board/edit");
				model.addAttribute("content", "editFragment");
				model.addAttribute("contentHead", "editFragmentHead");
				
				model.addAttribute("titleBox", boardService.get((Integer)session.getAttribute("nowPageId")).getTitle());
				model.addAttribute("contentBox", boardService.get((Integer)session.getAttribute("nowPageId")).getContent());
			}
			return "/basic/layout";
		}
	}
	
	@GetMapping("/content")
	public String contentPage(@RequestParam(required = false) Integer id, Model model, HttpSession session) {
		if(id == null) {
			id = 1;
		}
		
		if(session.getAttribute("userName") == null) {
			model.addAttribute("headLink", "basic/header");
			model.addAttribute("headerHead", "headerFragment");
			model.addAttribute("headerHead", "headerFragmentHead");
		}
		else {
			String userName = (String)session.getAttribute("userName");
			model.addAttribute("headLink", "user/login-box");
			model.addAttribute("headerHead", "loginBoxFragment");
			model.addAttribute("headerHead", "loginBoxFragmentHead");
			model.addAttribute("userName", userName);
		}
		model.addAttribute("title", "게시글");
		model.addAttribute("path", "/board/content");
		model.addAttribute("content", "contentFragment");
		model.addAttribute("contentHead", "contentFragmentHead");
		model.addAttribute("checkId", session.getAttribute("id"));
		
		Board board = boardService.get(id);
		
		board.setContent(board.getContent().replace("\n", "<br>"));
		
		model.addAttribute("contentBox", board);
		model.addAttribute("pageId", boardService.get(id).getUserId());
		model.addAttribute("isLogin", 2);
		
//		if(Integer.parseInt((String)session.getAttribute("id")) != boardService.get(id).getUserId()) {
//			
//		}
		
//		List<Comment> main = new ArrayList<>();
//		List<Comment> sub = new ArrayList<>();
//		for (int i = 0; i < commentService.getBoardAll(id).size(); i++) {
//			for (int j = 0; j < commentService.getGroupAll(i+1).size(); j++) {
//				System.out.println(commentService.getGroupAll(i+1).get(j).getIndex());
//				if(commentService.getGroupAll(i+1).get(j).getIndex() == 1) {
//					main.add(commentService.getGroupAll(i+1).get(0));
////					System.out.println("main input");
//				}
//				else {
//					sub.add(commentService.getGroupAll(i+1).get(j));
////					System.out.println("sub input");
//				}
//			}
//		}
//
//		System.out.println("main : "+main);
//		System.out.println("sub : "+sub);
//		model.addAttribute("comment-main", main);
//		model.addAttribute("comment-sub", sub);
		
		session.setAttribute("nowPageId", id);
		return "/basic/layout";
	}
	
	@GetMapping("/board/{boardId}")
	public String itemPage(@PathVariable("boardId") int boardId, Model model, HttpSession session) {
		Board boaed = boardService.get(boardId);
		
		if(session.getAttribute("userName") == null) {
			model.addAttribute("headLink", "basic/header");
			model.addAttribute("headerHead", "headerFragment");
			model.addAttribute("headerHead", "headerFragmentHead");
		}
		else {
			String userName = (String)session.getAttribute("userName");
			model.addAttribute("headLink", "user/login-box");
			model.addAttribute("headerHead", "loginBoxFragment");
			model.addAttribute("headerHead", "loginBoxFragmentHead");
			model.addAttribute("userName", userName);
		}
		model.addAttribute("title", "게시글");
		model.addAttribute("path", "/board/content");
		model.addAttribute("content", "contentFragment");
		model.addAttribute("contentHead", "contentFragmentHead");
		
		return "redirect:/";
	}
	
	
	
	@GetMapping("/notice")
	public String noticePage(Model model) {
		model.addAttribute("headLink", "user/login-box");
		model.addAttribute("headerHead", "loginBoxFragment");
		model.addAttribute("headerHead", "loginBoxFragmentHead");
		model.addAttribute("title", "공지사항");
		model.addAttribute("path", "board/notice");
		model.addAttribute("content", "noticeFragment");
		model.addAttribute("contentHead", "noticeFragmentHead");
		
		return "/basic/layout";
	}
	
	@PostMapping("/add")
	public String boardAdd(@RequestParam Map<String, String> data, HttpSession session) {
		
		if(session.getAttribute("id") == null) {
			return "redirect:/";
		}
		
		int userId = (Integer)session.getAttribute("id");
		
		boardService.add(new Board(userId, data.get("title"), data.get("content")));
		
		return "redirect:/";
		
	}
	
	@PostMapping("/edit")
	public String boardEdit(@RequestParam Map<String, String> data, Model model, HttpSession session) {
		Board boardOwn = boardService.get((Integer)session.getAttribute("nowPageId"));
		Board board = boardService.rewrite(boardOwn.getId(),data.get("title"), data.get("content"));
		
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String boarddelete(HttpSession session) {
		boardService.delete((Integer)session.getAttribute("nowPageId"));
		
		return "redirect:/";
	}

}
