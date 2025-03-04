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


public class BookingDAO extends DBContext{
    
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
    
}
