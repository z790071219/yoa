package com.yoa.service.impl;

import com.yoa.base.service.impl.BaseServiceImpl;
import com.yoa.dao.ApproveFlowDao;
import com.yoa.dao.DocTypeDao;
import com.yoa.model.ApproveFlow;
import com.yoa.model.DocType;
import com.yoa.service.DocTypeService;
import com.yoa.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Transactional(rollbackFor = Exception.class)
@Service
public class DocTypeServiceImpl extends BaseServiceImpl<DocTypeDao,DocType> implements DocTypeService {
    @Autowired
    DocTypeDao docTypeDao;
    @Autowired
    ApproveFlowDao approveFlowDao;
    @Override
    public List<DocType> getDocTypeList() {
        return docTypeDao.selectAll();
    }
    @Override
    public DocType selectById(Integer id){
        return docTypeDao.selectById(id);
    }

    @Override
    public boolean updateById(DocType docType) {
        if (docType==null ||docType.getId()==null)
            return false;
        Map<String,Object> param=new HashMap<>();
        param.put("type_name",docType.getTypeName());
        List<DocType> list=docTypeDao.selectByMap(param);
        if (list.size()>0){
            if (!list.get(0).getId().equals(docType.getId())||list.size()>1)
                return false;
        }
        docType.setModifyPerson(Global.getUser().getUsername());
        docType.setModifyTime(Global.formatDate(new Date()));
        super.updateById(docType);

        param.clear();
        param.put("type_id",docType.getId());
        approveFlowDao.deleteByMap(param);
        for (ApproveFlow af:docType.getApproveFlows()){
            af.setTypeId(docType.getId());
            approveFlowDao.insert(af);
        }
        return true;
    }

    @Override
    public boolean insert(DocType docType){
        Map<String,Object> query=new HashMap<>();
        query.put("type_name",docType.getTypeName());
        if (docTypeDao.selectByMap(query).size()>0){
            return false;
        } else{
            docTypeDao.insert(docType);
            docType.setId(docTypeDao.selectByMap(query).get(0).getId());
            this.updateById(docType);
            return true;
        }
    }
    @Override
    public boolean deleteById(Integer id){
        Map<String,Object> param=new HashMap<>();
        param.put("type_id",id);
        approveFlowDao.deleteByMap(param);
        return super.deleteById(id);
    }
}
