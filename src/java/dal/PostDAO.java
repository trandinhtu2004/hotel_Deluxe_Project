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
import model.Post;
import model.Comment;

public class PostDAO extends DBContext{
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT PostId, AccountId, Title, Content, CreatedDate FROM Post";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("PostId"),
                        rs.getInt("AccountId"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                       rs.getTimestamp("CreatedDate").toLocalDateTime());
                
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
            public List<Comment> getPostCommentsById(int postId) {
    List<Comment> comments = new ArrayList<>();
    String sql = "SELECT CommentId, PostId, AccountId, Content, CreatedDate FROM Comment WHERE PostId = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, postId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("CommentId"),
                        rs.getInt("PostId"),
                        rs.getInt("AccountId"),
                        rs.getString("Content"),
                        rs.getTimestamp("CreatedDate").toLocalDateTime()
                );
                comments.add(comment);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return comments;
}
    
    public void createPost(int accountId, String title, String content) {
    String sql = "INSERT INTO Post (AccountId, Title, Content, CreatedDate) VALUES (?, ?, ?, GETDATE())";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, accountId);
        ps.setString(2, title);
        ps.setString(3, content);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public void insertComment(int postId, int accountId, String content) {
    String sql = "INSERT INTO Comment (PostId, AccountId, Content, CreatedDate) VALUES (?, ?, ?, GETDATE())";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, postId);
        ps.setInt(2, accountId);
        ps.setString(3, content);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    

    public static void main(String[] args) {
        PostDAO pd= new PostDAO();
        List<Post> list = pd.getAllPosts();
        for (Post post : list) {
            System.out.println(post.getTitle()+" "+post.getContent()+" "+post.getCreatedDate()+" "+post.getPostId());
        }
       
//        List<Comment> list = pd.getPostCommentsById(1);
//        for (Comment comment : list) {
//            System.out.println(comment.getContent());
//        }
    }
    
}
