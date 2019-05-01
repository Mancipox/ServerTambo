
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
     String jsonQuestions;
    List<String> crit = new ArrayList<>();
    List values = new ArrayList<>();
    List<Classteacher> classteachers;
    Classteacher classteacher= new Classteacher();
    IPersistenceFacade facade;

    public ClassTeacherManager() {
        facade = new PersistenceFacadeFactory().getNewFacade();
    }
/*
    public String getTeachers(String classx) throws Exception {
                crit.add("o.contactPK.studentEmail =");
                values.add(classx);
                List<User> users=facade.searchElementByCriteria("user1",classteacher, crit, values);
                jsonQuestions = Utils.toJson(users);
                System.out.println(jsonQuestions);
        crit.clear();
        values.clear();
        return jsonQuestions;

    }

    public String getCT() throws Exception {
        classteachers = facade.search(new Classteacher());
        jsonQuestions = Utils.toJson(classteachers);
        return jsonQuestions;
    }

    public String persistCT(String contact) throws Exception {
        return updateCT(contact);
    }

    public String updateCT(String jsonclasst) throws Exception {
        boolean res;
        classteacher = (Classteacher) Utils.fromJson(jsonclasst, Classteacher.class);
             res= facade.update(classteacher, "o.contactPK", classteacher.getContactPK());
        crit.clear();
        values.clear();
        return Utils.toJson(res);
    }*/
}

