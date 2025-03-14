/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    
    
    
    public Room getRoomByID(String id) {
        String sql = "SELECT [RoomId], [RoomNumber], [CategoryId], [Status], [Image],[RoomType] "
                   + "FROM [Room] WHERE [RoomId] = ?";
        try (
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("RoomId"));
                room.setRoomNumber(rs.getString("RoomNumber"));
                room.setRoomType(rs.getString("RoomType"));
                return room;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        RoomDAO rd = new RoomDAO();
        Room room = rd.getRoomByID("1");
        System.out.println(room.getRoomType()+" "+room.getRoomNumber());
    }
    
    public Category getRoomCategoryById(int categoryId) {
        Category category = null;
        try {
            String sql = "SELECT [CategoryId]\n"
                    + "      ,[CategoryName]\n"
                    + "      ,[Capacity]\n"
                    + "      ,[PricePerNight]\n"
                    + "      ,[Description]\n"
                    + "      ,[Image]\n"
                    + "      ,[Size]\n"
                    + "      ,[Bed]\n"
                    + "  FROM [Category] WHERE CategoryId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt("CategoryId"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCapacity(rs.getInt("Capacity"));
                category.setPricePerNight(rs.getDouble("PricePerNight"));
                category.setDescription(rs.getString("Description"));
                category.setImage(rs.getString("Image"));
                category.setSize(rs.getDouble("Size"));
                category.setBed(rs.getInt("Bed"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public List<Category> getSimilarRoomCategories(int currentCategoryId) {
        List<Category> similarCategories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Category WHERE CategoryId != ? ORDER BY CategoryId OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, currentCategoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("CategoryId"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCapacity(rs.getInt("Capacity"));
                category.setPricePerNight(rs.getDouble("PricePerNight"));
                category.setDescription(rs.getString("Description"));
                category.setImage(rs.getString("Image"));
                category.setSize(rs.getDouble("Size"));
                similarCategories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return similarCategories;
    }

    public int getRoomCountByCategoryId(int categoryId) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS RoomCount FROM Room WHERE CategoryId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                count = rs.getInt("RoomCount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public ArrayList<Category> getTop3Category() {

        ArrayList<Category> list = new ArrayList<>();
        String sql = " \n"
                + "SELECT TOP (3)\n"
                + "    c.CategoryName,c.Description,c.Capacity,c.Image,c.PricePerNight,\n"
                + "    COUNT(*) AS NumberOfBookings\n"
                + "FROM \n"
                + "    [dbo].[Booking] b\n"
                + "join Room r on r.RoomId = b.RoomId\n"
                + "join Category c on r.CategoryId = c.CategoryId\n"
                + "GROUP BY \n"
                + "    c.CategoryName,c.Description,c.Capacity,c.Image,c.PricePerNight\n"
                + "ORDER BY \n"
                + "    NumberOfBookings DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            DecimalFormat df = new DecimalFormat();
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
                + "      ,[Size]\n"
                + "      ,[Bed]\n"
                + "  FROM [Category]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            DecimalFormat df = new DecimalFormat();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("CategoryId"));
                c.setCategoryName(rs.getString("CategoryName"));
                c.setCapacity(rs.getInt("Capacity"));
                c.setDescription(rs.getString("Description"));
                c.setImage(rs.getString("Image"));
                c.setPricePerNight(rs.getDouble("PricePerNight"));
                c.setFormattedPrice(df.format(c.getPricePerNight()));
                c.setSize(rs.getDouble("Size"));
                c.setBed(rs.getInt("Bed"));
                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Category> getAllCapacities() {
        ArrayList<Category> list = new ArrayList<>();
        //lấy số lượng người trong một phòng 
        String sql = "SELECT DISTINCT [Capacity]\n"
                + "  FROM [Category]\n"
                + "  ORDER BY [Capacity] ASC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCapacity(rs.getInt("Capacity"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Category> searchCategory(String categoryName) {
        ArrayList<Category> list = new ArrayList<>();

        String sql = "SELECT [CategoryId]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Capacity]\n"
                + "      ,[PricePerNight]\n"
                + "      ,[Description]\n"
                + "      ,[Image]\n"
                + "      ,[Size]\n"
                + "      ,[Bed]\n"
                + "  FROM [Category] c "
                + "  where c.CategoryName like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, categoryName);
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

    public ArrayList<Category> filterRoomCategory(Double minPrice, Double maxPrice, String categoryName,
            String checkOutDate, String checkInDate) {
        ArrayList<Category> list = new ArrayList<>();
        // Handle default values
        double effectiveMinPrice = (minPrice != null) ? minPrice : 0;

        // Get current date for default check-in
        LocalDate currentDate = LocalDate.now();
        String defaultCheckInDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Get end of year for default check-out
        LocalDate endOfYear = LocalDate.of(currentDate.getYear(), 12, 31);
        String defaultCheckOutDate = endOfYear.format(DateTimeFormatter.ISO_LOCAL_DATE);

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT \n")
                .append("c.CategoryId,\n")
                .append("    c.CategoryName, \n")
                .append("    c.Capacity, \n")
                .append("    c.PricePerNight, c.Image, \n")
                .append("    COUNT(*) AS NumberOfRooms\n")
                .append("FROM \n")
                .append("    Room r\n")
                .append("INNER JOIN \n")
                .append("    Category c ON r.CategoryId = c.CategoryId\n")
                .append("WHERE \n")
                .append("    r.Status = 'Available'\n")
                .append("    AND NOT EXISTS (\n")
                .append("        SELECT 1\n")
                .append("        FROM Booking b\n")
                .append("        WHERE \n")
                .append("            b.RoomId = r.RoomId\n")
                .append("            AND (\n")
                .append("                (b.CheckInDate <= ? AND b.CheckOutDate >= ?)\n")
                .append("            )\n")
                .append("            AND b.BookingStatus = 'Confirmed'\n")
                .append("    )\n");

        // Add dynamic conditions based on parameters
        ArrayList<Object> params = new ArrayList<>();
        params.add(checkOutDate);
        params.add(checkInDate);

        // Handle category name filter
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            sqlBuilder.append("   AND c.CategoryName = ?\n");
            params.add(categoryName);
        }

        // Get default max price if not provided or invalid
        double actualMaxPrice = (maxPrice != null && maxPrice > 0) ? maxPrice : getMaxPriceFromDatabase();
        double actualMinPrice = (minPrice != null && minPrice >= 0) ? minPrice : 0;

        sqlBuilder.append("    AND c.PricePerNight >= ? AND c.PricePerNight <= ?\n");
        params.add(actualMinPrice);
        params.add(actualMaxPrice);

        // Complete the query
        sqlBuilder.append("GROUP BY \n")
                .append("    c.CategoryId, c.CategoryName, c.Capacity, c.PricePerNight, c.Image\n")
                .append("ORDER BY \n")
                .append("    c.CategoryName");

        try {
            PreparedStatement ps = connection.prepareStatement(sqlBuilder.toString());

            // Set parameters for the prepared statement
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("CategoryId"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setCapacity(rs.getInt("Capacity"));
                category.setPricePerNight(rs.getDouble("PricePerNight"));
                category.setNumberOfRooms(rs.getInt("NumberOfRooms"));
                category.setImage(rs.getString("Image"));
                list.add(category);
            }

        } catch (SQLException e) {
            System.out.println("Error filtering room categories: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

// Helper method to get the maximum price from the database
    private double getMaxPriceFromDatabase() {
        double maxPrice = Double.MAX_VALUE; // Default fallback value

        String sql = "SELECT MAX(PricePerNight) AS MaxPrice FROM Category";

        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                maxPrice = rs.getDouble("MaxPrice");
            }

        } catch (SQLException e) {
            System.out.println("Error getting max price: " + e.getMessage());
            e.printStackTrace();
        }

        return maxPrice;
    }

    public int getTotalRoom() {
        ArrayList<Room> list = new ArrayList<>();
        String sql = "SELECT  [RoomId]\n"
                + "      ,[RoomNumber]\n"
                + "      ,[CategoryId]\n"
                + "      ,[Status]\n"
                + "  FROM [Room]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("RoomId"));
                r.setRoomNumber(rs.getString("RoomNumber"));
                r.setStatus(rs.getBoolean("Status"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list.size();
    }
    
    

    
}
