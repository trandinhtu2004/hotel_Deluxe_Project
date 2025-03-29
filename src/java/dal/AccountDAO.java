/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    public int getTotalStaffs() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account\n"
                + "join [Role] r on r.RoleId = Account.RoleId\n"
                + "where r.RoleId = 2";
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
                + "join [Role] r on r.RoleId = Account.RoleId\n"
                + "where r.RoleId = 3";
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

    public Account getAccountNameById(int accid) {
        Account acc = null;
        String sql = "SELECT * FROM Account WHERE AccountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setFullName(rs.getString("FullName"));
            }
        } catch (Exception e) {

        }
        return acc;
    }

    public Account getAccountInfoById(String accountId) {
        String sql = "SELECT [RoleId], [FullName], [Email], [Phone], [Address], [CreatedDate] "
                + "FROM [HotelManagement].[dbo].[Account] "
                + "WHERE [AccountId] = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int roleId = rs.getInt("RoleId");
                    Role role = new Role(roleId);
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    String address = rs.getString("Address");
                    Date createDate = rs.getDate("CreatedDate");
                    return new Account(role, fullName, email, phone, address, createDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        AccountDAO a = new AccountDAO();
        List<Account> acc = a.getAllAccount();
        for (Account account : acc) {
            System.out.println(account.getEmail() + " " + account.getPassword());
        }
//        Account ac = a.getAccountNameById(1);
//        System.out.println(ac.getFullName());
        Account ac = a.getAccountInfoById("3");
        System.out.println(ac.getAccountId() + " " + ac.getEmail());
    }

    public int[] getTotalAllAccountWithStatus() {
        String sql = "SELECT  COUNT(AccountId) AS TotalAccount,\n"
                + "	      COUNT(CASE WHEN [Status] = 'Active' THEN 1 END) AS ActiveAccount,\n"
                + "	      COUNT(CASE WHEN [Status] = 'Inactive' THEN 1 END) AS InactiveAccount\n"
                + " FROM [dbo].[Account]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int totalAccount = rs.getInt("TotalAccount");
                int activeAccount = rs.getInt("ActiveAccount");
                int inactiveAccount = rs.getInt("InactiveAccount");
                return new int[]{totalAccount, activeAccount, inactiveAccount};
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new int[]{0, 0, 0};
    }

    public int[] getTotalAccountWithStatus(String role) {
        String sql = "SELECT COUNT(a.AccountId) AS TotalAccount,\n"
                + "	     COUNT(CASE WHEN a.Status = 'Active' THEN 1 END) AS ActiveAccount,\n"
                + "          COUNT(CASE WHEN a.Status = 'Inactive' THEN 1 END) AS InactiveAccount\n"
                + "   FROM [dbo].[Account] a JOIN [Role] r ON r.RoleId = a.RoleId\n"
                + "   WHERE r.RoleName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, role);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int totalAccount = rs.getInt("TotalAccount");
                int activeAccount = rs.getInt("ActiveAccount");
                int inactiveAccount = rs.getInt("InactiveAccount");
                return new int[]{totalAccount, activeAccount, inactiveAccount};
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new int[]{0, 0, 0};
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT [AccountId],a.[RoleId],r.[RoleName],[FullName],[Email],[Phone],[Address],[CreatedDate],[Status],[Image]\n"
                + "FROM [dbo].[Account] a JOIN [dbo].[Role] r ON a.[RoleId] = r.[RoleId]"
                + "ORDER BY [CreatedDate] DESC";
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
                p.setPhone(rs.getString("Phone"));
                p.setAddress(rs.getString("Address"));
                p.setCreatedDate(rs.getDate("CreatedDate"));
                p.setStatus(rs.getString("Status"));
                p.setImage(rs.getString("Image"));

                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

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
    public void changePassword(String email, String newPassword) {
        //Em nghĩ ở đây xài accountID để xác định tài khoản cần đổi mật khẩu sẽ tốt hơn
        //Kiểu xài email hay phone lấy ra từ account nghe nó đần ý
        String sql = "UPDATE Account SET Password = ? WHERE Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPassword);
            st.setString(2, email);
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
        String sql = "SELECT a.AccountId,a.[RoleId],r.[RoleName],[FullName],[Email],[Password],[Phone],[Status]\n"
                + "FROM [dbo].[Account] a JOIN [dbo].[Role] r ON a.[RoleId] = r.[RoleId]\n"
                + "WHERE Email = ? AND Password = ?";

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
                account.setStatus(rs.getString("Status"));

                Role role = new Role();
                role.setRoleId(rs.getInt("RoleId"));
                role.setRoleName(rs.getString("RoleName"));
                account.setRole(role);
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
            ps.setInt(5, 3);
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

    public List<String> getCreatedDate() {
        List<String> listCreatedDate = new ArrayList<>();
        String sql = "SELECT DISTINCT [CreatedDate]\n"
                + "FROM [dbo].[Account]\n"
                + "ORDER BY [CreatedDate]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listCreatedDate.add(rs.getString("CreatedDate"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCreatedDate;
    }

    public void createAccount(int roleid, String fullName, String email, String password, String phone, String address, Date createdDate, String status) {
        String sql = "INSERT INTO [dbo].[Account]([RoleId],[FullName],[Email],[Password],[Phone],[Address],[CreatedDate],[Status])\n"
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, roleid);
            ps.setString(2, fullName);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setDate(7, new java.sql.Date(createdDate.getTime()));
            ps.setString(8, status);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String fullName, String phone, String address, String status, int accountId) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "SET [FullName] = ?,[Phone] = ?,[Address] = ?,[Status] = ?\n"
                + "WHERE [AccountId] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, status);
            ps.setInt(5, accountId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStatusUser(int accountId, String status) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "SET [Status] = ?\n"
                + "WHERE [AccountId] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, accountId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
