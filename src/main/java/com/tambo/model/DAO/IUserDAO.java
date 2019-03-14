/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.DAO;

import com.tambo.model.VO.User;
import java.util.List;



/**
 *
 * @author usuario
 */
public interface IUserDAO {
    public User isUser(User user) throws Exception;
    public boolean addUser(User user) throws Exception;
    public boolean updateUser(User user) throws Exception;
    public User searchUser(String email) throws Exception;
    public List<User> getAllUsers() throws Exception;
}
