<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0882a9bc95e9f1f3b15a299694d090b51e18c70b
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.facade;

import com.tambo.model.VO.Meeting;
import com.tambo.model.VO.Question;
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
public class PersistenceFacade<T> implements IPersistenceFacade<T> {

    EntityManagerFactory emf;

    public PersistenceFacade() {
        this.emf = Persistence.createEntityManagerFactory("TAMBO");
    }

    @Override
    public List search(T object) throws Exception {
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
    public List searchByCriteria(T object, List<String> crit, List values) throws Exception {
        EntityManager em = emf.createEntityManager();
        String perclass = object.getClass().getSimpleName();
        System.out.println(perclass);
        String query = "SELECT o FROM " + perclass + " o WHERE ";
        query += crit.get(0) + ":param" + String.valueOf(0);
        System.out.println(query);
        for (int i = 1; i < crit.size(); i++) {
            query += " AND " + crit.get(i) + ":param" + String.valueOf(i);
        }
        List data = null;

        Query quer = em.createQuery(query);
        for (int i = 0; i < crit.size(); i++) {
            quer.setParameter("param" + String.valueOf(i), values.get(i));
        }
        data = quer.getResultList();
        em.close();

        return data;

    }

    @Override
    public boolean make(T object) throws Exception {
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
    public boolean update(T object, String crit, Object value) throws Exception {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT o FROM " + object.getClass().getSimpleName() + " o WHERE " + crit + "=:param0";
        try {
            em.getTransaction().begin();
            String dataclass = object.getClass().getSimpleName();
            switch (dataclass) {
                case "Question":
                    Question dataq = (Question) em.createQuery(query).setParameter("param0", value).getSingleResult();
                    dataq.setAll((Question) object);
                    em.persist(dataq);
                    break;
                case "User":
                    User datau = (User) em.createQuery(query).setParameter("param0", value).getSingleResult();
                    datau.setAll((User) object);
                    em.persist(datau);
                    break;
                case "Meeting":
                    Meeting datam = (Meeting) em.createQuery(query).setParameter("param0", value).getSingleResult();
                    datam.setAll((Meeting) object);
                    em.persist(datam);
                    break;
            }
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        /*String query     = "UPDATE " + object.getClass().getSimpleName() + " o SET ";
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
         */

    }

}
<<<<<<< HEAD
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.facade;

import com.tambo.model.VO.Meeting;
import com.tambo.model.VO.Question;
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
public class PersistenceFacade<T> implements IPersistenceFacade<T> {

    EntityManagerFactory emf;

    public PersistenceFacade() {
        this.emf = Persistence.createEntityManagerFactory("TAMBO");
    }

    @Override
    public List search(T object) throws Exception {
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
    public List searchByCriteria(T object, List<String> crit, List values) throws Exception {
        EntityManager em = emf.createEntityManager();
        String perclass = object.getClass().getSimpleName();
        System.out.println(perclass);
        String query = "SELECT o FROM " + perclass + " o WHERE ";
        query += crit.get(0) + ":param" + String.valueOf(0);
        System.out.println(query);
        for (int i = 1; i < crit.size(); i++) {
            query += " AND " + crit.get(i) + ":param" + String.valueOf(i);
        }
        List data = null;

        Query quer = em.createQuery(query);
        for (int i = 0; i < crit.size(); i++) {
            quer.setParameter("param" + String.valueOf(i), values.get(i));
        }
        data = quer.getResultList();
        em.close();

        return data;

    }

    @Override
    public boolean make(T object) throws Exception {
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
    public boolean update(T object, String crit, Object value) throws Exception {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT o FROM " + object.getClass().getSimpleName() + " o WHERE " + crit + "=:param0";
        try {
            em.getTransaction().begin();
            String dataclass = object.getClass().getSimpleName();
            switch (dataclass) {
                case "Question":
                    Question dataq = (Question) em.createQuery(query).setParameter("param0", value).getSingleResult();
                    dataq.setAll((Question) object);
                    em.persist(dataq);
                    break;
                case "User":
                    User datau = (User) em.createQuery(query).setParameter("param0", value).getSingleResult();
                    datau.setAll((User) object);
                    em.persist(datau);
                    break;
                case "Meeting":
                    Meeting datam = (Meeting) em.createQuery(query).setParameter("param0", value).getSingleResult();
                    datam.setAll((Meeting) object);
                    em.persist(datam);
                    break;
            }
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        /*String query     = "UPDATE " + object.getClass().getSimpleName() + " o SET ";
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
         */

    }

}
>>>>>>> origin/JWTImp
=======
>>>>>>> 0882a9bc95e9f1f3b15a299694d090b51e18c70b
