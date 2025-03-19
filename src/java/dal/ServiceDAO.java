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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Service;

/**
 *
 * @author Admin
 */
public class ServiceDAO extends DBContext{
    
    public ArrayList<Service> getAllServices() {
        ArrayList<Service> list = new ArrayList<>();
        String sql = "SELECT s.ServiceName, s.Description, s.Price, s.Status " +
                     "FROM Service s " +
                     "LEFT JOIN Category_Service cs ON s.ServiceId = cs.ServiceId " +
                     "LEFT JOIN Category c ON c.CategoryId = cs.CategoryId " +
                     "ORDER BY s.ServiceId ASC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setServiceName(rs.getString("ServiceName"));
                service.setDescription(rs.getString("Description"));
                service.setPrice(rs.getDouble("Price"));
                service.setStatus(rs.getString("Status"));
                list.add(service);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy thông tin một dịch vụ theo ID
    public Service getServiceById(int serviceId) {
        Service service = null;
        String sql = "SELECT s.ServiceId, s.ServiceName, s.Description, s.Price, s.Status, cs.CategoryId " +
                     "FROM Service s " +
                     "LEFT JOIN Category_Service cs ON s.ServiceId = cs.ServiceId " +
                     "WHERE s.ServiceId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, serviceId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                service = new Service();
                service.setServiceName(rs.getString("ServiceName"));
                service.setDescription(rs.getString("Description"));
                service.setPrice(rs.getDouble("Price"));
                service.setStatus(rs.getString("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service;
    }

    // Thêm một dịch vụ mới
    public void addService(Service service) {
        String serviceSql = "INSERT INTO Service (ServiceName, Description, Price, Status) VALUES (?, ?, ?, ?)";
        String categoryServiceSql = "INSERT INTO Category_Service (CategoryId, ServiceId) VALUES (?, ?)";
        try {
            // Thêm vào bảng Service
            PreparedStatement serviceSt = connection.prepareStatement(serviceSql);
            serviceSt.setString(1, service.getServiceName());
            serviceSt.setString(2, service.getDescription());
            serviceSt.setDouble(3, service.getPrice());
            serviceSt.setString(4, service.getStatus());
            serviceSt.executeUpdate();

            // Lấy ServiceId tự động tạo
            ResultSet generatedKeys = serviceSt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int serviceId = generatedKeys.getInt(1);

                // Thêm vào bảng Category_Service
                PreparedStatement categoryServiceSt = connection.prepareStatement(categoryServiceSql);
                categoryServiceSt.setInt(1, service.getCategoryService().getCategoryId());
                categoryServiceSt.setInt(2, serviceId);
                categoryServiceSt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Xóa một dịch vụ
    public void deleteService(int serviceId) {
        String categoryServiceSql = "DELETE FROM Category_Service WHERE ServiceId = ?";
        String serviceSql = "DELETE FROM Service WHERE ServiceId = ?";
        try {
            // Xóa khỏi bảng Category_Service
            PreparedStatement categoryServiceSt = connection.prepareStatement(categoryServiceSql);
            categoryServiceSt.setInt(1, serviceId);
            categoryServiceSt.executeUpdate();

            // Xóa khỏi bảng Service
            PreparedStatement serviceSt = connection.prepareStatement(serviceSql);
            serviceSt.setInt(1, serviceId);
            serviceSt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Service> getServiceUsageByBookingId(int bookingId) {
    List<Service> serviceUsages = new ArrayList<>();
    String sql = "SELECT s.ServiceName, su.Quantity, s.Price " +
                 "FROM ServiceUsage su " +
                 "JOIN Service s ON su.ServiceId = s.ServiceId " +
                 "WHERE su.BookingId = ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, bookingId);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Service serviceUsage = new Service();
            serviceUsage.setServiceName(rs.getString("ServiceName"));
            serviceUsage.setQuantity(rs.getInt("Quantity"));
            serviceUsage.setPrice(rs.getDouble("Price"));
            serviceUsages.add(serviceUsage);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return serviceUsages;
}
    
    public static void main(String[] args) {
        ServiceDAO sv = new ServiceDAO();
        List<Service> usg = sv.getServiceUsageByBookingId(15);
        for (Service service : usg) {
            System.out.println(service.getServiceName()+" "+service.getQuantity() +" "+service.getPrice());
        }
    }
    
    public ArrayList<Service> getServiceByCategory(){
        ArrayList<Service> services = new ArrayList<>();
        
        
        return services;
    }
}
