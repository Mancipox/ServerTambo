/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.DAO;


import com.tambo.model.VO.Meeting;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class MeetingDAO implements IMeetingDAO{
EntityManagerFactory emf;

    public MeetingDAO() {
        this.emf =Persistence.createEntityManagerFactory("TAMBO");
    }

  
    @Override
    public int makeMeet(Meeting meet) throws Exception {
         EntityManager em = emf.createEntityManager();
         String consulta = "SELECT m FROM Meeting m WHERE m.meetingDate=:mdate AND m.place=:mplace";
        try {
            
            Meeting aux=((Meeting)em.createQuery(consulta).setParameter("mdate", meet.getMeetingDate())
                    .setParameter("mplace", meet.getPlace()).getSingleResult());
            if (aux!=null) {
                em.close();
                return aux.getMeetingId();
            }
            else{
            em.getTransaction().begin();
            em.persist(meet);
            em.getTransaction().commit();
            int id=((Meeting)em.createQuery(consulta).setParameter("mdate", meet.getMeetingDate())
                    .setParameter("mplace", meet.getPlace()).getSingleResult()).getMeetingId();
            em.close();
            return id;
            }
            
        } catch (Exception e) {
           em.getTransaction().begin();
            em.persist(meet);
            em.getTransaction().commit();
            int id=((Meeting)em.createQuery(consulta).setParameter("mdate", meet.getMeetingDate())
                    .setParameter("mplace", meet.getPlace()).getSingleResult()).getMeetingId();
            em.close();
            return id;
        }
    }

    @Override
    public List<Meeting> meets() throws Exception {
     EntityManager em = emf.createEntityManager();
        String consulta = "SELECT m FROM Meeting m ";
        List<Meeting> meets = null;
        try {
            meets = em.createQuery(consulta).getResultList();
            em.close();
            return meets;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
