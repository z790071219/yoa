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
@TableName(value = "YOA_APPROVE_FLOW")
public class ApproveFlow extends BaseModel {
    @TableField(value = "type_id")
    Integer typeId;
    @TableField(value = "has_dept_limit")
    Boolean hasDeptLimit;
    @TableField(value = "role_id")
    Integer roleId;
    @TableField(exist = false)
    Role role;
    Integer tier;
    @TableField(value = "is_last")
    Boolean last;
}
