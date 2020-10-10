package com.etc.service;

import com.etc.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserInfoService {
    void addUserInfo(User user);
    void delUserInfoByID(int id);
    void modUserInfoByID(User user);
    List<User> listAllUserInfo(User user);
    User listUserInfoByID(int id);
    PageInfo findPage(int page,int pageSize);
    User loginQuery(String name, String password);
}
