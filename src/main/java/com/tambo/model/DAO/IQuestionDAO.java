/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.DAO;


import com.tambo.model.VO.Question;
import com.tambo.model.VO.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IQuestionDAO {
    public List<Question> askedBy(User user) throws Exception;
    public List<Question> answeredBy(User user)throws Exception;
     public List<Question> questionsexc(User user)throws Exception;
    public List<Question> questions()throws Exception;
    public boolean makeQuestion(Question question)throws Exception;
    public boolean updateQuestion(Question question)throws Exception;
}
