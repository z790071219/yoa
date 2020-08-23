package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.model.Inform;
import com.yoa.service.InformService;
import com.yoa.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/inform")
public class InformController {
    @Autowired
    InformService informService;
    @Autowired
    RedisTemplate redisTemplate;
    ReturnDTO result=new ReturnDTO();

    @GetMapping("/{id}")
    public ModelAndView query(@PathVariable("id")String id){
        ModelAndView mv=new ModelAndView();
        Inform inform=informService.selectById(Integer.valueOf(id));
        if (inform==null) inform=new Inform();
        mv.setViewName("inform-form");
        mv.addObject(inform);
        return mv;
    }

    @PutMapping
    public ReturnDTO modify(@RequestBody Inform inform){
        if (informService.updateById(inform))
            return result.success(inform);
        else
            return result.fail("公告编号重复");
    }

    @PostMapping
    public ReturnDTO add(@RequestBody Inform inform){
        if (informService.insert(inform)){
            informService.sendMessageToUser(inform);
            return result.success(inform);
        }
        else
            return result.fail("公告编号重复");
    }

    @DeleteMapping("/{id}")
    public ReturnDTO delete(@PathVariable("id")String id){
        informService.deleteById(Integer.valueOf(id));
        return  result.success();
    }

    @GetMapping
    public ReturnDTO queryAll(){
        List<Inform> informList=informService.selectList(null);
        return  result.success(informList);
    }

    @GetMapping("/page")
    public ModelAndView queryPage(){
        ModelAndView mv=new ModelAndView();
        List<Inform> informList=informService.getAllInform();
        mv.addObject(informList);
        mv.setViewName("inform-list");
        return  mv;
    }
}
