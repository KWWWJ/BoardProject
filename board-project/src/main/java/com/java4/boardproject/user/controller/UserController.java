package com.java4.boardproject.user.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java4.boardproject.board.service.BoardService;
import com.java4.boardproject.user.domain.User;
import com.java4.boardproject.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;
	
	@GetMapping("/regist")
	public String registPage(Model model) {
		model.addAttribute("title", "회원 가입");
		model.addAttribute("headLink", "basic/header");
		model.addAttribute("headerHead", "headerFragment");
		model.addAttribute("headerHead", "headerFragmentHead");
		model.addAttribute("path", "/user/regist");
		model.addAttribute("content", "registFragment");
		model.addAttribute("contentHead", "registFragmentHead");
		return "/basic/layout";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("title", "로그인");
		model.addAttribute("headLink", "basic/header");
		model.addAttribute("headerHead", "headerFragment");
		model.addAttribute("headerHead", "headerFragmentHead");
		model.addAttribute("path", "/user/login");
		model.addAttribute("content", "loginFragment");
		model.addAttribute("contentHead", "loginFragmentHead");
		return "/basic/layout";
	}
	
	@PostMapping("/regist")
	public String registPage(@RequestParam Map<String, String> data, Model model) {
		
		int gender = 0;
		
		try {
			if(data.get("gender").equals("1")) {
				gender = 1;
			}
			if(data.get("gender").equals("2")) {
				gender = 2;
			}
			if(!(data.get("gender").equals("1") || data.get("gender").equals("2"))) {
				gender = 3;
			}
				
			String dateTime = null;
			
			Date date = null;
			
			if(data.get("date") != "") {
				dateTime = data.get("date");
				date = Date.valueOf(dateTime);
			}
			
			userService.add(new User(data.get("userId"),
					data.get("password"),
					data.get("name"),
					data.get("phoneNumber"),
					data.get("address"),
					data.get("email"),
					data.get("gitAddress"),
					gender,
					date));
			return "redirect:/";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/regist";
		}
	}
	
	@PostMapping("/login")
	public String loginPage(@RequestParam Map<String, String> data, HttpSession session, Model model) {
		User user = new User();
		user.setUserId(data.get("userId"));
		user.setPassword(data.get("password"));
		user = userService.login(user);
		if(user != null){
			session.setAttribute("userName", user.getName());
			session.setAttribute("id", user.getId());
			model.addAttribute("login", 1);
			System.out.println("로그인 성공");
		}
		else {
			model.addAttribute("login", 0);
			System.out.println("로그인 실패");
		}
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logout(@RequestParam Map<String, String> data, HttpSession session) {
		session.setAttribute("userName", null);
		session.setAttribute("id", null);
		return "redirect:/";
	}

	
}
