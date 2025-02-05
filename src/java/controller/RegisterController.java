/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import email.EmailUtil;
import hashPassword.hashUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import model.Account;

/**
 *
 * @author AD
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String fullName = request.getParameter("fullName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String phone = request.getParameter("phone");

    // Debug log để kiểm tra dữ liệu nhận được
    System.out.println("Full Name: " + fullName);
    System.out.println("Email: " + email);
    System.out.println("Phone: " + phone);
    System.out.println("Password: " + password); // Kiểm tra mật khẩu có bị null không

    if (password == null || password.isEmpty()) {
        request.setAttribute("message", "Mật khẩu không được để trống!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    AccountDAO accountDAO = new AccountDAO();
    if (accountDAO.emailExists(email)) {
        request.setAttribute("message", "Email đã tồn tại!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    // Mã hóa mật khẩu
    String hashedPassword = hashUtil.hashPassword(password);

    // Lưu thông tin tài khoản vào database
    Account account = new Account(fullName, email, hashedPassword, phone);
    accountDAO.insert(account);

    // Hiển thị thông báo đăng ký thành công
    request.setAttribute("message", "Đăng ký thành công! Vui lòng kiểm tra email để xác nhận tài khoản.");
    request.getRequestDispatcher("login.jsp").forward(request, response);
}


    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}
