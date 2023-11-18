package com.java4.boardproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	@GetMapping("/")
	public String board(Model model) {
		model.addAttribute("title", "게시판");
		return "/basic/layout";
	}
}
