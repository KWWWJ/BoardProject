package com.java4.boardproject.comment.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {
	private int id;
	@NonNull
	private String content;
	private Timestamp createdAt;
	private boolean withdrew = false;
	private final int userId;
	private final int boardId;
	private int commentId;
	private List<Comment> children;
	private String name;
	
	public Comment(String content, int userId, int boardId, int commentId) {
		this.content = content;
		this.userId = userId;
		this.boardId = boardId;
		this.commentId = commentId;
	}
}
