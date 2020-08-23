package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.model.Department;
import com.yoa.model.Permissions;
import com.yoa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    DepartmentService deptService;
    ReturnDTO result=new ReturnDTO();

    @GetMapping("/{id}")
    public ModelAndView query(@PathVariable("id")String id){
        ModelAndView mv=new ModelAndView();
        Department department=deptService.selectById(Integer.valueOf(id));
        if (department==null) department=new Department();
        mv.setViewName("dept-form");
        mv.addObject(department);
        return mv;
    }

    @PutMapping
    public ReturnDTO modify(@RequestBody Department department){
        if (deptService.updateById(department))
            return result.success(department);
        else
            return result.fail("该部门已存在");
    }

    @PostMapping
    public ReturnDTO add(@RequestBody Department department){
        if (deptService.insert(department))
            return result.success(department);
        else
            return result.fail("增加部门失败，该部门已存在");
    }

    @DeleteMapping("/{id}")
    public ReturnDTO delete(@PathVariable("id")String id){
        try {
            deptService.deleteById(Integer.valueOf(id));
        }catch (DataIntegrityViolationException e){
            return  result.fail("该部门使用中，无法删除");
        }
        return  result.success();
    }

    @GetMapping
    public ReturnDTO queryAll(){
        List<Department> deptList=deptService.selectDepaTree();
        return  result.success(deptList);
    }

    @GetMapping("/page")
    public ModelAndView queryPage(){
        ModelAndView mv=new ModelAndView();
        List<Department> departmentList=deptService.selectList(null);
        mv.addObject(departmentList);
        mv.setViewName("dept-list");
        return  mv;
    }


}
