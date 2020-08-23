package com.yoa.dao;

import com.yoa.base.dao.TreeDao;
import com.yoa.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuDao extends TreeDao<Menu> {
    List<Menu> selectAllMenu();
    List<Menu> selectMenuByUserId(long userId);
    List<Menu> selectMenuByRoleId(long roleId);
    Menu selectById(int id);
}
