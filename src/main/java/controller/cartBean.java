/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.product;
import util.sqlBean;
/**
 *
 * @author Haya
 */

/*** 
* Klasse cartBean
* Beinhaltet die meisten Funktionen, die mit dem Warenkorb zusammenhängen.
* Der Cart ist eine leere Arrayliste, die sich nach und nach mit hinzugefügten Artikeln füllt.
***/
@Named(value = "cartBean")
@SessionScoped
public class cartBean implements Serializable {         // Serialisierbar ermöglicht die Objektspeicherung und Verkehr im Netzwerk

    private List<product> cart;

    public cartBean() {
        cart = new ArrayList<>();
    }
    
    // Fügt das Produkt zum Warenkorb hinzu
    // Checkt ob der Warenkorb schon denselben Artikel enthält und erhöhr dann nur die Variable Quantity um 1
    public void addToCart(product product){
        FacesMessage msg = new FacesMessage("Product added",product.getProdName() + " has been added to your cart.");
        if(!cart.isEmpty()){
            boolean match = false;          // match: Variable zum Abgleich von schon vorhandenen Artikeln
            for(product p : cart) {         // for-Schleife geht jeden Artikel durch im Warenkorb und vergleicht die IDs
                if(p.getProdID() == product.getProdID()) {
                    match = true;
                    p.setProdQuant(p.getProdQuant() + 1);
                    FacesContext.getCurrentInstance().addMessage(null, msg);    // Der User sieht die msg im Growl
                    break;
                }
            }
            if(match == false){
                FacesContext.getCurrentInstance().addMessage(null, msg);
                cart.add(product);
            }
        } 
        else{
            FacesContext.getCurrentInstance().addMessage(null, msg);
            cart.add(product);
        }
    }
    
    // Entfernt das alle Artikel (auch Duplikate) mit derselben ID aus dem Warenkorb
    public void delItem(product product){
        FacesMessage msg = new FacesMessage("Product removed",product.getProdName() + " has been removed from your cart.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	cart.remove(product);
    }
    
    // Berechnet und gibt den gesamten Warenwert im Warenkorb an
    public double cartPrice(){
        double total = 0.00;
        for(product p : cart) {
            total = total + (p.getProdPrice() * p.getProdQuant());
        }
        return total;
    }
    
    // Entfernt den Artikel aus dem Warenkorb wenn die Quantity durch einen Spinner oder manueller Eingabe
    // 0 erreicht hat und der Sync button betätigt wurde (Seite wird neugeladen)
    public void spinnerChangeListener(product p) {
       // cartPrice();
        if(p.getProdQuant() == 0)
        {
            FacesMessage msg = new FacesMessage("Product removed",p.getProdName() + " has been removed from your cart.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            cart.remove(p);
        }
    }

    public String checkout(int userID) {
        try {
            // Get the sqlBean instance
            FacesContext context = FacesContext.getCurrentInstance();
            sqlBean sqlBean = context.getApplication().evaluateExpressionGet(context, "#{sqlBean}", sqlBean.class);

            // Insert into orders table
            String ordersSql = "INSERT INTO orders (FK_CID) VALUES (?)";
            PreparedStatement ordersStatement = sqlBean.getConn().prepareStatement(ordersSql);
            ordersStatement.setInt(1, userID);
            ordersStatement.executeUpdate();

            // Get the generated order ID
            int orderID;
            String orderIDSql = "SELECT LAST_INSERT_ID()";
            PreparedStatement orderIDStatement = sqlBean.getConn().prepareStatement(orderIDSql);
            try (ResultSet orderIDResultSet = orderIDStatement.executeQuery()) {
                if (orderIDResultSet.next()) {
                    orderID = orderIDResultSet.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated order ID.");
                }
            }

            // Insert into orderdetail table
            for (product p : cart) {
                String orderDetailsSql = "INSERT INTO orderdetail (FK_OID, FK_PRID, ODAMOUNT) VALUES (?, ?, ?)";
                PreparedStatement orderDetailsStatement = sqlBean.getConn().prepareStatement(orderDetailsSql);
                orderDetailsStatement.setInt(1, orderID);
                orderDetailsStatement.setInt(2, p.getProdID());
                orderDetailsStatement.setInt(3, p.getProdQuant());
                orderDetailsStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cart = new ArrayList<>();
        return "checkout.xhtml";
    }
    
    //Getter and Setter
    public List<product> getCart() {
        return cart;
    }
}
