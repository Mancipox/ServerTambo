
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.managers;

import com.tambo.facade.IPersistenceFacade;
import com.tambo.facade.PersistenceFacadeFactory;
import com.tambo.model.VO.Question;
import com.tambo.model.VO.User;
import com.tambo.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class QuestionManager {

    String jsonQuestions;
    List<String> crit = new ArrayList<>();
    List values = new ArrayList<>();
    List<Question> questions;
    Question question= new Question();
    IPersistenceFacade facade;

    public QuestionManager() {
        facade = new PersistenceFacadeFactory().getNewFacade();
    }

    public String getQuestions(String option, String user) throws Exception {
        User usertemp = (User) Utils.fromJson(user, User.class);
        switch (option) {
            case "askedBy":
                crit.add("o.studentEmail =");
                values.add(usertemp);
                questions = facade.searchByCriteria(question, crit, values);
                jsonQuestions = Utils.toJson(questions);
                break;
            case "answeredBy":
                crit.add("o.teacherEmail =");
                values.add(usertemp);
                questions = facade.searchByCriteria(question, crit, values);
                jsonQuestions = Utils.toJson(questions);
                break;
            case "except":
                crit.add("(o.teacherEmail =:param0 OR o.teacherEmail=null) AND o.studentEmail != ");
                values.add(usertemp);
              //  crit.add("o.studentEmail !=");
               // crit.add("o.teacherEmail =");
                values.add(usertemp);
                //values.add(null);
                questions = facade.searchByCriteria(question, crit, values);
                jsonQuestions = Utils.toJson(questions);
                break;
            default:
                jsonQuestions = "";
                break;
        }
        crit.clear();
        values.clear();
        questions.clear();
        return jsonQuestions;

    }

    public String getQuestions() throws Exception {
        questions = facade.search(new Question());
        jsonQuestions = Utils.toJson(questions);
        return jsonQuestions;
    }

    public String persistQuestion(String question) throws Exception {
        return updateQuestion(question);
    }

    public String updateQuestion(String jsonQuestion) throws Exception {
        boolean res;
        question = (Question) Utils.fromJson(jsonQuestion, Question.class);

        if (question.getTeacherEmail()!= null){
        
         res= facade.update(question, "o.questionId", question.getQuestionId()) && 
                facade.update(question.getMeetingId(), "o.meetingId", question.getMeetingId().getMeetingId()) && 
                facade.update(question.getStudentEmail(), "o.email", question.getStudentEmail().getEmail()) &&
                facade.update(question.getTeacherEmail(), "o.email", question.getTeacherEmail().getEmail());
        }
        else{
             res= facade.update(question, "o.questionId", question.getQuestionId()) && 
                facade.update(question.getMeetingId(), "o.meetingId", question.getMeetingId().getMeetingId()) && 
                facade.update(question.getStudentEmail(), "o.email", question.getStudentEmail().getEmail());
        }
        crit.clear();
        values.clear();
        return Utils.toJson(res);
    }
}
