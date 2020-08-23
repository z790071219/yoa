package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.model.DocType;
import com.yoa.model.Role;
import com.yoa.service.DocTypeService;
import com.yoa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/approve")
public class DocTypeController {
    @Autowired
    DocTypeService docTypeService;
    @Autowired
    RoleService roleService;
    ReturnDTO result=new ReturnDTO();
    @GetMapping("/{id}")
    public ModelAndView query(@PathVariable("id")String id){
        ModelAndView mv=new ModelAndView();
        DocType docType=docTypeService.selectById(Integer.valueOf(id));
        List<Role> roleList=roleService.selectList(null);
        if (docType==null) docType=new DocType();
        mv.setViewName("approve-form");
        mv.addObject(docType);
        mv.addObject(roleList);
        return mv;
    }
    @PostMapping
    public ReturnDTO add(@RequestBody DocType docType){
        if (docTypeService.insert(docType))
            return result.success(docType);
        else
            return result.fail("增加失败，类型已存在");
    }

    @PutMapping
    public ReturnDTO modify(@RequestBody DocType docType){
        if (docTypeService.updateById(docType))
            return result.success(docType);
        else
            return result.fail("类型已存在");
    }

    @DeleteMapping("/{id}")
    public ReturnDTO delete(@PathVariable("id")String id){
        try {
            docTypeService.deleteById(Integer.valueOf(id));
        }catch (Exception e){
            return  result.fail("删除失败，流程被使用中");
        }
        return  result.success();
    }
    @GetMapping("/page")
    public ModelAndView queryAll(){
        ModelAndView mv=new ModelAndView();
        List<DocType> docTypeList=docTypeService.getDocTypeList();
        mv.addObject(docTypeList);
        mv.setViewName("approve-list");
        return  mv;
    }

}
