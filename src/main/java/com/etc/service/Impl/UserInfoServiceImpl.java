package com.etc.service.Impl;

import com.etc.dao.UserDao;
import com.etc.entity.User;
import com.etc.service.IUserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Resource
    UserDao userDao;
    @Override
    public void addUserInfo(User user) {
        user.setUid(0);
        userDao.insertUser(user);
    }

    @Override
    public void delUserInfoByID(int id) {
        userDao.delUserByID(id);
    }

    @Override
    public void modUserInfoByID(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> listAllUserInfo(User user) {
        return userDao.findAllUser(user);
    }

    @Override
    public User listUserInfoByID(int id) {
        return userDao.seletUserByID(id);
    }

    @Override
    public PageInfo findPage(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<User> allUser = userDao.findAllUser(null);
        PageInfo pageInfo = new PageInfo(allUser);
        return pageInfo;
    }

    @Override
    public User loginQuery(String name,String password) {
        System.out.println(userDao.loginQuery(name,password));
        return userDao.loginQuery(name,password);
    }
}
