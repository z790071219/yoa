package com.yoa.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.yoa.base.model.TreeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "YOA_DEPARTMENT")
public class Department extends TreeModel<Department> {
    String name;
    String description;
}
