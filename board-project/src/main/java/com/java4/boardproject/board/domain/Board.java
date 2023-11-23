package com.java4.boardproject.board.domain;


import java.sql.Timestamp;

import com.java4.boardproject.category.domain.Category;
import com.java4.boardproject.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Board {
	
	private int id;
	@NonNull
	private int userId;
	@NonNull
	private String title;
	@NonNull
	private String content;
	private int views = 0;
//	private int likes = 0;
//	private int hates = 0;
	private boolean isWithdrew = false;
	private Timestamp createdAt;
	private String userName;
	private String usersUserId;

}
