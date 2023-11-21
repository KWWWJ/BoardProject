package com.java4.boardproject.category.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
	private int id;
	private String title;
	private Date createdAt;
}