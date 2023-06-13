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
import java.util.logging.Level;
import java.util.logging.Logger;
import newModel.Account;
import newModel.Product;


/**
 *
 * @author Haya
 */
@Named(value = "DBRequestBean")
@RequestScoped
public class DBRequestBean {
    
    private static final Logger LOGGER = Logger.getLogger(DBRequestBean.class.getName());
    private List<Account> accountList;
    private List<Product> productList;
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Creates a new instance of DBRequestBean
     */
    public DBRequestBean() {
    }
    
    
    // Getter and Setter

    
    public List<Product> getProducts() {
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
            productList = query.getResultList();
        }
        catch (Exception ex){
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return productList;
    }
    
    public List<Account> getAccounts() {
        try {
            TypedQuery<Account> query = em.createNamedQuery("Account.findAll", Account.class);
            accountList = query.getResultList();
        }
        catch (Exception ex){
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return accountList;
    }

    public void setAccounts(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
    
}
