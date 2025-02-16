/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Category;
import model.Role;
import model.Room;
import sun.awt.KeyboardFocusManagerPeerImpl;

/**
 *
 * @author Admin
 */

public class RoomDAO extends DBContext {

    public ArrayList<Category> getTop3Category() {

        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT TOP(3) [CategoryId]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Capacity]\n"
                + "      ,[PricePerNight]\n"
                + "      ,[Description]\n"
                + "      ,[Image]\n"
                + "  FROM [Category]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            DecimalFormat df = new DecimalFormat("#,##0.000");
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryName(rs.getString("CategoryName"));
                c.setCapacity(rs.getInt("Capacity"));
                c.setDescription(rs.getString("Description"));
                c.setImage(rs.getString("Image"));
                c.setPricePerNight(rs.getDouble("PricePerNight"));
                c.setFormattedPrice(df.format(c.getPricePerNight()));
                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Category> ListCategory() {

        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT [CategoryId]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Capacity]\n"
                + "      ,[PricePerNight]\n"
                + "      ,[Description]\n"
                + "      ,[Image]\n"
                + "  FROM [Category]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryName(rs.getString("CategoryName"));
                c.setCapacity(rs.getInt("Capacity"));
                c.setDescription(rs.getString("Description"));
                c.setImage(rs.getString("Image"));
                c.setPricePerNight(rs.getDouble("PricePerNight"));
                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    public void findRoom() {

    }

    public int getTotalRoom() {
        ArrayList<Room> list = new ArrayList<>();
        String sql = "SELECT  [RoomId]\n"
                + "      ,[RoomNumber]\n"
                + "      ,[CategoryId]\n"
                + "      ,[Status]\n"
                + "  FROM [Room]"; //dang sai sql
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("RoomId"));
                r.setRoomNumber(rs.getString("RoomNumber"));
                Category c = new Category();
                c.setCategoryName(rs.getString("RoomType"));
                c.setCapacity(rs.getInt("Capacity"));
                c.setPricePerNight(rs.getDouble("PricePerNight"));
                c.setDescription(rs.getString("Description"));
                r.setCategory(c);
                r.setStatus(rs.getBoolean("Status"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list.size();
    }

    public static void main(String[] args) {
        RoomDAO r = new RoomDAO();
        ArrayList<Category> topCategory = new ArrayList<>();
        topCategory = r.getTop3Category();
        DecimalFormat df = new DecimalFormat("#,##0.000");
        for (Category c : topCategory) {
            System.err.println(c.getFormattedPrice());
        }

        System.err.println(r.getTotalRoom());

    }

}
