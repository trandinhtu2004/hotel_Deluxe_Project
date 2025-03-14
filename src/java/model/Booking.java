/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Booking {

    private int bookingId;
    private int accountId;
    private int roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal totalPrice;
    private String bookingStatus;
    private Date bookingDate;
    private String note;
    private String status;

    private int no;
    private String roomType;
    private String customerName;
    private int accoutID;

    public Booking() {
    }

    

    public Booking(int no, int bookingId, int roomId, String roomType, Date bookingDate, Date checkInDate, Date checkOutDate, String note, String customerName, int accoutID, String status) {
        this.no = no;
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.roomType = roomType;
        this.bookingDate = bookingDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.note = note;
        this.customerName = customerName;
        this.accoutID = accoutID;
        this.status = status;
    }

    public Booking(int bookingId, int accountId, int roomId, Date checkInDate, Date checkOutDate, BigDecimal totalPrice, String bookingStatus, Date bookingDate, String note, String status) {
        this.bookingId = bookingId;
        this.accountId = accountId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.bookingStatus = bookingStatus;
        this.bookingDate = bookingDate;
        this.note = note;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAccoutID() {
        return accoutID;
    }

    public void setAccoutID(int accoutID) {
        this.accoutID = accoutID;
    }
}
