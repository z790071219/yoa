package com.yoa.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yoa.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "YOA_DOCUMENT")
public class Document extends BaseModel {
    String title;
    String content;
    @TableField(value = "type_id")
    Integer typeId;
    @TableField(exist = false)
    DocType type;
    String status;
    @TableField(value = "dept_id")
    Integer deptId;
    @TableField(exist = false)
    Department dept;
    @TableField(value = "role_id")
    Integer roleId;
    @TableField(exist = false)
    Role role;
    Integer tier;
    @TableField(value = "is_last")
    Boolean last;
    @TableField(value = "has_dept_limit")
    Boolean hasDeptLimit;
    String approver;
    String comment;
    @TableField(value = "create_person")
    String createPerson;
    @TableField(value = "create_time")
    String createTime;
}
