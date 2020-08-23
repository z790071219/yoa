package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.model.DocType;
import com.yoa.model.Document;
import com.yoa.model.Menu;
import com.yoa.service.DocTypeService;
import com.yoa.service.DocumentService;
import com.yoa.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    DocumentService documentService;
    @Autowired
    DocTypeService docTypeService;
    ReturnDTO result=new ReturnDTO();
    @GetMapping("/{id}")
    public ModelAndView query(@PathVariable("id")String id){
        ModelAndView mv=new ModelAndView();
        Document document=documentService.getDocumentById(Integer.valueOf(id));
        List<DocType> docTypeList=docTypeService.getDocTypeList();
        if (document==null) document=new Document();
        mv.setViewName("document-form");
        mv.addObject(document);
        mv.addObject(docTypeList);
        return mv;
    }
    @PutMapping("/approve")
    public ReturnDTO approve(@RequestBody Document document){
        if (documentService.approveDoc(document))
            return result.success(document);
        else
            return result.fail("批核失败，无权限或已批核");
    }
    @PutMapping("/refuse")
    public ReturnDTO refuse(@RequestBody Document document){
        if (documentService.refuseDoc(document))
            return result.success(document);
        else
            return result.fail("批核失败,无权限或已批核");
    }
    @PutMapping("/submit")
    public ReturnDTO submitDoc(@RequestBody Document document){
        if (documentService.submitDoc(document))
            return result.success(document);
        else
            return result.fail("提交失败,文件已提交或标题重复");
    }
    @PutMapping("/save")
    public ReturnDTO saveDoc(@RequestBody Document document){
        if (documentService.saveDoc(document))
            return result.success(document);
        else
            return result.fail("保存失败，标题重复");
    }
    @DeleteMapping("/{id}")
    public ReturnDTO delete(@PathVariable("id")String id){
        try {
            if (documentService.delDoc(Integer.valueOf(id)))
                return result.success();
            else
                return result.fail("删除失败，该公文无法删除");

        }catch (Exception e){
            return  result.fail("删除失败，请联系管理员");
        }

    }

    @GetMapping("/all")
    public ModelAndView all(){
        ModelAndView mv=new ModelAndView();
        List<Document> documentList=documentService.getDocList(null);
        mv.addObject(documentList);
        mv.addObject("title","公文管理");
        mv.setViewName("document-list");
        return  mv;
    }

    @GetMapping("/myDoc")
    public ModelAndView myDoc(){
        ModelAndView mv=new ModelAndView();
        List<Document> documentList=documentService.getDocByUserId(Global.getUser().getId());
        mv.addObject(documentList);
        mv.addObject("title","我提交的");
        mv.setViewName("document-list");
        return  mv;
    }

    @GetMapping("/activeToMe")
    public ModelAndView activeToMe(){
        ModelAndView mv=new ModelAndView();
        List<Document> documentList=documentService.getDocActiveByUserId(Global.getUser().getId());
        mv.addObject(documentList);
        mv.addObject("title","待审核");
        mv.setViewName("document-list");
        return  mv;
    }

}
