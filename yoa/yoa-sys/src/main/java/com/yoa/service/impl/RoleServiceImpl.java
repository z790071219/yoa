package com.yoa.service.impl;

import com.yoa.base.service.impl.BaseServiceImpl;
import com.yoa.dao.RoleDao;
import com.yoa.dao.UserDao;
import com.yoa.model.Permissions;
import com.yoa.model.Role;
import com.yoa.model.User;
import com.yoa.service.RoleService;
import com.yoa.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao,Role> implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Override
    public List<Role> selectRolesByUserId(int id) {
        return roleDao.selectRolesByUserId(id);
    }

    @Override
    public Role selectRolesById(int id) {
        return roleDao.selectRolesById(id);
    }


    @Override
    public void updateRolePermissions(Role role) {
        if (role==null||role.getId()==null)
            return;

        roleDao.delAllRolePermissions(role.getId());
        if (role.getPermissions()==null||role.getPermissions().size()==0)
            return;

        List<Permissions> permissions=role.getPermissions();
        for (int i=0;i<permissions.size();i++){
            if (permissions.get(i).getId()!=null)
                roleDao.addRolePermissions(role.getId(),permissions.get(i).getId());
        }
    }

    @Override
    public boolean updateById(Role role){
        if (role==null||role.getId()==null)
            return false;

        Map map=new HashMap();
        map.put("name",role.getName());
        List<Role> list=roleDao.selectByMap(map);
        if (list.size()>0){
            if (!list.get(0).getId().equals(role.getId())||list.size()>1)
                return false;
        }
        if (role.getPermissions()!=null&&role.getPermissions().size()>0)
            this.updateRolePermissions(role);
        role.setModifyPerson(Global.getUser().getUsername());
        role.setModifyTime(Global.formatDate(new Date()));
        return super.updateById(role);
    }

    @Override
    public boolean insert(Role role) {
        if (role==null||role.getName()==null||role.getName().equals(""))
            return false;
        Map<String,Object> query=new HashMap<>();
        query.put("name",role.getName());
        if (super.selectByMap(query).size()>0)
            return false;
        super.insert(role);
        role.setId(roleDao.selectByMap(query).get(0).getId());
        updateRolePermissions(role);
        return true;
    }
}
