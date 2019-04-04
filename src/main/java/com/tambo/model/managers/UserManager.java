<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0882a9bc95e9f1f3b15a299694d090b51e18c70b
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

    public UserManager() {
        facade = new PersistenceFacadeFactory().getNewFacade();
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

    public String isUser(String jsonU) throws Exception {
        user = (User) Utils.fromJson(jsonU, User.class);
        crit.add("o.email=");
        crit.add("o.password=");
        values.add(user.getEmail());
        values.add(user.getPassword());
        users = facade.searchByCriteria(user, crit, values);
        if (users.size() != 0) {
            return Utils.toJson(users.get(0));
        } else {
            return Utils.toJson(null);
        }
    }

    public String persist(String jsonU) throws Exception {
        user = (User) Utils.fromJson(jsonU, User.class);
        boolean res = facade.make(user);
        return Utils.toJson(res);
    }

    public String update(String jsonU) throws Exception {
        user = (User) Utils.fromJson(jsonU, User.class);
        boolean res = facade.update(user, "o.email", user.getEmail());
        return Utils.toJson(res);
    }
}
<<<<<<< HEAD
=======
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

    public UserManager() {
        facade = new PersistenceFacadeFactory().getNewFacade();
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

    public String isUser(String jsonU) throws Exception {
        User user = (User) Utils.fromJson(jsonU, User.class);
        System.out.println("Usuario es desde manager"+jsonU);
        crit.add("o.email=");
        crit.add("o.password=");
        values.add(user.getEmail());
        values.add(user.getPassword());
        List<User> users = facade.searchByCriteria(user, crit, values);
        if (users.size() != 0) {
            user=users.get(0);
            values.clear();
            crit.clear();
            return Utils.toJson(user);
        } else {
            return Utils.toJson(null);
        }
        
    }
    public String userToken(String jsonU){
         user = (User) Utils.fromJson(jsonU, User.class);
         String token = TokenUtil.createJWT("Log01", "UserManager", user.getUserName(), 900000);
         return token;
    }

    public String persist(String jsonU) throws Exception {
        user = (User) Utils.fromJson(jsonU, User.class);
        boolean res = facade.make(user);
        return Utils.toJson(res);
    }

    public String update(String jsonU) throws Exception {
        user = (User) Utils.fromJson(jsonU, User.class);
        boolean res = facade.update(user, "o.email", user.getEmail());
        return Utils.toJson(res);
    }
}
>>>>>>> origin/JWTImp
=======
>>>>>>> 0882a9bc95e9f1f3b15a299694d090b51e18c70b
