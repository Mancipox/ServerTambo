
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.managers;

import com.tambo.facade.IPersistenceFacade;
import com.tambo.facade.PersistenceFacadeFactory;
import com.tambo.model.VO.User;
import com.tambo.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import com.tambo.utils.TokenUtil;
/**
 *
 * @author usuario
 */
public class UserManager {

    User user = new User();
    List<String> crit = new ArrayList<>();
    List values = new ArrayList<>();
    List<User> users;
    IPersistenceFacade facade;
    String jsonObj;
 private static UserManager usmngr;
    private UserManager() {
        facade = PersistenceFacadeFactory.getNewFacade();
    }
    
    private synchronized static void createInstance() {
        if (usmngr == null) { 
            usmngr = new UserManager();
        }
    }
 
    public static UserManager getInstance() {
        createInstance();

        return usmngr;
    }

    
    
    
    

    public String getUsers() throws Exception {
        users = facade.search(user);
        jsonObj = Utils.toJson(users);
        return jsonObj;
    }

    public String getUser(String email) throws Exception {
        crit.add("o.email=");
        values.add(email);
        users = facade.searchByCriteria(user, crit, values);
        user = users.get(0);
        jsonObj = Utils.toJson(user);
        crit.clear();
        values.clear();
        return jsonObj;
    }
    
       public String userToken(String jsonU){
         user = (User) Utils.fromJson(jsonU, User.class);
         String token = TokenUtil.createJWT("Log01", "UserManager", user.getUserName(), 900000);
         return token;
    }

    public String isUser(String jsonU) throws Exception {
        try{
        user = (User) Utils.fromJson(jsonU, User.class);
        crit.add("o.email=");
        crit.add("o.password=");
        values.add(user.getEmail());
        values.add(user.getPassword());
        users = facade.searchByCriteria(user, crit, values);
        if (users.size() != 0) {
            crit.clear();
            values.clear();
            return Utils.toJson(users.get(0));
        } else {
            crit.clear();
            values.clear();
            return Utils.toJson(null);
        }
        }catch(Exception e){
            crit.clear();
            values.clear();
            e.printStackTrace();
            return Utils.toJson(null);
        }
    }

    public String persist(String jsonU) throws Exception {
        return this.update(jsonU);
    }

    public String update(String jsonU) throws Exception {
        user = (User) Utils.fromJson(jsonU, User.class);
        boolean res = facade.update(user, "o.email", user.getEmail());
        return Utils.toJson(res);
    }
}
