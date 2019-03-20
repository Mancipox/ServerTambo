/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.servlets;

import com.tambo.model.DAO.IMeetingDAO;
import com.tambo.model.DAO.IPersistenceFacade;
import com.tambo.model.DAO.IQuestionDAO;
import com.tambo.model.DAO.IUserDAO;
import com.tambo.model.DAO.MeetingDAO;
import com.tambo.model.DAO.PersistenceFacade;
import com.tambo.model.DAO.QuestionDAO;
import com.tambo.model.DAO.UserDAO;
import com.tambo.model.VO.Meeting;
import com.tambo.model.VO.Question;
import com.tambo.model.VO.User;
import com.tambo.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
public class ServletQuestion extends HttpServlet {

    IPersistenceFacade facade= new PersistenceFacade(new Question());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> crit = new ArrayList<>();
                    List values = new ArrayList<>();
        List<Question> questions;
        User usertemp;
        String jsonQuestions = "";
        try {
            PrintWriter out = response.getWriter();
            String opt = request.getParameter("option");
            switch (opt) {
                case "all":
                    questions = facade.search();
                    jsonQuestions = Utils.toJson(questions);
                    System.out.println(jsonQuestions.replace("{", "{\n").replace("}", "}\n"));
                    out.print(jsonQuestions);
                    break;
                case "askedBy":
                    
                    
                    usertemp = (User) Utils.fromJson(request.getParameter("user"), User.class);
                    crit.add("o.studentEmail =");
                    values.add(usertemp);
                    questions = facade.searchByCriteria(crit, values);
                    jsonQuestions = Utils.toJson(questions);
                    System.out.println(jsonQuestions.replace("{", "{\n").replace("}", "}\n"));
                    out.print(jsonQuestions);
                    break;
                case "answeredBy":

                    usertemp = (User) Utils.fromJson(request.getParameter("user"), User.class);
                    crit.add("o.teacherEmail =");
                    values.add(usertemp);
                    questions = facade.searchByCriteria(crit, values);
                    jsonQuestions = Utils.toJson(questions);
                    System.out.println(jsonQuestions.replace("{", "{\n").replace("}", "}\n"));
                    out.print(jsonQuestions);
                    break;
                case "except":
                   
                    usertemp = (User) Utils.fromJson(request.getParameter("user"), User.class);
                    crit.add("o.studentEmail !=");
                    crit.add("o.teacherEmail =");
                    values.add(usertemp);
                    values.add(null);
                    questions = facade.searchByCriteria(crit, values);
                    jsonQuestions = Utils.toJson(questions);
                    System.out.println(jsonQuestions.replace("{", "{\n").replace("}", "}\n"));
                    out.print(jsonQuestions);
                    break;
                default:
                    System.out.println("no se ha ingresado un parametro aceptable");
                    break;
            }

            //processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String jsonQ = request.getParameter("Question");
        try {
            Question qtemp = (Question) Utils.fromJson(jsonQ, Question.class);
            facade= new PersistenceFacade(qtemp);
            boolean res = facade.make();
            out.println(Utils.toJson(res));
            System.out.println(Utils.toJson(res));
        } catch (Exception ex) {
            Logger.getLogger(ServletQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                List<String> crit = new ArrayList<>();
                    List values = new ArrayList<>();
//la modificacion de registros ira aqui      
//  processRequest(request, response);
        PrintWriter out = response.getWriter();
        String opt = request.getParameter("option");
        try {
            switch (opt) {
                case ("student"): {
                    String jsonQ = request.getParameter("Question");
                    Question qtemp = (Question) Utils.fromJson(jsonQ, Question.class);
                    User utemp = qtemp.getTeacherEmail();
                    utemp.setKarma(utemp.getKarma()+qtemp.getKarma());
                    qtemp.setKarma(0);
                    qtemp.setTeacherEmail(utemp);
                    facade= new PersistenceFacade(qtemp);
                    crit.add("o.description=");
                    values.add(qtemp.getDescription());
                    crit.add("o.karma=");
                    values.add(qtemp.getKarma());
                    crit.add("o.state=");
                    values.add(qtemp.getState());
                    crit.add("o.studentEmail=");
                    values.add(qtemp.getStudentEmail());
                    crit.add("o.teacherEmail=");
                    values.add(qtemp.getTeacherEmail());
                    crit.add("o.questionId=");
                    values.add(qtemp.getQuestionId());
                    boolean res = facade.update(crit,values);
                    out.println(Utils.toJson(res));
                    System.out.println(Utils.toJson(res));
                    break;
                }
                case ("teacher"): {
                    String jsonQ = request.getParameter("Question");
                    Question qtemp = (Question) Utils.fromJson(jsonQ, Question.class);
                                        facade= new PersistenceFacade(qtemp);
                    crit.add("o.description=");
                    values.add(qtemp.getDescription());
                    crit.add("o.karma=");
                    values.add(qtemp.getKarma());
                    crit.add("o.state=");
                    values.add(qtemp.getState());
                    crit.add("o.studentEmail=");
                    values.add(qtemp.getStudentEmail());
                    crit.add("o.teacherEmail=");
                    values.add(qtemp.getTeacherEmail());
                    crit.add("o.questionId=");
                    values.add(qtemp.getQuestionId());
                    boolean res = facade.update(crit,values);
                    out.println(Utils.toJson(res));
                    System.out.println(Utils.toJson(res));
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ServletQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//la eliminacion de registros ira aqui       
// processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
