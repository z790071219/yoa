package com.yoa.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yoa.base.model.BaseModel;
import com.yoa.base.model.TreeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "YOA_MENU")
public class Menu extends TreeModel<Menu>{
    String name;
    String description;
    String url;
    Integer type;
    @TableField(exist = false)
    Permissions permissions;
    @TableField(value = "permissions_id")
    Integer permissionsId;
    String icon;

    @Override
    public String toString() {
        return "Menu{" +
                "node='" + getNode() + '\'' +
                ", PNode='" + getPNode() + '\'' +
                ", child='" + getChildList() + '\'' +
                '}';
    }
}
