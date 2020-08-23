package com.yoa;

import com.baomidou.mybatisplus.plugins.Page;
import com.yoa.dao.*;
import com.yoa.model.ApproveFlow;
import com.yoa.model.DocType;
import com.yoa.model.Menu;
import com.yoa.model.User;
import com.yoa.service.UserService;
import com.yoa.util.ListToTreeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YoaWebApplicationTests {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PermissionsDao permissionsDao;
    @Autowired
    MenuDao menuDao;
    @Autowired
    DepartmentDao departMentDao;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void contextLoads() throws IOException {
        //User user=userDao.selectList(null).get(0);
        /*File p=new File("D:\\1.jpg");
        User user=new User();
        FileInputStream inputStream=new FileInputStream(p);
        byte[] b=new byte[(int)p.length()];
        inputStream.read(b);
        user.setPhoto(b);
        userDao.insert(user);
        System.out.println(userDao.selectList(null));*/
        /*System.out.println(userDao.delAllUserRole(9));
        System.out.println(userDao.addUserRole(9,1));
        System.out.println(userDao.addUserRole(9,2));
        System.out.println(userDao.selectUserById(9));
        System.out.println(userDao.selectUserByUserName("张三"));
        System.out.println(menuDao.selectMenuByUserId(9));*/
//        Menu m=new Menu();
//        m.setParentNode(9);
//        System.out.println(menuDao.findByParentId(m));
        //System.out.println(userService.selectPage(new Page<User>(1,10)));
        //System.out.println(userDao.selectUserByUserName("张三"));
        List<Menu> menus=new ArrayList<>();
        menus.add(new Menu());
        menus.add(new Menu());
        menus.add(new Menu());
        menus.add(new Menu());
        menus.add(new Menu());
        menus.get(0).setNode(0);
        menus.get(0).setPNode(-1);
        menus.get(1).setNode(0);
        menus.get(1).setPNode(-1);
        menus.get(2).setNode(1);
        menus.get(2).setPNode(0);
        menus.get(3).setNode(1);
        menus.get(3).setPNode(0);
        menus.get(4).setNode(2);
        menus.get(4).setPNode(1);
        System.out.println(ListToTreeUtil.listToTree(menus));
        redisTemplate.opsForList();
        System.out.println(redisTemplate.opsForValue().get("aaa"));
    }

    public void testUtil(){
        List<Menu> menus=new ArrayList<>();
        menus.add(new Menu());
        menus.add(new Menu());
        menus.add(new Menu());
        menus.add(new Menu());
        menus.add(new Menu());
        menus.get(0).setNode(0);
        menus.get(0).setPNode(-1);
        menus.get(1).setNode(0);
        menus.get(1).setPNode(-1);
        menus.get(2).setNode(1);
        menus.get(2).setPNode(0);
        menus.get(3).setNode(1);
        menus.get(3).setPNode(0);
        menus.get(4).setNode(2);
        menus.get(4).setPNode(1);
        System.out.println(ListToTreeUtil.listToTree(menus));
    }
    @Autowired
    DocumentDao documentDao;
    @Autowired
    DocTypeDao docTypeDao;
    @Autowired
    ApproveFlowDao approveFlowDao ;
    @Test
    public void documentTest(){
        //Map<String,Object> map=new HashMap<>();
       // map.put("typeId",1);
        //System.out.println(documentDao.selectByMap(map));
       /// System.out.println(docTypeDao.selectById(1));
       // System.out.println(approveFlowDao.selectById(1));
        Map<String,Object> params=new HashMap<>();
        params.put("type_id",1);
        params.put("tier","3");
        ApproveFlow approveFlow=approveFlowDao.selectByMap(params).get(0);
        System.out.println(approveFlow);
    }
}
