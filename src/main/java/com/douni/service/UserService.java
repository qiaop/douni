package com.douni.service;

import com.douni.model.User;

public interface UserService {

	public User getUserInfo(Integer userId);

	public int register(User user);

}
