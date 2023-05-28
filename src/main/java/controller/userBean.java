/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import model.user;
import util.dataBean;
/**
 *
 * @author Haya
 * Geplant war die userBean für die Veranschaulichung der Benutzerkonten und Daten im UserTable,
 * aber die dataBean funktionierte auch.
 */
@Named(value = "userBean")
@SessionScoped
public class userBean implements Serializable {
    
    private List<user> userDataList;
    
    @Inject
    private dataBean userData;
    
    public userBean(){ 
        userDataList = userData.getUserList();
    }

    public List<user> getUserDataList() {
        return userDataList;
    }

    public void setProductDataList(List<user> userDataList) {
        this.userDataList = userDataList;
    }
    
    
    
}
