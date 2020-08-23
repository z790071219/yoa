package com.yoa.util;

import com.yoa.shiro.ShiroUser;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Describe: 全局常量
 */
public class Global {
    @Autowired
    RedisTemplate redisTemplate;
    public static final String HASH_ALGORITHM="MD5";
    public static final int HASH_INTERATIONS=2;
    public static final String FORMAT_DATE="yyyy-MM-dd";
    public static Boolean isLogin(){
        System.out.println("123123123"+SecurityUtils.getSubject().isAuthenticated());
        return SecurityUtils.getSubject().isAuthenticated();
    }

    public static ShiroUser getUser() {
        Object key = SecurityUtils.getSubject().getPrincipal();
        ShiroUser shiroUser=new ShiroUser();
        try {
            BeanUtils.copyProperties(shiroUser, key);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return shiroUser;
    }

    public static String encipherment(String text,String salt){
        SimpleHash sh=new SimpleHash(Global.HASH_ALGORITHM,text,ByteSource.Util.bytes(salt),Global.HASH_INTERATIONS);
        return sh.toString();
    }

    public static String formatDate(Date date){
        return new SimpleDateFormat(FORMAT_DATE).format(date);
    }


}
