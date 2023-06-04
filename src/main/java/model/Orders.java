/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author wrede
 */
public class Orders {
    
    private int oid;
    private LocalDate delDate;
    private status status;
    private Customer customer;
    private String comment;
    private Timestamp changeDate;
    private OrderDetail orderDetail;
    private LocalDate currentDate;

    
    
    enum status {
    Pending,
    Completed,
    Denied
}
    
    public Orders(LocalDate delDate,
                  Customer customer, 
                  String comment, 
                  Timestamp changeDate) {
        this.delDate = delDate;
        this.customer = customer;
        this.comment = comment;
        this.changeDate = changeDate;
    }
    
    public LocalDate getDelDate() {
        currentDate = LocalDate.now();
        delDate = currentDate.plusDays(7);
        return delDate;
    }

    public void setDelDate(LocalDate delDate) {
        this.delDate = delDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Timestamp changeDate) {
        this.changeDate = changeDate;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
            
    
    
    
}
