package com.yoa.service;

import com.yoa.base.service.BaseService;
import com.yoa.model.User;

import java.util.List;

public interface UserService extends BaseService<User> {
    List<User> selectAllUser();
    User selectUserById(int id);
    User selectUserByUserName(String userName);
    void updateUserRole(User user);
}
