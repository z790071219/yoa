package com.yoa.dao;

import com.yoa.base.dao.BaseDao;
import com.yoa.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleDao extends BaseDao<Role> {
    List<Role> selectRolesByUserId(int id);
    Role selectRolesById(int id);
    int addRolePermissions(@Param(value = "roleId") int roleId,@Param(value = "permissionsId") int permissionsId);
    int delRolePermissions(@Param(value = "roleId") int roleId,@Param(value = "permissionsId") int permissionsId);
    int delAllRolePermissions(int id);
}
