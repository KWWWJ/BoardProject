package com.java4.boardproject.board.domain;

import java.security.Timestamp;
import java.sql.Date;

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
	private int veiws;
	private int likes;
	private int hates;
	@NonNull
	private String title;
	@NonNull
	private String content;
	private Timestamp createdAt;
	private User writer;
	private boolean isWuthdrew = false;
	private Category category;
	@NonNull
	private int usrtId;
	
}
