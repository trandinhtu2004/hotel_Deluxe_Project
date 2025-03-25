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
import model.Room;
import model.Service;
import dal.ServiceDAO;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import model.Role;


/**
 *
 * @author DELL
 */
@WebServlet(name = "DetailInformationOut", urlPatterns = {"/DetailInformationOut"})
public class DetailInformationOut extends HttpServlet {

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
            out.println("<title>Servlet DetailInformationOut</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailInformationOut at " + request.getContextPath() + "</h1>");
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
        
       ServiceDAO sd = new  ServiceDAO();
       List<Service> listSV = sd.getServiceUsageByBookingId(Integer.parseInt(bookingId));long total=0;
        for (Service service : listSV) {
            total+=service.getPrice()*service.getQuantity();
        }
        
        System.out.println("Hello");
        RoomDAO rd = new RoomDAO();
        Room room = rd.getRoomByID(roomId);
        
        String roomnumber = room.getRoomNumber();
        Role roles = acc.getRole();
        
        int roleid=roles.getRoleId();
        String role;
        int temp =roleid;
        if(temp==1){
            role="Owner";
        }
        else if(temp==2){
            role="Staff";
        }else{
            role="Customer";
        }
        Date createDate = acc.getCreateDate();
        String address = acc.getAddress();
        String phone = acc.getPhone();
        
        BookingDAO bk = new BookingDAO();
        Booking bking = bk.getBookingById(Integer.parseInt(bookingId));
        
        Date checkin = bking.getCheckInDate();
        Date checkout = bking.getCheckOutDate();
        long diffInMillies = Math.abs(checkout.getTime() - checkin.getTime());
    long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

    int discont = bk.countBookingByAccountID(accoutID);
    if(role.equals("Owner")){
        discont=20;
    }else if(role.equals("Staff")){
        discont=10;
    }
    
    BigDecimal discountrate = BigDecimal.valueOf(100-discont*5).divide(BigDecimal.valueOf(100));     
        
        BigDecimal price;
        price = bking.getPricefernight().multiply(BigDecimal.valueOf(daysBetween));
        BigDecimal totalPrice = price.multiply(discountrate);
        
        request.setAttribute("roomnum", roomnumber);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("createdate", createDate);
        request.setAttribute("role", role);
        request.setAttribute("bookingId", bookingId);
        request.setAttribute("email", email);
        request.setAttribute("roomType", bking.getRoomType());
        request.setAttribute("bookingDate", bking.getBookingDate());
        request.setAttribute("checkInDate", bking.getCheckInDate());
        request.setAttribute("checkOutDate", bking.getCheckOutDate());
        request.setAttribute("note", bking.getNote());
        request.setAttribute("customerName", bking.getCustomerName());
        request.setAttribute("accoutID", accoutID);
        request.setAttribute("status", bking.getStatus());
        request.setAttribute("pernight", bking.getPricefernight().longValue());
        request.setAttribute("nightstay", daysBetween);
        request.setAttribute("dc", discont*5);
        request.setAttribute("price", totalPrice.longValue());
        request.setAttribute("serviceList", listSV);
        request.setAttribute("totalservice", total);
        
        request.getRequestDispatcher("bookingdetailcheckout.jsp").forward(request, response);
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
