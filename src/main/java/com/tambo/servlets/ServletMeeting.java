/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tambo.model.DAO.IMeetingDAO;
import com.tambo.model.DAO.MeetingDAO;
import com.tambo.model.VO.Meeting;
<<<<<<< HEAD
import com.tambo.model.managers.MeetingManager;
=======
import com.tambo.utils.TokenUtil;
>>>>>>> origin/JWTImp
import com.tambo.utils.Utils;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class ServletMeeting extends HttpServlet {
 MeetingManager mmanager= new MeetingManager();

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
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response); 
 
            try {
                PrintWriter out = response.getWriter();
                String token = request.getParameter("authorization");
            Claims claims  = TokenUtil.decodeJWT(token);//throws exception if token is tampered, or secret key doesn't match
        String opt=request.getParameter("option");
        switch(opt){
            case "all":
                out.print(mmanager.getMeets());
                break;
            default:
                System.out.println("no se ha ingresado un parametro aceptable");
                out.print("[]");
                break;
        }

    } catch (Exception ex) {
        Logger.getLogger(ServletQuestion.class.getName()).log(Level.SEVERE, null, ex);
    }





//String opt=request.getParameter("option");

      
// processRequest(request, response);
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
<<<<<<< HEAD
            String meet=request.getParameter("meet");
            out.print(mmanager.persist(meet));
=======
            String token = request.getParameter("authorization");
            Claims claims  = TokenUtil.decodeJWT(token);//throws exception if token is tampered, or secret key doesn't match
            claims.getSubject();
            
            Meeting meet=(Meeting)Utils.fromJson(request.getParameter("meet"), Meeting.class);
            id=md.makeMeet(meet);
            System.out.println(Utils.toJson(id));
            out.print(Utils.toJson(id));
>>>>>>> origin/JWTImp
        } catch (Exception ex) {
            Logger.getLogger(ServletMeeting.class.getName()).log(Level.SEVERE, null, ex);
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
//la modificacion de registros ira aqui      
//  processRequest(request, response);
      
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
