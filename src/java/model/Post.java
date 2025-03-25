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
public class Post {
    private int postId;
    private int accountId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private String accountName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    

    public Post() {
    }

    public Post(int postId, int accountId, String title, String content) {
        this.postId = postId;
        this.accountId = accountId;
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

    public Post(int postId, int accountId, String title, String content, LocalDateTime createdDate) {
        this.postId = postId;
        this.accountId = accountId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
            }
}
