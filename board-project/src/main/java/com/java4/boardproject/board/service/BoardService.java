package com.java4.boardproject.board.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java4.boardproject.board.dao.BoardDAO;
import com.java4.boardproject.board.domain.Board;

@Service
public class BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	public void add(Board board) {
		boardDAO.add(board);
	}
	
	public Board get(int id) {
		Board board = boardDAO.get(id);
		return board;
	}
	
	public Board rewrite(int id, String title, String content, Timestamp createdAt) {
		Board board = boardDAO.get(id);
		
		board.setTitle(title);
		board.setContent(content);
		board.setCreatedAt(createdAt);
		
		return board;
	}
	
	public List<Board> getAll(){
		return boardDAO.getAll();
	}
}
