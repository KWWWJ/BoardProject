package com.java4.boardproject.comment.domain;

import java.sql.Date;

import com.java4.boardproject.board.domain.Board;
import com.java4.boardproject.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {
	private int id;
	private int likes;
	private String content;
	private User writer;
	private Board board;
	private Date createdAt;
}
