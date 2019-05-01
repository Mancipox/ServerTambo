package com.tambo.model.managers;


import com.tambo.facade.IPersistenceFacade;
import com.tambo.facade.PersistenceFacadeFactory;
import com.tambo.model.VO.Contact;
import com.tambo.model.VO.User;
import com.tambo.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ContactManager{
    String jsonQuestions;
    List<String> crit = new ArrayList<>();
    List values = new ArrayList<>();
    List<Contact> contacts;
    Contact contact= new Contact();
    IPersistenceFacade facade;

    public ContactManager() {
        facade = new PersistenceFacadeFactory().getNewFacade();
    }

    public String getContacts(String user) throws Exception {
                crit.add("o.contactPK.studentEmail =");
                values.add(user);
                List<User> users=facade.searchElementByCriteria("user1",contact, crit, values);
                crit.clear();
                crit.add("o.contactPK.teacherEmail =");
                users.addAll(facade.searchElementByCriteria("user",contact, crit, values));
                jsonQuestions = Utils.toJson(users);
                System.out.println(jsonQuestions);
        crit.clear();
        values.clear();
        return jsonQuestions;

    }

    public String getContacts() throws Exception {
        contacts = facade.search(new Contact());
        jsonQuestions = Utils.toJson(contacts);
        return jsonQuestions;
    }

    public String persistContact(String contact) throws Exception {
        return updateContact(contact);
    }

    public String updateContact(String jsonQuestion) throws Exception {
        boolean res;
        contact = (Contact) Utils.fromJson(jsonQuestion, Contact.class);
        System.out.println(contact.getContactPK().getStudentEmail());
             res= facade.update(contact, "o.contactPK", contact.getContactPK());
        crit.clear();
        values.clear();
        return Utils.toJson(res);
    }
}