/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.servlets;

import com.tambo.model.managers.ClassTeacherManager;
import com.tambo.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ServletCT extends HttpServlet {
ClassTeacherManager ctmanager= new ClassTeacherManager();
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletCT</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCT at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
                out.print(ctmanager.getCT());
            }  else{
                String classt = request.getParameter("id");
                out.print(ctmanager.getTeachers(classt));
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

            //String token = request.getParameter("authorization");
            //Claims claims  = TokenUtil.decodeJWT(token);//throws exception if token is tampered, or secret key doesn't match
            //claims.getSubject();
            String classteacher=request.getParameter("ct");
            out.print(ctmanager.persistCT(classteacher));

        } catch (Exception ex) {
            Logger.getLogger(ServletMeeting.class.getName()).log(Level.SEVERE, null, ex);
        }
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
