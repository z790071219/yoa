package com.yoa.base.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yoa.base.dao.BaseDao;
import com.yoa.base.model.BaseModel;
import com.yoa.base.service.BaseService;
import com.yoa.shiro.ShiroUser;
import com.yoa.util.Global;
import org.apache.shiro.SecurityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseServiceImpl<M extends BaseDao<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {
}
