package com.reflectanddy.service;

import javax.annotation.Resource;

import com.reflectanddy.dao.UserDao;
import com.reflectanddy.entity.User;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void save(User user){
		System.out.println("UserService----->save--->"+user);
		userDao.save(user);
	}

	@Override
	public void update() {
		System.out.println("UserService----->update--->");
	}
	
}
