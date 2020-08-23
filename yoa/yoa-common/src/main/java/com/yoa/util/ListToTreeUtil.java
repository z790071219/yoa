package com.yoa.util;

import com.yoa.base.model.TreeModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * List转换成Tree结构的嵌套List
 */
@Data
public class ListToTreeUtil {
    public static <T extends TreeModel> List<T> listToTree(List<T> list){
        List<T> tree=new ArrayList<T>();
        for (T t:list)
        {
            boolean flag=false;
            for (T t1:list){
                if (t.getPNode()!=-1&&t.getPNode()==t1.getNode()){
                    if (t1.getChildList()==null)
                        t1.setChildList(new ArrayList<T>());
                    t1.getChildList().add(t);
                    flag=true;
                    break;
                }
            }
            if (!flag){
                tree.add(t);
            }
        }
        return tree;
    }


}
