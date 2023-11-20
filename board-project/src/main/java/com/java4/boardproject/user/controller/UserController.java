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

import com.java4.boardproject.user.domain.User;
import com.java4.boardproject.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/regist")
	public String registPage(Model model) {
		model.addAttribute("title", "회원 가입");
		model.addAttribute("path", "/board/regist");
		model.addAttribute("content", "registFragment");
		model.addAttribute("contentHead", "registFragmentHead");
		return "/basic/layout";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("title", "로그인");
		model.addAttribute("path", "/board/login");
		model.addAttribute("content", "loginFragment");
		model.addAttribute("contentHead", "loginFragmentHead");
		return "/basic/layout";
	}
	
	@PostMapping("/regist")
	public String registPage(@RequestParam Map<String, String> data) {
		
		boolean gender = false;
		
		User user = new User();
		user.setUserId(data.get("userId"));
		
		for(int i=0; i<userService.getAll().size(); i++) {
			if(user.getUserId().equals(userService.getAll().get(i).getUserId()) == false) {
				return "redirect:/";
			}
		}
		
		
		if(!(data.get("gender").equals("0"))) {
			gender = true;
		}
			
		String dateTime = data.get("date");
		
		Date date = Date.valueOf(dateTime);
		
		System.out.println("회원 가입 시도중");
		
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
	}
	
	@PostMapping("/login")
	public String loginPage(@RequestParam Map<String, String> data, HttpSession session) {
		User user = new User();
		user.setUserId(data.get("userId"));
		user.setPassword(data.get("password"));
		user = userService.login(user);
		if(user != null){
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("id", user.getId());
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logout(@RequestParam Map<String, String> data, HttpSession session) {
		session.setAttribute("userId", null);
		session.setAttribute("id", null);
		return "redirect:/";
	}

	
}
