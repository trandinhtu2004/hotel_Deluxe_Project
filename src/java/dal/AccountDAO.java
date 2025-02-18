/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    private static final String DB_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=HotelManagement;encrypt=true;trustServerCertificate=true;";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123";
   
    public void addAccount() {

    }

    public int getTotalStaffs() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account\n"
                + "                     join [Role] r on r.RoleId = Account.RoleId\n"
                + "                where r.RoleId = 2";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //get Account
                Account p = new Account();
               p.setAccountId(rs.getInt("AccountId"));

                //getID role
                Role r = new Role();
                r.setRoleId(rs.getInt("RoleId"));
                r.setRoleName(rs.getString("RoleName"));
                p.setRole(r);

                p.setFullName(rs.getString("Fullname"));
                p.setEmail(rs.getString("Email"));
                p.setPassword(rs.getString("Password"));
                p.setPhone(rs.getString("Phone"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.size();
    }

    public int getTotalCustumers() {
        List<Account> list = new ArrayList<>();
       String sql = "select * from Account\n"
                + "                     join [Role] r on r.RoleId = Account.RoleId\n"
               + "                where r.RoleId = 3";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                //get Account
                Account p = new Account();
               p.setAccountId(rs.getInt("AccountId"));

                //getID role
                Role r = new Role();
                r.setRoleId(rs.getInt("RoleId"));
                r.setRoleName(rs.getString("RoleName"));
                p.setRole(r);

                p.setFullName(rs.getString("Fullname"));
                p.setEmail(rs.getString("Email"));
                p.setPassword(rs.getString("Password"));

                p.setPhone(rs.getString("Phone"));
                list.add(p);
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.size();
    }

    public static void main(String[] args) {
        AccountDAO a = new AccountDAO();
        //test if everything go 
        
        
       
        Account a1= new Account();
        a1.setAccountId(2);
        a1.setEmail("hieu@gmail.com");
        a1.setFullName("Nguyen Minh Hieu");
        a1.setPassword("123");
        a1.setPhone("0921970999");
        a1.setRoleid(1);
        a.insert(a1);
        
        
    }

    public int getTotalAccount() {
        String sql = "select * from Account\n"
                + "join [Role] r on r.RoleId = Account.RoleId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

//    public List<Account> getAllAccount() {
//        List<Account> list = new ArrayList<>();
//        String sql = "select * from Account\n"
//                + "join [Role] r on r.RoleId = Account.RoleId";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                //get Account
//                Account p = new Account();
//                p.setAccountId(rs.getInt("AccountId"));
//
//                //getID role
//                Role r = new Role();
//                r.setRoleId(rs.getInt("RoleId"));
//                r.setRoleName(rs.getString("RoleName"));
//                p.setRole(r);
//
//                p.setFullName(rs.getString("Fullname"));
//                p.setEmail(rs.getString("Email"));
//                p.setPassword(rs.getString("Password"));
//
//                p.setPhone(rs.getString("Phone"));
//                list.add(p);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return list;
//    }

//    public User getUserByEmail(String email) {
//        String sql = "select * from users where email like ?";
//        User user = null;
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, "%" + email + "%");
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                user = new User();
//                user.setId(rs.getInt("id"));
//                user.setName(rs.getString("fullname"));
//                user.setEmail(rs.getString("email"));
//                user.setPassword(rs.getString("password"));
//                user.setRoleid(rs.getInt("roleid"));
//                user.setPhone(rs.getString("phone"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return user;
//    }
    public void changePassword(int accountId, String newPassword) {
        //Em nghĩ ở đây xài accountID để xác định tài khoản cần đổi mật khẩu sẽ tốt hơn
        //Kiểu xài email hay phone lấy ra từ account nghe nó đần ý
        String sql = "UPDATE Account SET Password = ? WHERE AccountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword);
            st.setInt(2, accountId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    public String getPasswordByEmail(String email) {
//        //Em xài email trả lại nó cái password luôn chứ có biết xài API gửi email xác nhận đâu :((
//        //Trả lại password luôn khỏi cần đặt lại password
//        //muốn đặt lại thì có changePassword ở trên rồi
//    String password = null;
//    String sql = "SELECT Password FROM Account WHERE Email = ?";
//    try {
//        PreparedStatement st = connection.prepareStatement(sql);
//        st.setString(1, email);
//        ResultSet rs = st.executeQuery();
//        if (rs.next()) {
//            password = rs.getString("Password");
//        }
//    } catch (SQLException e) {
//        System.out.println(e.getMessage());
//    }
//    return password;
//}
    // Hàm lưu OTP vào database
    public void storeOTP(String email, String otp) {
        String sql = "UPDATE Account SET OTP = ? WHERE Email = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, otp);
            st.setString(2, email);
            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Không tìm thấy email để cập nhật OTP.");
            } else {
                System.out.println("OTP đã được cập nhật thành công.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra OTP
    public boolean verifyOTP(String email, String otp) {
        String sql = "SELECT OTP FROM Account WHERE Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String storedOTP = rs.getString("OTP");
                return storedOTP != null && storedOTP.equals(otp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm đặt lại mật khẩu
    public boolean resetPassword(String email, String newPassword) {
        String sql = "UPDATE Account SET Password = ? WHERE Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword);
            st.setString(2, email);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account login(String email, String password) {
        Account account = null;
        String sql = "SELECT * FROM Account WHERE Email = ? AND Password = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getInt("AccountId"));
                account.setEmail(rs.getString("Email"));
                account.setPassword(rs.getString("Password"));
                account.setFullName(rs.getString("FullName"));
                account.setPhone(rs.getString("Phone"));
                account.setRoleid(rs.getInt("RoleId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT Email FROM Account WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insert(Account account) {
        String sql = "INSERT INTO Account (FullName, Email, Password, Phone, RoleId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, account.getFullName());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getPhone());
            ps.setInt(5,1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByEmail(String email) {
        Account account = null;
        String sql = "SELECT AccountId, RoleId, FullName, Email, Password, Phone FROM Account WHERE Email = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getInt("AccountId"));
                account.setFullName(rs.getString("FullName"));
                account.setEmail(rs.getString("Email"));
                account.setPassword(rs.getString("Password"));
                account.setPhone(rs.getString("Phone"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return account;
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Account WHERE Email = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPhoneExists(String phone, String currentEmail) {
        String sql = "SELECT COUNT(*) FROM Account WHERE Phone = ? AND Email <> ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, phone);
            st.setString(2, currentEmail);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAccount(String email, String fullName, String phone) {
        String sql = "UPDATE Account SET FullName = ?, Phone = ? WHERE Email = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, fullName);
            st.setString(2, phone);
            st.setString(3, email);

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public void testLogin(String email, String password) {
        Account account = login(email, password);

        if (account != null) {
            
            System.out.println("Login successful. Account found.");
            System.out.println("Account email: " + account.getEmail());
            System.out.println("Account full name: " + account.getFullName());
        } else {
            System.out.println("Login failed. Account not found.");
        }
    }

    public boolean verifyCode(String email, String code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void markEmailAsVerified(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
