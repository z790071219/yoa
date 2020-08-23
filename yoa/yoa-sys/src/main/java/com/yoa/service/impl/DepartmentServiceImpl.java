package com.yoa.service.impl;

import com.yoa.base.service.impl.BaseServiceImpl;
import com.yoa.dao.DepartmentDao;
import com.yoa.model.Department;
import com.yoa.service.DepartmentService;
import com.yoa.util.Global;
import com.yoa.util.ListToTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentDao,Department> implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    @Override
    public List<Department> selectDepaTree() {
        return ListToTreeUtil.listToTree(departmentDao.selectList(null));
    }

    @Override
    public boolean insert(Department department) {
        Map<String,Object> query=new HashMap<>();
        query.put("name",department.getName());
        if (departmentDao.selectByMap(query).size()>0)
            return false;
        else {
            departmentDao.insert(department);
            department.setId(departmentDao.selectByMap(query).get(0).getId());
            return true;
        }
    }

    @Override
    public boolean updateById(Department department){
        Map map=new HashMap();
        map.put("name",department.getName());
        List<Department> list=departmentDao.selectByMap(map);
        if (list.size()>0){
            if (!list.get(0).getId().equals(department.getId())||list.size()>1)
            return false;
        }
        department.setModifyPerson(Global.getUser().getUsername());
        department.setModifyTime(Global.formatDate(new Date()));
        return super.updateById(department);
    }
}
