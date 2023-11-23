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
import com.java4.boardproject.user.service.UserService;


import jakarta.servlet.http.HttpSession;


@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;
	
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
		
//		List<Object> subList = new ArrayList<Object>();
//		for (int i = 0; i < boardService.getAll().size(); i++) {
//			subList.add(userService.get(boardService.getAll().get(i).getUserId()).getUserId());
//		}
//		model.addAttribute("userId", subList);
		System.out.println(boardService.getAll().size());
		model.addAttribute("pageLength", (boardService.getAll().size()/10)+1);
		
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
	
	@GetMapping("/edit")
	public String boardEdit(Model model, HttpSession session) {
		if(session.getAttribute("id") == null) {
			System.out.println("get edit : 로그인이 되어있지 않음");
			return "redirect:/";
		}
		else {
			if(session.getAttribute("userName") != null) {
				System.out.println("get edit : "+session.getAttribute("nowPageId"));
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
		model.addAttribute("contentBox", boardService.get(id));
		model.addAttribute("pageId", boardService.get(id).getUserId());
		model.addAttribute("isLogin", 2);
		
		session.setAttribute("nowPageId", id);
		System.out.println("page id : "+session.getAttribute("nowPageId"));
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
	
	@PostMapping("/edit")
	public String boardEdit(@RequestParam Map<String, String> data, Model model, HttpSession session) {
		Board boardOwn = boardService.get((Integer)session.getAttribute("nowPageId"));
		System.out.println("수정된 제목 : "+data.get("title"));
		Board board = boardService.rewrite(boardOwn.getId(),data.get("title"), data.get("content"));
		
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String boarddelete(HttpSession session) {
		boardService.delete((Integer)session.getAttribute("nowPageId"));
		System.out.println("삭제되었습니다.");
		
		return "redirect:/";
	}

}
