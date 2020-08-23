package com.yoa.dao;

import com.yoa.base.dao.BaseDao;
import com.yoa.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDao<User> {
    User selectUserById(int id);
    User selectUserByUserName(String userName);
    int addUserRole(@Param(value = "userId") int userId,@Param(value = "roleId") int roleId);
    int delUserRole(@Param(value = "userId") int userId,@Param(value = "roleId") int roleId);
    int delAllUserRole(int id);
    List<User> selectAllUser();
}
