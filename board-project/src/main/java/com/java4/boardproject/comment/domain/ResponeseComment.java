package com.java4.boardproject.comment.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponeseComment {
	private List<Comment> list;
	private boolean isEnd;
}
