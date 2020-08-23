package com.yoa.service.impl;

import com.yoa.base.service.impl.BaseServiceImpl;
import com.yoa.dao.MenuDao;
import com.yoa.model.Menu;
import com.yoa.service.MenuService;
import com.yoa.util.Global;
import com.yoa.util.ListToTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDao,Menu> implements MenuService {
    @Autowired
    MenuDao menuDao;
    @Override
    public List<Menu> selectMenuTreeByUserId(int userId) {
        return ListToTreeUtil.listToTree(menuDao.selectMenuByUserId(userId));
    }

    @Override
    public List<Menu> selectMenuTreeByRoleId(int roleId) {
        return ListToTreeUtil.listToTree(menuDao.selectMenuByRoleId(roleId));
    }

    @Override
    public List<Menu> selectAllMenuTree() {
        return ListToTreeUtil.listToTree(menuDao.selectAllMenu());
    }

    public List<Menu> selectAllMenu() {
        return menuDao.selectAllMenu();
    }

    @Override
    public Menu selectById(Integer id) {
        return menuDao.selectById(id);
    }

    @Override
    public boolean updateById(Menu menu){
        Map map=new HashMap();
        map.put("name",menu.getName());
        List<Menu> list=menuDao.selectByMap(map);
        if (list.size()>0){
            if (!list.get(0).getId().equals(menu.getId())||list.size()>1)
                return false;
        }
        menu.setModifyPerson(Global.getUser().getUsername());
        menu.setModifyTime(Global.formatDate(new Date()));
        return super.updateById(menu);
    }

    @Override
    public boolean insert(Menu menu) {
        Map<String,Object> query=new HashMap<String,Object>();
        query.put("name",menu.getName());
        if (super.selectByMap(query).size()>0)
            return false;
        else {
            super.insert(menu);
            menu.setId(super.selectByMap(query).get(0).getId());
            menu.setNode(menu.getId());
            this.updateById(menu);
            return true;
        }
    }
}
