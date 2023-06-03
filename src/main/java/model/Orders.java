/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.sql.Date;

/**
 *
 * @author wrede
 */
public class Orders {

    private Date delDate;
    private status status;
    private Customer customer;
    private String comment;
    private Timestamp changeDate;

    enum status {
    Pending,
    Completed,
    Denied
}
    
    public Orders(Date delDate, status status, Customer customer, String comment, Timestamp changeDate) {
        this.delDate = delDate;
        this.status = status;
        this.customer = customer;
        this.comment = comment;
        this.changeDate = changeDate;
    }
    
    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }
    
    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
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
            
    
    
    
}
