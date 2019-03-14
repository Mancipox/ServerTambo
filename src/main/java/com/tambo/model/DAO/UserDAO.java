/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.DAO;

import com.tambo.model.VO.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class UserDAO implements IUserDAO {

    EntityManagerFactory emf;

    public UserDAO() {
        this.emf = Persistence.createEntityManagerFactory("TAMBO");
    }

    @Override
    public User isUser(User user) throws Exception {
        EntityManager em = emf.createEntityManager();
        String consulta = "SELECT u FROM User u WHERE u.email= :uemail AND u.password=:upass";
        User usertemp = null;
        try {
            usertemp = (User) em.createQuery(consulta).setParameter("uemail", user.getEmail()).setParameter("upass", user.getPassword()).getSingleResult();
            em.close();
            if (usertemp.getEmail().equals(user.getEmail())) {
                return usertemp;
            } else {
                return usertemp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return usertemp;
        }
    }

    @Override
    public boolean addUser(User user) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        EntityManager em = emf.createEntityManager();
        String consulta = "UPDATE User u SET u.password=:upass, u.userName=:usrname"
                + ",u.firstName=:ufirst,u.lastName=:ulast,u.phone=:uphone,"
                + "u.gender=:ugender,u.karma=:ukarma Where u.email=:uemail";
        try {
            em.getTransaction().begin();
            em.createQuery(consulta).setParameter("upass", user.getPassword())
                    .setParameter("usrname", user.getUserName())
                    .setParameter("ufirst", user.getFirstName())
                    .setParameter("ulast", user.getLastName())
                    .setParameter("uphone", user.getPhone())
                    .setParameter("ugender", user.getGender())
                    .setParameter("ukarma", user.getKarma())
                    .setParameter("uemail", user.getEmail())
                    .executeUpdate();
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User searchUser(String email) throws Exception {
        EntityManager em = emf.createEntityManager();
        String consulta = "SELECT u FROM User u WHERE u.email=:uemail";
        User usertemp = null;
        try {
            usertemp = (User) em.createQuery(consulta).setParameter("uemail", email).getSingleResult();
            em.close();
            return usertemp;
        } catch (Exception e) {
            e.printStackTrace();
            return usertemp;
        }
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        EntityManager em = emf.createEntityManager();
        String consulta = "SELECT u FROM User u ";
        List<User> users = null;
        try {
            users = em.createQuery(consulta).getResultList();
            em.close();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
