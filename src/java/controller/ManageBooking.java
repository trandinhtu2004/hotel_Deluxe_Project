/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Overlordil
 */

public class ManageBooking extends HttpServlet {

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
            out.println("<title>Servlet ManageBooking</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageBooking at " + request.getContextPath() + "</h1>");
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
        BookingDAO booking = new BookingDAO();

        request.setAttribute("bookingList", booking.getBookingList());
        request.getRequestDispatcher("manageBooking.jsp").forward(request, response);
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
            BookingDAO booking = new BookingDAO();
            

            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            String bookingStatus = "";
                    
            String submit = request.getParameter("submit");
            switch (submit) {
                case "accept":
                    bookingStatus = "On Going";
                    booking.acceptBooking(bookingId, bookingStatus);
                    request.setAttribute("bookingList", booking.getBookingList());
                    request.getRequestDispatcher("manageBooking.jsp").forward(request, response);
                    break;
                case "cancel":
                    bookingStatus = "Cancel";
                    booking.acceptBooking(bookingId, bookingStatus);
                    request.setAttribute("bookingList", booking.getBookingList());
                    request.getRequestDispatcher("manageBooking.jsp").forward(request, response);
                    break;
                case "late":
                    bookingStatus = "Late";
                    booking.acceptBooking(bookingId, bookingStatus);
                    request.setAttribute("bookingList", booking.getBookingList());
                    request.getRequestDispatcher("manageBooking.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
