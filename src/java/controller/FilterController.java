/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author Admin
 */
public class FilterController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String categoryName = request.getParameter("roomType");
        String capacity = request.getParameter("capacity");
        String checkinDate = request.getParameter("checkin");
        String checkoutDate = request.getParameter("checkout");
        String[] priceRanges = request.getParameterValues("priceRange");
        
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Category> filteredCategories = new ArrayList<>();
        
        // Handle price ranges if selected
        if (priceRanges != null && priceRanges.length > 0) {
            // Process each selected price range
            for (String range : priceRanges) {
                // Split the range into min and max prices
                String[] prices = range.split("-");
                double minPrice = Double.parseDouble(prices[0]);
                double maxPrice = Double.parseDouble(prices[1]);
                
                // Use the filterRoomCategory method with appropriate parameters
                ArrayList<Category> categoriesInPriceRange = roomDAO.filterRoomCategory(
                    minPrice, 
                    maxPrice, 
                    categoryName, 
                    checkoutDate, 
                    checkinDate
                );
                
                // Add unique categories to the filtered list
                for (Category category : categoriesInPriceRange) {
                    if (!containsCategory(filteredCategories, category)) {
                        filteredCategories.add(category);
                    }
                }
            }
        } else {
            filteredCategories = roomDAO.filterRoomCategory(
                null,  // min price (will use default 0)
                null,  // max price (will use max from database)
                categoryName,
                checkoutDate,
                checkinDate
            );
        }
        
        // Get all capacities 
        ArrayList<Category> capacities = roomDAO.getAllCapacities();
        
        // Get all categories for the room type dropdown
        ArrayList<Category> allCategories = roomDAO.ListCategory();
        
        // Set attributes for the JSP
        request.setAttribute("list", filteredCategories);
        request.setAttribute("capacities", capacities);
        request.setAttribute("allCategories", allCategories);
        
        // Add selected filter parameters to maintain state
        request.setAttribute("selectedCategory", categoryName);
        request.setAttribute("selectedCapacity", capacity);
        request.setAttribute("selectedCheckin", checkinDate);
        request.setAttribute("selectedCheckout", checkoutDate);
        request.setAttribute("selectedPriceRanges", priceRanges);
        
        // Forward to rooms.jsp
        request.getRequestDispatcher("rooms.jsp").forward(request, response);
    }
    
    private boolean containsCategory(ArrayList<Category> categories, Category category) {
        for (Category c : categories) {
            if (c.getCategoryId() == category.getCategoryId()) {
                return true;
            }
        }
        return false;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
