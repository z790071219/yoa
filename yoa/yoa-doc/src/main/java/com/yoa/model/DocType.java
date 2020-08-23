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
@TableName(value = "YOA_DOCUMENT_TYPE")
public class DocType extends BaseModel {
    @TableField(value = "type_name")
    String typeName;
    String comment;
    @TableField(exist = false)
    List<ApproveFlow> approveFlows;
}
