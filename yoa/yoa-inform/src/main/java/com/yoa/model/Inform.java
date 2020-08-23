package com.yoa.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yoa.base.model.BaseModel;
import com.yoa.base.model.TreeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "YOA_INFORM")
public class Inform extends BaseModel {
    String name;
    String title;
    String content;
    int level;
    @TableField(value = "create_person")
    String createPerson;
    @TableField(value = "create_time")
    String createTime;
}
