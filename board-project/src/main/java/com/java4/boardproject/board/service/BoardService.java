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
	
	public int getPageCount(int count) {
		return (boardDAO.getCount()-1)/10+1;
	}
	
	public Board rewrite(int id, String title, String content) {
		Board board = boardDAO.get(id);
		
		board.setTitle(title);
		board.setContent(content);
		
		boardDAO.update(board);
		
		return board;
	}
	
	public List<Board> getAll(){
		return boardDAO.getAll();
	}
	
	public List<Board> getPage(int start){
		return boardDAO.getAll(start);
	}
	
	public void delete(int id) {
		boardDAO.delete(id);
	}
}
