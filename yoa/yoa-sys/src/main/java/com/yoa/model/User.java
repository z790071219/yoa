package com.yoa.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.yoa.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "YOA_USER")
public class User extends BaseModel {
    @TableField(value = "user_name",strategy=FieldStrategy.NOT_EMPTY)
    String userName;
    @TableField(exist = false)
    Department department;
    @TableField(value = "department_id")
    Long departmentId;
    @TableField(exist = false)
    List<Role> roles;
    String type;
    @TableField(value = "hash_pwd",strategy=FieldStrategy.NOT_EMPTY)
    String hashPwd;
    @TableField(value = "full_name")
    String fullName;
    String sex;
    byte[] photo;
    String mail;
    String tel;
    String address;
    Boolean disable;
    @TableField(value = "reg_time")
    String regTime;

    public Department getDepartment(){
        return this.department;
    }
}
