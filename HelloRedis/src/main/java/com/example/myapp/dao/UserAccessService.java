package com.example.myapp.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.example.myapp.dto.User;


public interface UserAccessService {

	public User getUser(int id);
	public void setUser(User user);
	public User deleteUser(User User);
	public void updateUser(int id, User updateUser);
}
