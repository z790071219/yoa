package com.yoa.service.impl;

import com.yoa.base.service.impl.BaseServiceImpl;
import com.yoa.dao.UserDao;
import com.yoa.model.Role;
import com.yoa.model.User;
import com.yoa.service.RoleService;
import com.yoa.service.UserService;
import com.yoa.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao,User> implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public User selectUserById(int id) {
        return userDao.selectUserById(id);
    }

    @Override
    public User selectUserByUserName(String userName) {
        return userDao.selectUserByUserName(userName);
    }

    @Override
    public void updateUserRole(User user) {
        if (user==null||user.getId()==null)
            return;
        userDao.delAllUserRole(user.getId());
        if (user.getRoles()==null || user.getRoles().size()==0)
            return;
        List<Role> roles=user.getRoles();
        for (int i=0;i<roles.size();i++){
            if (roles.get(i).getId()!=null)
                userDao.addUserRole(user.getId(),roles.get(i).getId());
        }
    }

    @Override
    public boolean updateById(User user){
        if (user==null||user.getId()==null)
            return false;
        //不允许修改
        user.setHashPwd(null);
        user.setUserName(null);
        if (user.getRoles()!=null&&user.getRoles().size()>0)
            this.updateUserRole(user);
        user.setModifyPerson(Global.getUser().getUsername());
        user.setModifyTime(Global.formatDate(new Date()));
        return super.updateById(user);
    }

    @Override
    public boolean insert(User user){
        if (user==null||user.getUserName()==null||user.getUserName().equals(""))
            return false;
        user.setHashPwd(Global.encipherment(user.getHashPwd(),user.getUserName()));

        Map<String,Object> query=new HashMap<>();
        query.put("user_name",user.getUserName());
        if (super.selectByMap(query).size()>0)
            return false;
        user.setRegTime(Global.formatDate(new Date()));
        super.insert(user);
        user.setId(userDao.selectUserByUserName(user.getUserName()).getId());
        this.updateUserRole(user);
        return true;
    }
}
