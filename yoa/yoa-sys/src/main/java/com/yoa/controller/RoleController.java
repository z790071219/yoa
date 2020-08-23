package com.yoa.controller;
import com.yoa.base.model.ReturnDTO;
import com.yoa.dao.RoleDao;
import com.yoa.model.Permissions;
import com.yoa.model.Role;
import com.yoa.model.User;
import com.yoa.service.PermissionsService;
import com.yoa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionsService permissionsService;
    ReturnDTO result=new ReturnDTO();

    @GetMapping("/{id}")
    public ModelAndView query(@PathVariable("id")String id){
        ModelAndView mv=new ModelAndView();
        Role role=roleService.selectRolesById(Integer.valueOf(id));
        if (role==null) role=new Role();
        String permissions="";
        String permissionIds="";
        if (role.getPermissions()!=null){
            for (Permissions permission:role.getPermissions()){
                permissions+=permission.getDescription()+' ';
                permissionIds+=permission.getId().toString()+' ';
            }
        }
        mv.setViewName("role-form");
        mv.addObject(role);
        mv.addObject("permissions",permissions);
        mv.addObject("permissionIds",permissionIds);
        mv.addObject("permissionsList",permissionsService.selectList(null));
        return mv;
    }

    @PutMapping
    public ReturnDTO modify(@RequestBody Role role){
        if (roleService.updateById(role))
            return result.success(role);
        else
            return result.fail("该角色已存在");
    }
    @GetMapping("/page")
    public ModelAndView queryPage(){
        ModelAndView mv=new ModelAndView();
        List<Role> roleList=roleService.selectList(null);
        mv.addObject(roleList);
        mv.setViewName("role-list");
        return  mv;
    }
    @PostMapping
    public ReturnDTO add(@RequestBody Role role){
        if (roleService.insert(role))
            return result.success(role);
        else
            return result.fail("增加角色失败，该角色已存在");
    }

    @DeleteMapping("/{id}")
    public ReturnDTO delete(@PathVariable int id){
        if(roleService.deleteById(Integer.valueOf(id)))
        {
            return  result.success();
        }else{
            return  result.fail("删除失败!");
        }
    }

    @GetMapping
    public ReturnDTO queryAll(){
        return result.success(roleService.selectList(null));
    }

}
