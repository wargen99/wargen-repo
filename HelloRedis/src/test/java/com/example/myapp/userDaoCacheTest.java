package com.example.myapp;

import static org.junit.Assert.*;

import org.hamcrest.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.myapp.dao.UserAccessService;
import com.example.myapp.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "file:D:/Essensial Program/workspace_test/HelloRedis/src/main/webapp/WEB-INF/spring/appServlet/RedisConfigure.xml"})
public class userDaoCacheTest {


	@Autowired
	@Qualifier("userDaoCache")
	private UserAccessService redisCache;
	
	@Autowired
	@Qualifier("userDaoTemplate")
	private UserAccessService redisTemplate;
	
	@Test
	public void testCache() {
		
		User user = new User();
		user.setUserID(1);
		user.setUserName("john");
		redisCache.setUser(user);
		
		user = redisCache.getUser(1);
		System.out.println("Result : " + user.getUserName());
		user = redisCache.getUser(1);
		System.out.println("Result : " + user.getUserName());
		user = redisCache.getUser(1);
		System.out.println("Result : " + user.getUserName());
		
		user.setUserName("tommy");
		
		redisCache.updateUser(1, user);
		
		user = redisCache.getUser(1);
		System.out.println("Result : " + user.getUserName());
		user = redisCache.getUser(1);
		System.out.println("Result : " + user.getUserName());
		user = redisCache.getUser(1);
		System.out.println("Result : " + user.getUserName());
		
		redisCache.deleteUser(user);
	}
	
	@Test
	public void testRedis()
	{
		User user = new User();
		user.setUserID(1);
		user.setUserName("john");
		redisTemplate.setUser(user);
		
		User userfromRedis = redisTemplate.getUser(1);
		assertThat(user.getUserName(), org.hamcrest.core.Is.is(userfromRedis.getUserName()));
		
		user.setUserName("tommy");
		redisTemplate.updateUser(1, user);
		userfromRedis = redisTemplate.getUser(1);
		
		assertThat(user.getUserName(), org.hamcrest.core.Is.is(userfromRedis.getUserName()));
		
		redisTemplate.deleteUser(user);
		userfromRedis = redisTemplate.getUser(1);
		
		
		
	}

}
