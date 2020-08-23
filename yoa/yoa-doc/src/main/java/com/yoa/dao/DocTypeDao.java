package com.yoa.dao;

import com.yoa.base.dao.BaseDao;
import com.yoa.model.DocType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocTypeDao extends BaseDao<DocType> {
    DocType selectById(int id);
    List<DocType> selectAll();
}
