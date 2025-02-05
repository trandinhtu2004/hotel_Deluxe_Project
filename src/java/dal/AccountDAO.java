/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
    
    
     public boolean emailExists(String email) {
        String sql = "SELECT email FROM Account WHERE email = ?";
         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insert(Account account) {
        String sql = "INSERT INTO Account (fullName, email, password, phone, roleId, isVerified) VALUES (?, ?, ?, ?, ?, false)";
         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account.getFullName());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getPhone());
            ps.setInt(5, account.getRole().getRoleId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveVerificationCode(String email, String code) {
        String sql = "INSERT INTO VerificationCode (email, code, expiration) VALUES (?, ?, DATE_ADD(NOW(), INTERVAL 5 MINUTE)) ON DUPLICATE KEY UPDATE code = ?, expiration = DATE_ADD(NOW(), INTERVAL 5 MINUTE)";
         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, code);
            ps.setString(3, code);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean verifyCode(String email, String code) {
    String sql = "SELECT * FROM VerificationCode WHERE email = ? AND code = ? AND expiration > NOW()";
     try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ps.setString(2, code);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public void markEmailAsVerified(String email) {
    String sql = "UPDATE Account SET isVerified = true WHERE email = ?";
     try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public int getTotalStaffs() {
       List<Account> list = new ArrayList<>();
        String sql = "select * from Account\n" +
"                     join [Role] r on r.RoleId = Account.RoleId\n" +
"                where r.RoleId = 2";
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
        String sql = "select * from Account\n" +
"                     join [Role] r on r.RoleId = Account.RoleId\n" +
"                where r.RoleId = 3";
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
        System.out.print(a.getTotalCustumers() + ", " + a.getTotalStaffs());
    }
    
    
    public int getTotalAccount() {
        String sql = "select * from Account\n" +
                     "join [Role] r on r.RoleId = Account.RoleId";
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

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account\n" +
                     "join [Role] r on r.RoleId = Account.RoleId";
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
        return list;
    }


     public Account login(String email, String password) {
        String query = "SELECT a.*, r.roleId, r.roleName FROM Account a " +
                       "JOIN Role r ON a.roleId = r.roleId WHERE a.email = ? AND a.password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Role role = new Role(rs.getInt("roleId"), rs.getString("roleName"));
                return new Account(rs.getInt("accountId"), role, rs.getString("fullName"),
                                   rs.getString("email"), rs.getString("password"), rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
