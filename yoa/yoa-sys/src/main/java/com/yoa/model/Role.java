package com.yoa.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yoa.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "YOA_ROLE")
public class Role extends BaseModel {
    String name;
    String description;
    @TableField(exist = false)
    List<Permissions> permissions;
}
