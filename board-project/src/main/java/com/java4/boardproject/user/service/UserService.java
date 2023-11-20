package com.java4.boardproject.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java4.boardproject.user.dao.UserDAO;
import com.java4.boardproject.user.domain.User;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	public void add(User user) {
		userDAO.add(user);
	}
	
	public List<User> getAll(){
		return userDAO.getAll();
	}
	
	public User login(User user) {
		User tempUser = new User();
		for(int i=1; i< userDAO.getAll().size(); i++) {
			if((user.getUserId().equals(userDAO.getAll().get(i).getUserId()))&&(user.getPassword().equals(userDAO.getAll().get(i).getPassword()))) {
				tempUser= userDAO.getAll().get(i);
				return tempUser;
			}
		}
		return null;
	}
}
