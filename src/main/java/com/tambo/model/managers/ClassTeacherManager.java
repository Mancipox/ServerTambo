
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.managers;

import com.tambo.facade.IPersistenceFacade;
import com.tambo.facade.PersistenceFacadeFactory;
import com.tambo.model.VO.Classteacher;
import com.tambo.model.VO.User;
import com.tambo.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ClassTeacherManager {
     String jsonCT;
    List<String> crit = new ArrayList<>();
    List values = new ArrayList<>();
    List<Classteacher> classteachers;
    Classteacher classteacher= new Classteacher();
    IPersistenceFacade facade;

    public ClassTeacherManager() {
        facade = new PersistenceFacadeFactory().getNewFacade();
    }

    public String getTeachers(String classx) throws Exception {
        try{        
        crit.add("o.classId.classId =");
                values.add(Integer.valueOf(classx));
                List<User> users=facade.searchElementByCriteria("teacherEmail",classteacher, crit, values);
                jsonCT = Utils.toJson(users);
                System.out.println(jsonCT);
        crit.clear();
        values.clear();
        }
        catch(Exception e){
                 crit.clear();
        values.clear();
                }
        return jsonCT;
    }

    public String getCT() throws Exception {
        classteachers = facade.search(new Classteacher());
        jsonCT = Utils.toJson(classteachers);
        return jsonCT;
    }

    public String persistCT(String contact) throws Exception {
        return updateCT(contact);
    }

    public String updateCT(String jsonclasst) throws Exception {
        boolean res;
        classteacher = (Classteacher) Utils.fromJson(jsonclasst, Classteacher.class);
             res= facade.update(classteacher, "o.ctId", classteacher.getCtId());
        crit.clear();
        values.clear();
        return Utils.toJson(res);
    }
    public String deleteCT(String param) throws Exception{
            crit.add("o.classId.classId=");
    values.add(Integer.valueOf(param));
    Boolean res=facade.deleteByCriteria(classteacher, crit, values);
    jsonCT = Utils.toJson(res);
        crit.clear();
        values.clear();
        return jsonCT;
    }
}

