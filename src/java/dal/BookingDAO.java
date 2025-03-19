/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author DELL
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;
import model.Booking;
import java.math.*;
import java.util.concurrent.TimeUnit;

public class BookingDAO extends DBContext {

    
    
    public Booking getBookingById(int bookingId) {
    String sql = "SELECT \n"
            + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
            + "    b.BookingId,\n"
            + "    b.RoomId,\n"
            + "    c.CategoryName AS RoomType,\n"
            + "    b.BookingDate,\n"
            + "    b.CheckInDate,\n"
            + "    b.CheckOutDate,\n"
            + "    b.Note,\n"
            + "    a.FullName AS Name,\n"
            + "    a.AccountId,\n"
            + "    b.BookingStatus AS Status,\n"
            + "    c.PricePerNight  -- Thay TotalPrice bằng PricePerNight từ Category\n"
            + "FROM \n"
            + "    Booking b\n"
            + "JOIN \n"
            + "    Room r ON b.RoomId = r.RoomId\n"
            + "JOIN \n"
            + "    Category c ON r.CategoryId = c.CategoryId\n"
            + "JOIN \n"
            + "    Account a ON b.AccountId = a.AccountId\n"
            + "WHERE \n"
            + "    b.BookingId = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, bookingId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Booking(
                        rs.getInt("No"),
                        rs.getInt("BookingId"),
                        rs.getInt("RoomId"),
                        rs.getString("RoomType"),
                        rs.getDate("BookingDate"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getString("Note"),
                        rs.getString("Name"),
                        rs.getInt("AccountId"),
                        rs.getString("Status"),
                        rs.getBigDecimal("PricePerNight") // Lấy giá từ Category
                );
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return null;
}
    
    
    
    
    public List<Booking> getTodayNotYetBooking() {
    List<Booking> list = new ArrayList<>();
    String sql = "SELECT \n"
            + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
            + "    b.BookingId,\n"
            + "    b.RoomId,\n"
            + "    c.CategoryName AS RoomType,\n"
            + "    b.BookingDate,\n"
            + "    b.CheckInDate,\n"
            + "    b.CheckOutDate,\n"
            + "    b.Note,\n"
            + "    a.FullName AS Name,\n"
            + "    a.AccountId,\n"
            + "    b.BookingStatus AS Status\n"
            + "FROM \n"
            + "    Booking b\n"
            + "JOIN \n"
            + "    Room r ON b.RoomId = r.RoomId\n"
            + "JOIN \n"
            + "    Category c ON r.CategoryId = c.CategoryId\n"
            + "JOIN \n"
            + "    Account a ON b.AccountId = a.AccountId\n"
            + "WHERE \n"
            + "    b.BookingStatus = 'Not yet' \n"
            + "    AND CONVERT(DATE, b.CheckInDate) = CONVERT(DATE, GETDATE()) \n"
            + "ORDER BY \n"
            + "    b.BookingId;";

    try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Booking bookingInfo = new Booking(
                    rs.getInt("No"),
                    rs.getInt("BookingId"),
                    rs.getInt("RoomId"),
                    rs.getString("RoomType"),
                    rs.getDate("BookingDate"),
                    rs.getDate("CheckInDate"),
                    rs.getDate("CheckOutDate"),
                    rs.getString("Note"),
                    rs.getString("Name"),
                    rs.getInt("AccountId"),
                    rs.getString("Status")
            );
            list.add(bookingInfo);
        }
    } catch (Exception e) {
        System.out.println(e);
    }

    return list;
}
    
    
    
    
    
   public static void main(String[] args) {
    BookingDAO bid = new BookingDAO();
    Booking list = bid.getBookingById(15);

    System.out.println(list.getPricefernight() + " " + list.getCheckOutDate() + " " + list.getCheckInDate());

    // Sửa lại công thức tính số ngày
    long diffInMillies = Math.abs(list.getCheckOutDate().getTime() - list.getCheckInDate().getTime());
    long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

    System.out.println("Số ngày lưu trú: " + daysBetween);
}
    
   public int countBookingByAccountID(String id){
       String sql = "SELECT COUNT(*) FROM BOOKING WHERE AccountId = ?";
       try(PreparedStatement ps = connection.prepareStatement(sql)){
           ps.setString(1, id);
           try(ResultSet rs = ps.executeQuery()){
               if(rs.next()){
                   int a= rs.getInt(1);
                   if(a>3){
                       return 3;
                   }else{
                       return a;
                   }
               }
           }
       }catch(Exception e){
           System.out.println(e);
       }
       return 0;
   }
    
    
    public List<Booking> getAllNotYetBookingBeforeToday() {
    List<Booking> list = new ArrayList<>();
    String sql = "SELECT \n"
            + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
            + "    b.BookingId,\n"
            + "    b.RoomId,\n"
            + "    c.CategoryName AS RoomType,\n"
            + "    b.BookingDate,\n"
            + "    b.CheckInDate,\n"
            + "    b.CheckOutDate,\n"
            + "    b.Note,\n"
            + "    a.FullName AS Name,\n"
            + "    a.AccountId,\n"
            + "    b.BookingStatus AS Status\n"
            + "FROM \n"
            + "    Booking b\n"
            + "JOIN \n"
            + "    Room r ON b.RoomId = r.RoomId\n"
            + "JOIN \n"
            + "    Category c ON r.CategoryId = c.CategoryId\n"
            + "JOIN \n"
            + "    Account a ON b.AccountId = a.AccountId\n"
            + "WHERE \n"
            + "    b.BookingStatus = 'Not yet' \n"
            + "    AND b.CheckInDate <= GETDATE() \n"
            + "ORDER BY \n"
            + "    b.CheckInDate ASC;";

    try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Booking bookingInfo = new Booking(
                    rs.getInt("No"),
                    rs.getInt("BookingId"),
                    rs.getInt("RoomId"),
                    rs.getString("RoomType"),
                    rs.getDate("BookingDate"),
                    rs.getDate("CheckInDate"),
                    rs.getDate("CheckOutDate"),
                    rs.getString("Note"),
                    rs.getString("Name"),
                    rs.getInt("AccountId"),
                    rs.getString("Status")
            );
            list.add(bookingInfo);
        }
    } catch (Exception e) {
        System.out.println(e);
    }

    return list;
}

    public List<Booking> getBookingList() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM Booking";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("BookingId"),
                        rs.getInt("AccountId"),
                        rs.getInt("RoomId"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getBigDecimal("TotalPrice"),
                        rs.getString("BookingStatus"),
                        rs.getDate("BookingDate"),
                        rs.getString("Note"),
                        rs.getString("Status")
                );
                list.add(booking);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Booking> getCheckInList() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE BookingStatus = 'Not Yet'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("BookingId"),
                        rs.getInt("AccountId"),
                        rs.getInt("RoomId"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getBigDecimal("TotalPrice"),
                        rs.getString("BookingStatus"),
                        rs.getDate("BookingDate"),
                        rs.getString("Note"),
                        rs.getString("Status")
                );
                list.add(booking);
            }
        } catch (Exception e) {
            System.out.println("Error getCheckInList: " + e.getMessage());
        }
        return list;
    }

    public List<Booking> getCheckOutList() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE BookingStatus = 'On Going'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("BookingId"),
                        rs.getInt("AccountId"),
                        rs.getInt("RoomId"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getBigDecimal("TotalPrice"),
                        rs.getString("BookingStatus"),
                        rs.getDate("BookingDate"),
                        rs.getString("Note"),
                        rs.getString("Status")
                );
                list.add(booking);
            }
        } catch (Exception e) {
            System.out.println("Error getCheckoutList: " + e.getMessage());
        }
        return list;
    }

//    public List<Booking> getTodayCheckIn() {
//        List<Booking> list = new ArrayList<>();
//        String sql = "SELECT * FROM Booking WHERE CAST(CheckInDate AS DATE) = CAST(GETDATE() AS DATE)";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Booking booking = new Booking(
//                        rs.getInt("BookingId"),
//                        rs.getInt("AccountId"),
//                        rs.getInt("RoomId"),
//                        rs.getDate("CheckInDate"),
//                        rs.getDate("CheckOutDate"),
//                        rs.getBigDecimal("TotalPrice"),
//                        rs.getString("BookingStatus"),
//                        rs.getDate("BookingDate"),
//                        rs.getString("Note"),
//                        rs.getString("Status")
//                );
//                list.add(booking);
//            }
//        } catch (Exception e) {
//            System.out.println("Error getTodayCheckIn: " + e.getMessage());
//        }
//        return list;
//    }

    public List<Booking> getAllBookingInformation() {
    List<Booking> list = new ArrayList<>();
    String sql = "SELECT \n"
            + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
            + "    b.BookingId,\n"
            + "    b.RoomId,\n"
            + "    c.CategoryName AS RoomType,  -- Lấy RoomType từ CategoryName\n"
            + "    b.BookingDate,\n"
            + "    b.CheckInDate,\n"
            + "    b.CheckOutDate,\n"
            + "    b.Note,\n"
            + "    a.FullName AS Name,\n"
            + "    a.AccountId,\n"
            + "    b.BookingStatus AS Status\n"
            + "FROM \n"
            + "    Booking b\n"
            + "JOIN \n"
            + "    Room r ON b.RoomId = r.RoomId\n"
            + "JOIN \n"
            + "    Category c ON r.CategoryId = c.CategoryId  -- Tham gia bảng Category để lấy CategoryName\n"
            + "JOIN \n"
            + "    Account a ON b.AccountId = a.AccountId\n"
            + "ORDER BY \n"
            + "    b.BookingId;";

    try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Booking bookingInfo = new Booking(
                    rs.getInt("No"),
                    rs.getInt("BookingId"),
                    rs.getInt("RoomId"),
                    rs.getString("RoomType"),  // Lấy RoomType từ CategoryName
                    rs.getDate("BookingDate"),
                    rs.getDate("CheckInDate"),
                    rs.getDate("CheckOutDate"),
                    rs.getString("Note"),
                    rs.getString("Name"),
                    rs.getInt("AccountId"),
                    rs.getString("Status")
            );
            list.add(bookingInfo);
        }
    } catch (Exception e) {
        System.out.println(e);
    }

    return list;
}

    public List<Booking> getAllNotYetBooking() {
    List<Booking> list = new ArrayList<>();
    String sql = "SELECT \n"
            + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
            + "    b.BookingId,\n"
            + "    b.RoomId,\n"
            + "    c.CategoryName AS RoomType,\n"
            + "    b.BookingDate,\n"
            + "    b.CheckInDate,\n"
            + "    b.CheckOutDate,\n"
            + "    b.Note,\n"
            + "    a.FullName AS Name,\n"
            + "    a.AccountId,\n"
            + "    b.BookingStatus AS Status\n"
            + "FROM \n"
            + "    Booking b\n"
            + "JOIN \n"
            + "    Room r ON b.RoomId = r.RoomId\n"
            + "JOIN \n"
            + "    Category c ON r.CategoryId = c.CategoryId\n"
            + "JOIN \n"
            + "    Account a ON b.AccountId = a.AccountId\n"
            + "WHERE \n"
            + "    b.BookingStatus = 'Not yet' \n"
            + "ORDER BY \n"
            + "    b.CheckInDate ASC;";

    try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Booking bookingInfo = new Booking(
                    rs.getInt("No"),
                    rs.getInt("BookingId"),
                    rs.getInt("RoomId"),
                    rs.getString("RoomType"),
                    rs.getDate("BookingDate"),
                    rs.getDate("CheckInDate"),
                    rs.getDate("CheckOutDate"),
                    rs.getString("Note"),
                    rs.getString("Name"),
                    rs.getInt("AccountId"),
                    rs.getString("Status")
            );
            list.add(bookingInfo);
        }
    } catch (Exception e) {
        System.out.println(e);
    }

    return list;
}

        public List<Booking> getAllOnGoingBooking() {
    List<Booking> list = new ArrayList<>();
    String sql = "SELECT \n"
            + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
            + "    b.BookingId,\n"
            + "    b.RoomId,\n"
            + "    c.CategoryName AS RoomType,\n"
            + "    b.BookingDate,\n"
            + "    b.CheckInDate,\n"
            + "    b.CheckOutDate,\n"
            + "    b.Note,\n"
            + "    a.FullName AS Name,\n"
            + "    a.AccountId,\n"
            + "    b.BookingStatus AS Status\n"
            + "FROM \n"
            + "    Booking b\n"
            + "JOIN \n"
            + "    Room r ON b.RoomId = r.RoomId\n"
            + "JOIN \n"
            + "    Category c ON r.CategoryId = c.CategoryId\n"
            + "JOIN \n"
            + "    Account a ON b.AccountId = a.AccountId\n"
            + "WHERE \n"
            + "    b.BookingStatus = 'On Going' \n"
            + "ORDER BY \n"
            + "    b.CheckOutDate ASC;";

    try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Booking bookingInfo = new Booking(
                    rs.getInt("No"),
                    rs.getInt("BookingId"),
                    rs.getInt("RoomId"),
                    rs.getString("RoomType"),
                    rs.getDate("BookingDate"),
                    rs.getDate("CheckInDate"),
                    rs.getDate("CheckOutDate"),
                    rs.getString("Note"),
                    rs.getString("Name"),
                    rs.getInt("AccountId"),
                    rs.getString("Status")
            );
            list.add(bookingInfo);
        }
    } catch (Exception e) {
        System.out.println(e);
    }

    return list;
}

    public void acceptBooking(int bookingId, String bookingStatus) {
        String sql = "UPDATE [dbo].[Booking]\n"
                + "SET [BookingStatus] = ?\n"
                + "WHERE [BookingId] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookingStatus);
            ps.setInt(2, bookingId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStatusDone(int id, long totalPrice) {
    String sql = "UPDATE [dbo].[Booking] SET [BookingStatus] = 'Done', [TotalPrice] = ? WHERE [BookingId] = ?;";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setLong(1, totalPrice);
        ps.setInt(2, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void changeStatusOnGoing(int id) {
        String sql = "UPDATE [dbo].[Booking] SET [BookingStatus] = 'On Going' WHERE [BookingId] = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeStatusCancel(int id) {
        String sql = "UPDATE [dbo].[Booking] SET [BookingStatus] = 'On Going' WHERE [BookingId] = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
