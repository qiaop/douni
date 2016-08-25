package com.douni.dao;

import com.douni.model.User;

public interface UserDao extends BaseDao<User> {
	
	public User getUserInfo(Integer userId);

}
