package com.yoa.service.impl;

import com.yoa.base.service.impl.BaseServiceImpl;
import com.yoa.dao.InformDao;
import com.yoa.dao.UserDao;
import com.yoa.model.Inform;
import com.yoa.model.User;
import com.yoa.service.InformService;
import com.yoa.util.Global;
import com.yoa.util.ListToTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class InformServiceImpl extends BaseServiceImpl<InformDao,Inform> implements InformService {
    @Autowired
    InformDao informDao;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserDao userDao;
    @Override
    public boolean updateById(Inform inform){
        inform.setModifyPerson(Global.getUser().getUsername());
        inform.setModifyTime(Global.formatDate(new Date()));
        return super.updateById(inform);
    }
    @Override
    public boolean insert(Inform inform) {
            inform.setCreatePerson(Global.getUser().getUsername());
            inform.setCreateTime(Global.formatDate(new Date()));
            informDao.insert(inform);
            inform.setId(informDao.getMaxId());
            return true;
    }

    @Override
    public List<Inform> getAllInform() {
        clearUserMessage();
        return informDao.selectAllInform();
    }

    @Override
    public void sendMessageToUser(Inform inform) {
        List<User> userList=userDao.selectAllUser();
        for (User user:userList)
        {
            redisTemplate.opsForList().leftPush("user"+user.getId(),inform);
        }
    }

    @Override
    public void clearUserMessage() {
            redisTemplate.opsForList().trim("user"+Global.getUser().getId(),1,0);
    }
}
