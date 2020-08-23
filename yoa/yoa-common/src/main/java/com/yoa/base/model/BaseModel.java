package com.yoa.base.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BaseModel implements Serializable {

    protected static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @TableId(type = IdType.AUTO)
    Integer id;
    /**
     * 修改人
     */
    @TableField(value = "modify_person", fill = FieldFill.INSERT_UPDATE)
    String modifyPerson;

    /**
     * 修改日期
     */
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    String modifyTime;
}
