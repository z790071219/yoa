package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.model.Permissions;
import com.yoa.model.Role;
import com.yoa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionsController {
    @Autowired
    PermissionsService permissionService;
    ReturnDTO result=new ReturnDTO();

    @GetMapping("/{id}")
    public ModelAndView query(@PathVariable("id")String id){
        ModelAndView mv=new ModelAndView();
        Permissions permissions=permissionService.selectById(Integer.valueOf(id));
        if (permissions==null) permissions=new Permissions();
        mv.setViewName("permissions-form");
        mv.addObject(permissions);
        return mv;
    }

    @PutMapping
    public ReturnDTO modify(@RequestBody Permissions permissions){
        if (permissionService.updateById(permissions))
            return result.success(permissions);
        else
            return result.fail("该权限已存在");
    }

    @PostMapping
    public ReturnDTO add(@RequestBody Permissions permissions){
        if (permissionService.insert(permissions))
            return result.success(permissions);
        else
            return result.fail("增加权限失败，该权限已存在");
    }

    @GetMapping("/page")
    public ModelAndView queryPage(){
        ModelAndView mv=new ModelAndView();
        List<Permissions> permissionsList=permissionService.selectList(null);
        mv.addObject(permissionsList);
        mv.setViewName("permissions-list");
        return  mv;
    }

    @DeleteMapping("/{id}")
    public ReturnDTO delete(@PathVariable("id")String id){
        try {
            permissionService.deleteById(Integer.valueOf(id));
        }catch (DataIntegrityViolationException e){
            return  result.fail("该权限使用中，无法删除");
        }
        return  result.success();
    }

}
