package com.java4.boardproject.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java4.boardproject.board.dao.BoardDAO;
import com.java4.boardproject.comment.dao.CommentDAO;
import com.java4.boardproject.comment.domain.Comment;

@Service
public class CommentService {
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	BoardDAO boardDAO;
	
	public void add(Comment comment) {
		commentDAO.add(comment);
	}
	
	public Comment get(int id) {
		return commentDAO.get(id);
	}
	
//	public Comment getGroup(int group) {
//		return commentDAO.get(group);
//	}
	
	public List<Comment> getComments(int boardId, int start){
		List<Comment> list =  commentDAO.getParents(boardId, start);
		list.forEach((item)->{
			item.setChildren(getChildren(boardId, item));
		});
		return list;
	}
	
	private List<Comment> getChildren(int boardId, Comment comment){
		List<Comment> list = commentDAO.getChildren(boardId,  comment.getId());
		list.forEach((item)->{
			item.setChildren(getChildren(boardId, item));
		});
		return list;
	}
	
	public int getCount(int boardId) {
		return commentDAO.getCount(boardId);
	}
	
	public List<Comment> getChildren(int boardId, int commentId){
		return commentDAO.getChildren(boardId, commentId);
	}
}
