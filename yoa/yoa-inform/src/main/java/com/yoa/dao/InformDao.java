package com.yoa.dao;

import com.yoa.base.dao.BaseDao;
import com.yoa.model.Inform;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformDao extends BaseDao<Inform> {
    List<Inform> selectAllInform();
    int getMaxId();
}
