package com.yoa.dao;

import com.yoa.base.dao.TreeDao;
import com.yoa.model.Department;
import com.yoa.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentDao extends TreeDao<Department> {

}
