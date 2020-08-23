package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class myController {
    @Autowired
    RedisTemplate redisTemplate;
    ReturnDTO result=new ReturnDTO();
    @RequestMapping("/getMessage")
    public ReturnDTO getMessage() {
        return result.success(redisTemplate.opsForList().range("user"+Global.getUser().getId(),0,-1));
    }
    @RequestMapping("/index111")
    public ModelAndView in(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}