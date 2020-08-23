package com.yoa.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.yoa.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "YOA_PERMISSIONS")
public class Permissions extends BaseModel {
    String name;
    String description;
}
