package com.yoa.dao;

import com.yoa.base.dao.BaseDao;
import com.yoa.model.ApproveFlow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApproveFlowDao extends BaseDao<ApproveFlow> {
    List<ApproveFlow> selectByTypeId(Integer id);
}
