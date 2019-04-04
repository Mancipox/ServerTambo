/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.DAO;


import com.tambo.model.VO.Question;
import com.tambo.model.VO.User;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class QuestionDAO implements IQuestionDAO{
EntityManagerFactory emf;

    public QuestionDAO() {
 this.emf =Persistence.createEntityManagerFactory("TAMBO");
    }

    @Override
    public List<Question> askedBy(User user) throws Exception {
        EntityManager em = emf.createEntityManager();
        String consulta = "SELECT q FROM Question q WHERE q.studentEmail =:user ";
        List<Question> questions = null;
        try {
            questions = em.createQuery(consulta).setParameter("user", user).getResultList();
            em.close();
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }      
    }

    @Override
    public List<Question> answeredBy(User user) throws Exception {
    EntityManager em = emf.createEntityManager();
        String consulta = "SELECT q FROM Question q WHERE q.teacherEmail =:user";
        List<Question> questions = null;
        try {
            questions = em.createQuery(consulta).setParameter("user", user).getResultList();
            em.close();
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }         
    }

    @Override
    public List<Question> questionsexc(User user) throws Exception {
    EntityManager em = emf.createEntityManager();
<<<<<<< HEAD
        String consulta = "SELECT q FROM Question q WHERE (q.teacherEmail =:user OR q.teacherEmail =null) AND q.studentEmail !=:user";
=======
        String consulta = "SELECT q FROM Question q WHERE q.studentEmail !=:user and q.teacherEmail =null";
>>>>>>> 3rd_iter
        List<Question> questions = null;
        try {
            questions = em.createQuery(consulta).setParameter("user", user).getResultList();
            em.close();
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }       
    }

    @Override
    public List<Question> questions() throws Exception {
      EntityManager em = emf.createEntityManager();
        String consulta = "SELECT q FROM Question q ";
        List<Question> questions = null;
        try {
            questions = em.createQuery(consulta).getResultList();
            em.close();
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }

    @Override
    public boolean makeQuestion(Question question) throws Exception {
             EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(question);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    
    }

    @Override
    public boolean updateQuestion(Question question) throws Exception {
     EntityManager em = emf.createEntityManager();
        String consulta = "UPDATE Question q SET q.description=:qdesc, q.karma=:qkarma"
                + ",q.state=:qstate,q.studentEmail=:qstudent,q.teacherEmail=:qteacher "
                + "WHERE q.questionId=:qid";
        try {
            em.getTransaction().begin();
            em.createQuery(consulta).setParameter("qdesc",question.getDescription())
                    .setParameter("qkarma", question.getKarma())
                    .setParameter("qstate", question.getState())
                    .setParameter("qstudent", question.getStudentEmail())
                    .setParameter("qteacher", question.getTeacherEmail())
                    .setParameter("qid", question.getQuestionId())
                    .executeUpdate();
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    
    }


}
