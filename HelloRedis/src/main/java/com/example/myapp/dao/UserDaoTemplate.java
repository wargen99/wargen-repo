package com.example.myapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Repository;

import com.example.myapp.dto.User;

@Repository("userDaoTemplate")
public class UserDaoTemplate implements UserAccessService {

	@Autowired
	private RedisTemplate<Integer, User> redisTemplate;

	public UserDaoTemplate() {
		
	}

	@Override
	public User getUser(int id) {
		User user = redisTemplate.opsForValue().get(id);
		return user;
	}

	@Override
	public void setUser(User user) {
		redisTemplate.opsForValue().set(user.getUserID(), user);

	}

	@Override
	public User deleteUser(User User) {
		redisTemplate.delete(User.getUserID());
		return User;
	}

	@Override
	public void updateUser(int id, User updateUser) {
		redisTemplate.opsForValue().getAndSet(id, updateUser);
		
	}

}
