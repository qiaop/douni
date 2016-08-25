package com.douni.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.douni.dao.UserDao;
import com.douni.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getUserInfo(Integer userId) {

		String hql = "from User  where id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", userId);

		User user = get(hql, params);

		return user;
	}

}
