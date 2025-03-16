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

public class BookingDAO extends DBContext {

    public static void main(String[] args) {
        BookingDAO bid = new BookingDAO();
        List<Booking> list = bid.getAllOnGoingBooking();
        for (Booking bookingInfo : list) {
            System.out.println(bookingInfo.getNo() + " " + bookingInfo.getBookingId() + " " + bookingInfo.getRoomId() + " " + bookingInfo.getRoomType() + " " + bookingInfo.getBookingDate() + " " + bookingInfo.getCheckInDate() + " " + bookingInfo.getCheckOutDate() + " " + bookingInfo.getCustomerName() + " " + bookingInfo.getAccoutID() + " " + bookingInfo.getStatus());
        }
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

    public List<Booking> getTodayCheckIn() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE CAST(CheckInDate AS DATE) = CAST(GETDATE() AS DATE)";
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
            System.out.println("Error getTodayCheckIn: " + e.getMessage());
        }
        return list;
    }

    public List<Booking> getAllBookingInformation() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
                + "    b.BookingId,\n"
                + "    b.RoomId,\n"
                + "    r.RoomType,\n"
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
                + "    Account a ON b.AccountId = a.AccountId\n"
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

    public List<Booking> getAllNotYetBooking() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
                + "    b.BookingId,\n"
                + "    b.RoomId,\n"
                + "    r.RoomType,\n"
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
                + "    Account a ON b.AccountId = a.AccountId\n"
                + "WHERE \n"
                + "    b.BookingStatus = 'Not yet' \n"
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

    public List<Booking> getAllOnGoingBooking() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY b.BookingId) AS No,\n"
                + "    b.BookingId,\n"
                + "    b.RoomId,\n"
                + "    r.RoomType,\n"
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
                + "    Account a ON b.AccountId = a.AccountId\n"
                + "WHERE \n"
                + "    b.BookingStatus = 'On Going' \n"
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

    public void changeStatusDone(int id) {
        String sql = "UPDATE [dbo].[Booking] SET [BookingStatus] = 'Done' WHERE [BookingId] = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
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
