/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import newModel.Account;


/**
 *
 * @author Haya
 */
@Named(value = "dBRequestBean")
@RequestScoped
public class DBRequestBean {
    
    private List<Account> accountList;
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Creates a new instance of DBRequestBean
     */
    public DBRequestBean() {
    }
    
    
    // Getter and Setter

    public List<Account> getAccountList() {
        TypedQuery<Account> query = em.createNamedQuery("Account.findAll", Account.class);
        accountList = query.getResultList();
        
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    
}
