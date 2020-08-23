package com.yoa.base.dao;

import java.util.List;

public interface TreeDao<T> extends BaseDao<T> {
    /**
     * 找到所有子节点
     * @param entity
     * @return
     */
    List<T> findByParentId(T entity);

}
