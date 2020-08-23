package com.yoa.dao;

import com.yoa.base.dao.BaseDao;
import com.yoa.model.Document;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DocumentDao extends BaseDao<Document> {
    Document selectById(int id);
    List<Document> selectByMap(Map<String, Object> params);
    List<Document> selectActiveByUserId(int id);
    List<Document> selectByUserId(int id);
}
