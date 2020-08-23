package com.yoa.service;

import com.yoa.base.service.BaseService;
import com.yoa.model.DocType;

import java.util.List;

public interface DocTypeService extends BaseService<DocType> {
    List<DocType> getDocTypeList();
    DocType selectById(Integer id);
    boolean deleteById(Integer id);
    boolean updateById(DocType docType);
    boolean insert(DocType docType);
}
