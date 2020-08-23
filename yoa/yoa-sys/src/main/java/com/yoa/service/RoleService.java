package com.yoa.service;

import com.yoa.base.service.BaseService;
import com.yoa.model.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {
    List<Role> selectRolesByUserId(int id);
    Role selectRolesById(int id);
    void updateRolePermissions(Role role);
}
