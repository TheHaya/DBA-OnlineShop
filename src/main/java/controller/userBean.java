/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import model.user;
import util.dataBean;
import util.sqlBean;
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
    private String sortOrder = "idAsc";
    
    private sqlBean userData;
    
    @PostConstruct
    public void init() {
        sortUsers();
    }
    
    public userBean(){
        userData = new sqlBean();
        userDataList = userData.getUserList();
    }

    public void sortUsers() {
        if (sortOrder.equals("idAsc")) {
            userDataList.sort(Comparator.comparing(user::getUserID));
        } else if (sortOrder.equals("idDesc")) {
            userDataList.sort(Comparator.comparing(user::getUserID).reversed());
        }
    }
    
    public List<user> getUserDataList() {
        return userDataList;
    }

    public void setProductDataList(List<user> userDataList) {
        this.userDataList = userDataList;
    }
    
    public String getSortOrder() {
        return sortOrder;
    }
    
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
    
}
