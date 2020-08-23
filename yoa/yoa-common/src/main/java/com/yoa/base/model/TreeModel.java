package com.yoa.base.model;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.List;

@Data
public class TreeModel<T extends TreeModel> extends BaseModel{
    /**
     * 当前节点
     */
    Integer node;
    /**
     * 父级节点
     */
    @TableField(value = "parent_node")
    Integer pNode;
    /**
     * 子结点
     */
    @TableField(exist = false)
    List<T> childList;
}
