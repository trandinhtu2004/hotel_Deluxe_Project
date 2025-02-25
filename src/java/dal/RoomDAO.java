/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Category;
import model.Role;
import model.Room;

/**
 *
 * @author Admin
 */
public class RoomDAO extends DBContext {
    
    public List<Category> getCategory(){
        
            List<Category> list = new ArrayList<>();
            String sql="";
           try{ 
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Category c = new Category();
                c.setCategoryName(sql);
                c.setCapacity(0);
                c.setDescription(sql);
                c.setImage(sql);
                c.setPricePerNight(0);
                
            }
             
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           return list;
    }
    
    public List<Category> listRoomCategory() {
        
            List<Category> list = new ArrayList<>();
            String sql = "";
            try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                Category c = new Category();
                c.setCategoryId(rs.getInt("CategoryId"));
                c.setCategoryName(sql);
                c.setCapacity(0);
                c.setPricePerNight(0);
                c.setDescription(sql);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return list;
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
}
