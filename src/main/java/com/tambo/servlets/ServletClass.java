/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.servlets;

import com.tambo.model.managers.ClassManager;
import com.tambo.model.managers.QuestionManager;
import com.tambo.utils.TokenUtil;
import io.jsonwebtoken.Claims;
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
public class ServletClass extends HttpServlet {
    
ClassManager cmanager = new ClassManager();
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
            //String token = request.getParameter("authorization");
            //System.out.println(token+" esto llega de Cliente");
            //Claims claims  = TokenUtil.decodeJWT(token);//throws exception if token is tampered, or secret key doesn't match
            //System.out.println(claims.getSubject());
            String opt = request.getParameter("option");
            if (opt.equals("all")) {
                out.print(cmanager.getClasss());
            } else if(opt.equals("byTopic")){
                String usertemp = request.getParameter("user");
                String topic = request.getParameter("topic");
                out.print(cmanager.getClass(opt,usertemp,topic));
            } else{
                String usertemp = request.getParameter("user");
                out.print(cmanager.getClasss(opt, usertemp));
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
        try {
            PrintWriter out = response.getWriter();
            String jsonQ = request.getParameter("Class");
            out.print(cmanager.persistClass(jsonQ));

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
        PrintWriter out = response.getWriter();
        try {

            String token = request.getParameter("authorization");
            Claims claims  = TokenUtil.decodeJWT(token);//throws exception if token is tampered, or secret key doesn't match
            String jsonQ = request.getParameter("Question");
            out.print(cmanager.updateClass(jsonQ));

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
