/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Booking;
import java.util.*;
import dal.BookingDAO;
import model.Account;
import dal.AccountDAO;
import dal.RoomDAO;
import java.time.LocalDateTime;
import model.Room;


/**
 *
 * @author DELL
 */
@WebServlet(name = "GetDetailInfomation", urlPatterns = {"/GetDetailInfomation"})
public class GetDetailInfomation extends HttpServlet {

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
            out.println("<title>Servlet GetDetailInfomation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetDetailInfomation at " + request.getContextPath() + "</h1>");
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
        String bookingId = request.getParameter("bookingId");
        String roomId = request.getParameter("roomId");
        String roomType = request.getParameter("roomType");
        String bookingDate = request.getParameter("bookingDate");
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String note = request.getParameter("note");
        String customerName = request.getParameter("customerName");
        String accoutID = request.getParameter("accoutID");
        String status = request.getParameter("status");
        AccountDAO ac = new AccountDAO();
        Account acc = ac.getAccountInfoById(accoutID);
        String email = acc.getEmail();
        
        RoomDAO rd = new RoomDAO();
        Room room = rd.getRoomByID(roomId);
        
        String roomnumber = room.getRoomNumber();
        
        String role;
        int temp =Integer.parseInt(roomId);
        if(temp==1){
            role="Owner";
        }
        else if(temp==2){
            role="Staff";
        }else{
            role="Customer";
        }
        Date createDate = acc.getCreatedDate();
        String address = acc.getAddress();
        String phone = acc.getPhone();
        
        
        request.setAttribute("roomnum", roomnumber);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("createdate", createDate);
        request.setAttribute("role", role);
        request.setAttribute("bookingId", bookingId);
        request.setAttribute("email", email);
        request.setAttribute("roomType", roomType);
        request.setAttribute("bookingDate", bookingDate);
        request.setAttribute("checkInDate", checkInDate);
        request.setAttribute("checkOutDate", checkOutDate);
        request.setAttribute("note", note);
        request.setAttribute("customerName", customerName);
        request.setAttribute("accoutID", accoutID);
        request.setAttribute("status", status);
        
        
        request.getRequestDispatcher("bookingdetailnew.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
