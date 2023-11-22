package com.java4.boardproject.board.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java4.boardproject.board.domain.Board;
import com.java4.boardproject.board.service.BoardService;
import com.java4.boardproject.user.domain.User;
import com.java4.boardproject.user.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String boardMainPage(Model model, HttpSession session) {
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
		
		List<Object> subList = new ArrayList<Object>();
		for (int i = 0; i < boardService.getAll().size(); i++) {
			subList.add(userService.get(boardService.getAll().get(i).getUserId()).getUserId());
		}
		
		model.addAttribute("list", boardService.getAll());
		model.addAttribute("userId", subList);
		if(session.getAttribute("login") == null) {
			model.addAttribute("isLogin", 2);
		}else {
			model.addAttribute("isLogin", (Integer)session.getAttribute("login"));
		}
		
	
		return "/basic/layout";
	}
	
	@GetMapping("/add")
	public String boardAdd(Model model) {
		model.addAttribute("headLink", "user/login-box");
		model.addAttribute("headerHead", "loginBoxFragment");
		model.addAttribute("headerHead", "loginBoxFragmentHead");
		model.addAttribute("title", "작성하기");
		model.addAttribute("path", "/board/add");
		model.addAttribute("content", "addFragment");
		model.addAttribute("contentHead", "addFragmentHead");
		
		return "/basic/layout";
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
	
	@PostMapping("/boardedit")
	public void boardEdit(@RequestParam int id, @RequestParam String title, @RequestParam String content, @RequestParam Timestamp createdAt) {
		Board board = boardService.rewrite(id, title, content, createdAt);
		
	}

}
