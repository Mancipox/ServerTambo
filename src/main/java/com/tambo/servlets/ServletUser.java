/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.servlets;

import com.tambo.model.DAO.IUserDAO;
import com.tambo.model.DAO.UserDAO;
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
public class ServletUser extends HttpServlet {

    IUserDAO udao = new UserDAO();

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
        String opt = request.getParameter("option");
        List<User> users;
        User user;
        String jsonObj = "";
        try {
            PrintWriter out = response.getWriter();
            switch (opt) {
                case "all":
                    users = udao.getAllUsers();
                    jsonObj = Utils.toJson(users);
                    System.out.println(jsonObj);
                    out.println(jsonObj);
                    break;
                case "byEmail":
                    String email = request.getParameter("email");
                    user = udao.searchUser(email);
                    jsonObj = Utils.toJson(user);
                    System.out.println(jsonObj);
                    out.println(jsonObj);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        String opt = request.getParameter("option");
        User user;
        try {
            PrintWriter out = response.getWriter();
            String jsonU = request.getParameter("user");
            user = (User) Utils.fromJson(jsonU, User.class);
            boolean res;
            switch (opt) {
                case "create":
                    res = udao.addUser(user);
                    System.out.println(Utils.toJson(res));
                    out.print(Utils.toJson(res));
                    break;
                case "login":
                    User userresp = udao.isUser(user);
                    out.print(Utils.toJson(userresp));
                    System.out.println(Utils.toJson(userresp));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        User user;
        try {
            PrintWriter out = response.getWriter();
            String jsonU = request.getParameter("user");
            user = (User) Utils.fromJson(jsonU, User.class);
            boolean res = udao.updateUser(user);
            System.out.println(Utils.toJson(res));
            out.print(Utils.toJson(out));
        } catch (Exception e) {
            e.printStackTrace();
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
