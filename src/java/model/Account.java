/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Account {

    private int accountId;
    private Role role;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String OTP;

    private int roleId;
    private String Address;
    private String CreateDate;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public Account() {
    }
    
    public Account(int roleId, String fullname, String Email, String Phone, String Address, String CreateDate) {
        this.roleId = roleId;
        this.fullName = fullname;
        this.email = Email;
        this.phone = Phone;
        this.Address = Address;
        this.CreateDate = CreateDate;
    }

    public Account(int accountId, Role role, String fullName, String email, String password, String phone, String OTP) {
        this.accountId = accountId;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.OTP = OTP;
    }

    public Account(String fullName, String email, String password, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

}
