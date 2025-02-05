package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import dal.AccountDAO;
import java.io.IOException;
import model.Role;

@WebServlet("/login")
public class LoginController extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Giả lập tài khoản cứng (Có thể thay bằng truy vấn database)
        Account admin = new Account(1, new Role(1, "Admin"), "Admin User", "admin@gmail.com", "123456", "0123456789");

        if (email.equals(admin.getEmail()) && password.equals(admin.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);
            response.sendRedirect("index.jsp"); // Chuyển hướng về trang chủ
        } else {
            request.setAttribute("error", "Invalid email or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
