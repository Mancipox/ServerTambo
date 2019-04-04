/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.servlets;

import com.tambo.model.managers.QuestionManager;
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
    
    QuestionManager qmanager = new QuestionManager();

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
        try {            
            PrintWriter out = response.getWriter();
            String opt = request.getParameter("option");
            if (opt.equals("all")) {
                out.print(qmanager.getQuestions());
            } else {
                String usertemp = request.getParameter("user");
                out.print(qmanager.getQuestions(opt, usertemp));
            }
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
<<<<<<< HEAD
        PrintWriter out = response.getWriter();
        String opt = request.getParameter("option");
        String jsonQ = request.getParameter("Question");
        try {
            switch (opt) {
               case ("student"): {
                    Question qtemp = (Question) Utils.fromJson(jsonQ, Question.class);
                    User utemp = qtemp.getTeacherEmail();
                    utemp.setKarma(utemp.getKarma() + qtemp.getKarma());
                    qtemp.setKarma(0);
                    boolean res = (qdao.updateQuestion(qtemp) && udao.updateUser(utemp));
                    out.println(Utils.toJson(res));
                    System.out.println(Utils.toJson(res));
                    break;
                }
                case ("teacher"): {
                    Question qtemp = (Question) Utils.fromJson(jsonQ, Question.class);
                    boolean res = qdao.updateQuestion(qtemp);
                    out.println(Utils.toJson(res));
                    System.out.println(Utils.toJson(res));
                    break;
                }
                case ("create"): {
                    Question qtemp = (Question) Utils.fromJson(jsonQ, Question.class);
                    Meeting mtemp = qtemp.getMeetingId();
                    int id = mdao.makeMeet(mtemp);
                    mtemp.setMeetingId(id);
                    qtemp.setMeetingId(mtemp);
                    System.out.println(qtemp.getMeetingId().getMeetingDate());
                    udao.updateUser(qtemp.getStudentEmail());
                    boolean res = qdao.makeQuestion(qtemp);
                    out.println(Utils.toJson(res));
                    System.out.println(Utils.toJson(res));
                    break;
                }
            }
=======
        //FALTA actualizar estudiante
        try {
            PrintWriter out = response.getWriter();
            String jsonQ = request.getParameter("Question");
            out.print(qmanager.persistQuestion(jsonQ));
>>>>>>> 3rd_iter
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
//FALTA actualizar profesor     
        PrintWriter out = response.getWriter();
        try {
<<<<<<< HEAD
            switch (opt) {
                case ("student"): {
                    String jsonQ = request.getParameter("Question");
                    Question qtemp = (Question) Utils.fromJson(jsonQ, Question.class);
                    User utemp = qtemp.getTeacherEmail();
                    utemp.setKarma(utemp.getKarma() + qtemp.getKarma());
                    qtemp.setKarma(0);
                    boolean res = (qdao.updateQuestion(qtemp) && udao.updateUser(utemp));
                    out.println(Utils.toJson(res));
                    System.out.println(Utils.toJson(res));
                    break;
                }
                case ("teacher"): {
                    String jsonQ = request.getParameter("Question");
                    Question qtemp = (Question) Utils.fromJson(jsonQ, Question.class);
                    boolean res = qdao.updateQuestion(qtemp);
                    out.println(Utils.toJson(res));
                    System.out.println(Utils.toJson(res));
                    break;
                }
            }
=======
            String jsonQ = request.getParameter("Question");
            out.print(qmanager.updateQuestion(jsonQ));
>>>>>>> 3rd_iter
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
