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
                crit.add("o.studentEmail !=");
                crit.add("o.teacherEmail =");
                values.add(usertemp);
                values.add(null);
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
        Question qtemp = (Question) Utils.fromJson(question, Question.class);
        boolean res = facade.make(qtemp);
        return Utils.toJson(res);
    }

    public String updateQuestion(String jsonQuestion) throws Exception {
        question = (Question) Utils.fromJson(jsonQuestion, Question.class);
        crit.add("o.description=");
        values.add(question.getDescription());
        crit.add("o.karma=");
        values.add(question.getKarma());
        crit.add("o.state=");
        values.add(question.getState());
        crit.add("o.studentEmail=");
        values.add(question.getStudentEmail());
        crit.add("o.teacherEmail=");
        values.add(question.getTeacherEmail());
        crit.add("o.questionId=");
        values.add(question.getQuestionId());
        boolean res = facade.update(question, crit, values);
        crit.clear();
        values.clear();
        return Utils.toJson(res);
    }
}
