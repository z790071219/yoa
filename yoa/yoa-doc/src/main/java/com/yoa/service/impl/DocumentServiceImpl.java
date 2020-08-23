package com.yoa.service.impl;

import com.yoa.base.service.impl.BaseServiceImpl;
import com.yoa.dao.*;
import com.yoa.model.*;
import com.yoa.service.DocumentService;
import com.yoa.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Transactional(rollbackFor = Exception.class)
@Service
public class DocumentServiceImpl extends BaseServiceImpl<DocumentDao,Document> implements DocumentService {
    @Autowired
    DocumentDao documentDao;
    @Autowired
    DocTypeDao docTypeDao;
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    ApproveFlowDao approveFlowDao;
    @Override
    public List<Document> getDocList(Map<String, Object> map) {
        return documentDao.selectByMap(map);
    }

    @Override
    public Document getDocumentById(Integer id) {
        return documentDao.selectById(id);
    }

    @Override
    public List<Document> getDocActiveByUserId(Integer id) {
        return documentDao.selectActiveByUserId(id);
    }

    @Override
    public List<Document> getDocByUserId(Integer id) {
        return documentDao.selectByUserId(id);
    }

    @Override
    public boolean approveDoc(Document document) {
        if (!document.getStatus().equals("active")||!checkPermissions(document))
            return false;
        if (document.getLast())
            document.setStatus("approved");
        else {
            Map<String,Object> params=new HashMap<>();
            params.put("type_id",document.getTypeId());
            params.put("tier",document.getTier()+1);
            ApproveFlow approveFlow=approveFlowDao.selectByMap(params).get(0);
            document.setHasDeptLimit(approveFlow.getHasDeptLimit());
            document.setLast(approveFlow.getLast());
            document.setRoleId(approveFlow.getRoleId());
            document.setTier(approveFlow.getTier());
        }
        document.setApprover(Global.getUser().getUsername());
        document.setModifyPerson(Global.getUser().getUsername());
        document.setModifyTime(Global.formatDate(new Date()));
        super.updateById(document);
        return true;
    }

    @Override
    public boolean refuseDoc(Document document) {
        if (!document.getStatus().equals("active")||!checkPermissions(document))
            return false;
        document.setStatus("refuse");
        document.setApprover(Global.getUser().getUsername());
        return this.updateById(document);
    }

    @Override
    public boolean submitDoc(Document document) {
        if (document.getStatus().equals("active")||document.getStatus().equals("approved")){
            return false;
        }else {
            if (document.getId()==null||document.getId()<=0){
                if (!this.insert(document))
                    return false;
            }
            Map<String,Object> params=new HashMap<>();
            params.put("type_id",document.getTypeId());
            params.put("tier",1);
            ApproveFlow approveFlow=approveFlowDao.selectByMap(params).get(0);
            document.setHasDeptLimit(approveFlow.getHasDeptLimit());
            document.setLast(approveFlow.getLast());
            document.setRoleId(approveFlow.getRoleId());
            document.setTier(approveFlow.getTier());
            document.setStatus("active");
            document.setDeptId(userDao.selectUserById(Global.getUser().getId()).getDepartmentId().intValue());
            return this.updateById(document);
        }
    }

    @Override
    public boolean saveDoc(Document document) {
        if (document.getId()==null){
            document.setStatus("new");
            return this.insert(document);
        }
        else if (!document.getStatus().equals("new")&&!document.getStatus().equals("refuse"))
            return false;
        else
            return this.updateById(document);
    }

    @Override
    public boolean delDoc(Integer id) {
        Document document=documentDao.selectById(id);
        if (document.getStatus()!=null&&document.getStatus().equals("active"))
            return false;
        else
            return super.deleteById(id);
    }

    @Override
    public boolean insert(Document document){
        Map<String,Object> query=new HashMap<>();
        query.put("title",document.getTitle());
        if (documentDao.selectByMap(query).size()>0)
            return false;
        else {
            document.setCreatePerson(Global.getUser().getUsername());
            document.setCreateTime(Global.formatDate(new Date()));
            super.insert(document);
            document.setId(super.selectByMap(query).get(0).getId());
            return true;
        }
    }
    @Override
    public boolean updateById(Document document){
        Map<String,Object> query=new HashMap<>();
        query.put("title",document.getTitle());
        List<Document> list=documentDao.selectByMap(query);
        if (list.size()>0)
            if (!list.get(0).getId().equals(document.getId())||list.size()>1)
                return false;
        document.setModifyTime(Global.formatDate(new Date()));
        document.setModifyPerson(Global.getUser().getUsername());
        return super.updateById(document);
    }

    public boolean checkPermissions(Document document){
        boolean hasPermissions=false;
        User user=userDao.selectUserById(Global.getUser().getId());
        DocType docType=docTypeDao.selectById(document.getTypeId());
        if (document.getHasDeptLimit()&&user.getDepartmentId().intValue()!=document.getDeptId()){
            return false;
        }
        for (Role role:user.getRoles()){
            if (role.getId()==document.getRoleId()) {
                hasPermissions = true;
                break;
            }
        }
        if (!hasPermissions) return false;
        return true;
    }
}
