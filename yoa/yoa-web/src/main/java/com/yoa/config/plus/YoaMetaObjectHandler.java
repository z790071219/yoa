package com.yoa.config.plus;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.yoa.util.Global;
import java.text.DateFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 字段自动填充
 * */
@Component
public class YoaMetaObjectHandler extends MetaObjectHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void insertFill(MetaObject metaObject) {
        modifyFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //bug
        //modifyFill(metaObject);
    }

    public void modifyFill(MetaObject metaObject){
        Object modifyTime = metaObject.getValue("modifyTime");
        Object modifyPerson = metaObject.getValue("modifyPerson");

        if (null == modifyTime) {
            metaObject.setValue("modifyTime", Global.formatDate(new Date()));
        }
        if (null == modifyPerson) {
            metaObject.setValue("modifyPerson",Global.getUser().getUsername());
        }
    }
}
