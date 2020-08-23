package com.yoa.service;

import com.yoa.base.service.BaseService;
import com.yoa.model.Menu;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface MenuService extends BaseService<Menu> {
    List<Menu> selectMenuTreeByUserId(int userId);
    List<Menu> selectMenuTreeByRoleId(int roleId);
    List<Menu> selectAllMenuTree();
    List<Menu> selectAllMenu();
    Menu selectById(Integer id);
}
