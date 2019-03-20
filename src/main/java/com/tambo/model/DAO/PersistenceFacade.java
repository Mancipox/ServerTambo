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
import javax.persistence.Query;

/**
 *
 * @author usuario
 * @param <T>
 */
public class PersistenceFacade<T> implements IPersistenceFacade {

    EntityManagerFactory emf;
    private T object;

    public PersistenceFacade(T object) {
        this.object = object;
        this.emf = Persistence.createEntityManagerFactory("TAMBO");
    }


    
    
    @Override
    public List search() throws Exception {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT o FROM " + object.getClass().getSimpleName() + " o ";
        List data = null;
        try {
            data = em.createQuery(query).getResultList();
            em.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List searchByCriteria(List<String> crit, List values) throws Exception {
    EntityManager em = emf.createEntityManager();
    String query     = "SELECT o FROM " + object.getClass().getSimpleName() + " o WHERE ";
    query+=crit.get(0)+":param"+String.valueOf(0);
        System.out.println(query);
    for (int i = 1; i < crit.size(); i++) {
            query+="AND "+crit.get(i)+":param"+String.valueOf(i);
            System.out.println(query);
        }
    List data=null;
   
        Query quer=em.createQuery(query);
        for (int i = 0; i < crit.size(); i++) {
            quer.setParameter("param"+String.valueOf(i), values.get(i));
        }
        data=quer.getResultList();
        em.close();

       return data;  

   
    }

    @Override
    public boolean make() throws Exception {
         EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    
    
    }

    @Override
    public boolean update(List<String> crit, List values) throws Exception {
           EntityManager em = emf.createEntityManager();
    String query     = "UPDATE " + object.getClass().getSimpleName() + " o SET ";
    query+=crit.get(0)+":param"+String.valueOf(0);
    for (int i = 1; i < crit.size()-1; i++) {
            query+=", "+crit.get(i)+":param"+String.valueOf(i);
        }
    query+=" WHERE "+crit.get(crit.size()-1)+":param"+String.valueOf(crit.size()-1);
        System.out.println(query);

em.getTransaction().begin();
        Query quer=em.createQuery(query);
        for (int i = 0; i < crit.size(); i++) {
            quer.setParameter("param"+String.valueOf(i), values.get(i));
        }
        quer.executeUpdate();
        em.getTransaction().commit();
        em.close();

       return true;  

    }


}
