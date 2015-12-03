package com.example.myapp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.example.myapp.dto.User;

@Repository("userDaoCache")
public class UserDaoCache implements UserAccessService {

	// 임의의 Database로 가정
	private static  HashMap<Integer, User> USERMAP = new HashMap<Integer, User>();

	/*private HashMap<Integer, User> getUserMap() {
		if (userMap == null)
			userMap = new HashMap<Integer, User>();
		return userMap;
	}*/

	public UserDaoCache() {

	}

	@Override
	@Cacheable(value = "users", key = "#id")
	public User getUser(int id) {
		User user = USERMAP.get(id);
		System.out.println("This user is come from HashMap : "
				+ user.getUserName());
		return user;
	}

	
	@Override
	public void setUser(User user) {
		USERMAP.put(user.getUserID(), user);
	}

	@Override
	@CacheEvict(value = "users", allEntries=true)
	public User deleteUser(User user) {
		USERMAP.remove(user.getUserID());
		return null;
	}

	@Override
	@CacheEvict(value = "users", allEntries=true)
	public void updateUser(int id, User updateUser) {
		USERMAP.replace(id, updateUser);

	}

}
