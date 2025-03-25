/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DELL
 */
public class Comment {
    private int commentId;
    private int postId;
    private int accountID;
    private String content;
    private LocalDateTime createdDate;

    public Comment() {
    }

    public Comment(int commentId, int postId, int accountID, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.accountID = accountID;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

    public Comment(int commentId, int postId, int accountID, String content, LocalDateTime createdDate) {
        this.commentId = commentId;
        this.postId = postId;
        this.accountID = accountID;
        this.content = content;
        this.createdDate = createdDate;
    }

    
    
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return createdDate.format(formatter);
}

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public static void main(String[] args) {
        Comment comment = new Comment(1, 101, 202, "This is a comment");
        System.out.println("Raw DateTime: " + comment.getCreatedDate());
    }
}
