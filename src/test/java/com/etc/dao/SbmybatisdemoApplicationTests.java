package com.etc.dao;

import com.etc.entity.User;
import com.etc.service.IUserInfoService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SbmybatisdemoApplicationTests {

	@Resource
	UserDao userDao;

	@Resource
	IUserInfoService userInfoService;
	@Test
	void findUserInfoTest() {
//		List<User> users = userDao.findAllUser();
		List<User> users = userInfoService.listAllUserInfo(null);
		for (User u:users) {
			System.out.println(u);
		}
	}
	@Test
	void userLoginTest() {
		User asuka = userDao.loginQuery("asuka", "123456");
		System.out.println(asuka);
//		userDao.delUserByID(22);
	}

}