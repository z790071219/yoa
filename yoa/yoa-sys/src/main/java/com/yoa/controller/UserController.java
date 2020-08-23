package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.model.Menu;
import com.yoa.model.Role;
import com.yoa.model.User;
import com.yoa.service.DepartmentService;
import com.yoa.service.RoleService;
import com.yoa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    DepartmentService departmentService;
    ReturnDTO result=new ReturnDTO();

    @GetMapping("/{id}")
    public ModelAndView query(@PathVariable("id")String id){
        ModelAndView mv=new ModelAndView();
        User user=userService.selectUserById(Integer.valueOf(id));
        if (user==null) user=new User();
        String roles="";
        String roleIds="";
        if (user.getRoles()!=null){
            for (Role role:user.getRoles()){
                roles+=role.getDescription()+' ';
                roleIds+=role.getId().toString()+' ';
            }
        }
        mv.setViewName("user-form");
        mv.addObject(user);
        mv.addObject("roles",roles);
        mv.addObject("roleIds",roleIds);
        mv.addObject("roleList",roleService.selectList(null));
        mv.addObject("deptList",departmentService.selectList(null));
        return  mv;
    }



    @PutMapping
    public ReturnDTO modify(@RequestBody User user){
        if (userService.updateById(user))
            return result.success(user);
        else
            return result.fail("error");
    }

    @PostMapping
    public ReturnDTO add(@RequestBody User user){
        if (userService.insert(user))
            return result.success(user);
        else
            return result.fail("增加用户失败!");
    }
//todo:admin
    @DeleteMapping("/{id}")
    public ReturnDTO delete(@PathVariable("id")String id){
        if(userService.deleteById(Integer.valueOf(id)))
        {
            return  result.success();
        }else{
            return  result.fail("删除失败!");
        }
    }
//todo:page
    @GetMapping
    public ReturnDTO queryAll(){
        List<User> userList=userService.selectList(null);
        return  result.success(userList);
    }

    @GetMapping("/page")
    public ModelAndView queryPage(){
        ModelAndView mv=new ModelAndView();
        List<User> userList=userService.selectAllUser();
        mv.addObject(userList);
        mv.setViewName("user-list");
        return  mv;
    }
    @PostMapping("/modifyRole")
    public ReturnDTO modifyRole(@RequestBody User user){
        try{
            userService.updateUserRole(user);
        }catch (DataIntegrityViolationException e){
            return result.fail("Error,role is not found!");
        }
        return result.success();
    }
}
