package com.yoa.service;

import com.yoa.base.service.BaseService;
import com.yoa.model.Document;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public interface DocumentService extends BaseService<Document> {
    List<Document> getDocList(Map<String,Object> map);
    Document getDocumentById(Integer id);
    List<Document> getDocActiveByUserId(Integer id);
    List<Document> getDocByUserId(Integer id);
    boolean approveDoc(Document document);
    boolean refuseDoc(Document document);
    boolean submitDoc(Document document);
    boolean saveDoc(Document document);
    boolean delDoc(Integer id);
}
