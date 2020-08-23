package com.yoa.service;

import com.yoa.base.service.BaseService;
import com.yoa.model.Department;

import java.util.List;

public interface DepartmentService extends BaseService<Department> {
    List<Department> selectDepaTree();
}
