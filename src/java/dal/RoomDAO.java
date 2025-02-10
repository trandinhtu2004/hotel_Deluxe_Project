/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;
import model.Room;

/**
 *
 * @author Admin
 */
public class RoomDAO extends DBContext {

    public void listRoomCategory() {
        String sql = "";
    }
    
    public void findRoom(){
        
    }

    public int getTotalRoom() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT[RoomId]\n"
                + "      ,[RoomNumber]\n"
                + "      ,[RoomType]\n"
                + "      ,[Capacity]\n"
                + "      ,[PricePerNight]\n"
                + "      ,[Description]\n"
                + "      ,[Status]\n"
                + "  FROM [Room]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("RoomId"));
                r.setRoomNumber(rs.getString("RoomNumber"));
                r.setRoomType(rs.getString("RoomType"));
                r.setCapacity(rs.getInt("Capacity"));
                r.setPricePerNight(rs.getDouble("PricePerNight"));
                r.setDescription(rs.getString("Description"));
                r.setStatus(rs.getBoolean("Status"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list.size();
    }
}
