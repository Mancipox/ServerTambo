package com.tambo.model.managers;

import com.tambo.facade.IPersistenceFacade;
import com.tambo.facade.PersistenceFacadeFactory;
import com.tambo.model.VO.Class;
import com.tambo.model.VO.Contact;
import com.tambo.model.VO.ContactPK;
import com.tambo.model.VO.Topic;
import com.tambo.model.VO.User;
import com.tambo.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ClassManager {

    String jsonClasss;
    List<String> crit = new ArrayList<>();
    List values = new ArrayList<>();
    List<Class> classxs;
    Class classx = new Class();
    IPersistenceFacade facade;

    public ClassManager() {
        facade = new PersistenceFacadeFactory().getNewFacade();
    }

    public String getClasss(String option, String user) throws Exception {
        User usertemp = null;
        if (!option.equals("byTopic")) {
            usertemp = (User) Utils.fromJson(user, User.class);
        }

        switch (option) {
            case "askedBy":
                crit.add("o.studentEmail =");
                values.add(usertemp);
                classxs = facade.searchByCriteria(classx, crit, values);
                jsonClasss = Utils.toJson(classxs);
                break;
            case "answeredBy":
                crit.add("o.teacherEmail =");
                values.add(usertemp);
                classxs = facade.searchByCriteria(classx, crit, values);
                jsonClasss = Utils.toJson(classxs);
                break;
            case "except":
                crit.add("(o.teacherEmail =:param0 OR o.teacherEmail=null) AND o.studentEmail != ");
                values.add(usertemp);
                classxs = facade.searchByCriteria(classx, crit, values);
                jsonClasss = Utils.toJson(classxs);
                break;
            case "calendar":
                crit.add("o.teacherEmail =:param0 OR o.studentEmail = ");
                values.add(usertemp);
                classxs = facade.searchByCriteria(classx, crit, values);
                jsonClasss = Utils.toJson(classxs);
                break;
            default:
                jsonClasss = "";
                break;
        }
        crit.clear();
        values.clear();
        classxs.clear();
        return jsonClasss;

    }

    public String getClass(String option, String user, String topic) throws Exception {
        User usertemp = null;
        usertemp = (User) Utils.fromJson(user, User.class);
        crit.add("(o.teacherEmail =:param0 OR o.teacherEmail=null) AND o.studentEmail != ");
        crit.add("o.topicId.description =");
        values.add(usertemp);
        values.add(topic);
        classxs = facade.searchByCriteria(classx, crit, values);
        jsonClasss = Utils.toJson(classxs);
        crit.clear();
        values.clear();
        classxs.clear();
        return jsonClasss;
    }

    public String getClasss() throws Exception {
        classxs = facade.search(new Class());
        jsonClasss = Utils.toJson(classxs);
        return jsonClasss;
    }

    public String persistClass(String classx) throws Exception {
        return updateClass(classx);
    }
public String deleteClass(Class clasx) throws Exception{
    crit.add("o.classId=");
    values.add(clasx.getClassId());
    Boolean res=facade.deleteByCriteria(clasx, crit, values);
    jsonClasss = Utils.toJson(res);
        crit.clear();
        values.clear();
        return jsonClasss;
}
    public String updateClass(String jsonClass) throws Exception {
        boolean res;
        classx = (Class) Utils.fromJson(jsonClass, Class.class);

        if (classx.getTeacherEmail() != null) {
(new ClassTeacherManager()).deleteCT(classx.getClassId().toString());
            res = facade.update(classx, "o.classId", classx.getClassId())
                    && facade.update(classx.getMeetingId(), "o.meetingId", classx.getMeetingId().getMeetingId())
                    && facade.update(classx.getStudentEmail(), "o.email", classx.getStudentEmail().getEmail())
                    && facade.update(classx.getTeacherEmail(), "o.email", classx.getTeacherEmail().getEmail());
        } else {
            res = facade.update(classx, "o.classId", classx.getClassId())
                    && facade.update(classx.getMeetingId(), "o.meetingId", classx.getMeetingId().getMeetingId())
                    && facade.update(classx.getStudentEmail(), "o.email", classx.getStudentEmail().getEmail());
        }
        try{
        if (classx.getState()){
            Contact ct=new Contact(new ContactPK(classx.getTeacherEmail().getEmail(),classx.getStudentEmail().getEmail()),classx.getTeacherEmail(),classx.getStudentEmail());
           String con=(new ContactManager()).getContacts(ct.getContactPK().getStudentEmail());
            if (!con.contains(ct.getContactPK().getTeacherEmail()))facade.make(ct);
        }
        if(classx.getDownvote()>=20){
            (new ClassTeacherManager()).deleteCT(classx.getClassId().toString());
            this.deleteClass(classx);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        crit.clear();
        values.clear();
        return Utils.toJson(res);
    }
}

