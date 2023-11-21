package com.java4.boardproject.user.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	@NonNull
	private String userId;
	@NonNull
	private String password;
	@NonNull
	private String name;
	@NonNull
	private String phone;
	private String address;
	@NonNull
	private String email;
	private String gitAddress;
	private int gender;
	private Date birth=null;
	private Timestamp createdAt;
	
	public User(String userId,
			String password,
			String name,
			String phone,
			String address,
			String email,
			String gitAddress,
			int gender,
			Date birth) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.gitAddress = gitAddress;
		this.gender = gender;
		this.birth = birth;
	}

}
