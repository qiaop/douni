package com.douni.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.douni.dao.UserDao;
import com.douni.model.User;
import com.douni.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public User getUserInfo(Integer userId) {

		return userDao.getUserInfo(userId);
	}

	@Override
	public int register(User user) {

		try {
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return 0;
	}

}
