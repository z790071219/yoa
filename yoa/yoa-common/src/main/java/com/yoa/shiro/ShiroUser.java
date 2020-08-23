package com.yoa.shiro;



import java.io.Serializable;
import java.util.Objects;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 *
 */
public class ShiroUser implements Serializable{
    public Integer id;
    public String username;
    public String name;
    public ShiroUser(){}
    public ShiroUser(Integer id, String username, String name){
        this.id=id;
        this.username=username;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}