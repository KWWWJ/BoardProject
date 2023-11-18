package com.java4.boardproject.user.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
	private int id;
	private String userId;
	private String password;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String gitAddress;
	private boolean gender;
	private Date createdAt;
	private Date brith;
}
