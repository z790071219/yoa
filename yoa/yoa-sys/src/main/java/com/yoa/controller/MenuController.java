package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.model.Menu;
import com.yoa.model.Permissions;
import com.yoa.service.MenuService;
import com.yoa.service.PermissionsService;
import com.yoa.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @Autowired
    PermissionsService permissionsService;
    ReturnDTO result=new ReturnDTO();

    @GetMapping("/{id}")
    public ModelAndView query(@PathVariable("id")String id){
        ModelAndView mv=new ModelAndView();
        Menu menu=menuService.selectById(Integer.valueOf(id));
        if (menu==null) menu=new Menu();
        mv.setViewName("menu-form");
        mv.addObject(menu);
        Map<String,Object> map=new HashMap<>();
        map.put("type",1);
        mv.addObject("pNodes",menuService.selectByMap(map));

        List<Permissions> permissionsList=permissionsService.selectList(null);
        mv.addObject(permissionsList);
        return  mv;
    }

    @PutMapping
    public ReturnDTO modify(@RequestBody Menu menu){
        if (menuService.updateById(menu))
            return result.success(menu);
        else
            return result.fail("该菜单项已存在");
    }

    @PostMapping
    public ReturnDTO add(@RequestBody Menu menu){
        if (menuService.insert(menu))
            return result.success(menu);
        else
            return result.fail("增加菜单项失败，菜单编码已存在");
    }

    @DeleteMapping("/{id}")
    public ReturnDTO delete(@PathVariable("id")String id){
        try {
            menuService.deleteById(Integer.valueOf(id));
        }catch (Exception e){
            return  result.fail("删除失败，请联系管理员");
        }
        return  result.success();
    }

    @GetMapping("/page")
    public ModelAndView queryAll(){
        ModelAndView mv=new ModelAndView();
        List<Menu> menuList=menuService.selectAllMenu();
        mv.addObject(menuList);
        mv.setViewName("menu-list");
        return  mv;
    }

    @GetMapping("/getMenuByUser")
    public ReturnDTO queryMenuByUser(){
        List<Menu> menuList=menuService.selectMenuTreeByUserId(Global.getUser().getId());
        return  result.success(menuList);
    }

}
