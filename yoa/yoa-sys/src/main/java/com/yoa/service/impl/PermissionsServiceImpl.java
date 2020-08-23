package com.yoa.service.impl;

import com.yoa.base.service.impl.BaseServiceImpl;
import com.yoa.dao.PermissionsDao;
import com.yoa.model.Permissions;
import com.yoa.service.PermissionsService;
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
public class PermissionsServiceImpl extends BaseServiceImpl<PermissionsDao,Permissions> implements PermissionsService {
    @Autowired
    PermissionsDao permissionsDao;

    @Override
    public boolean insert(Permissions permissions) {
        Map<String,Object> query=new HashMap<>();
        query.put("name",permissions.getName());
        if (permissionsDao.selectByMap(query).size()>0)
            return false;
        else {
            super.insert(permissions);
            permissions.setId(permissionsDao.selectByMap(query).get(0).getId());
            return true;
        }
    }
    @Override
    public boolean updateById(Permissions permissions){
        Map map=new HashMap();
        map.put("name",permissions.getName());
        List<Permissions> list=permissionsDao.selectByMap(map);
        if (list.size()>0){
            if (!list.get(0).getId().equals(permissions.getId())||list.size()>1)
                return false;
        }
        permissions.setModifyPerson(Global.getUser().getUsername());
        permissions.setModifyTime(Global.formatDate(new Date()));
        return super.updateById(permissions);
    }
}
