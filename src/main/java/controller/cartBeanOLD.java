///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
// */
//package controller;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.inject.Named;
//import jakarta.enterprise.context.SessionScoped;
//import jakarta.faces.application.FacesMessage;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.event.AjaxBehaviorEvent;
//import jakarta.inject.Inject;
//import java.io.Serializable;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.logging.Level;
//import newModel.Customer;
//import newModel.Orderdetail;
//import newModel.Orders;
//import model.user;
//import newModel.Product;
//import util.sqlBean;
///**
// *
// * @author Haya
// */
//
///*** 
//* Klasse cartBean
//* Beinhaltet die meisten Funktionen, die mit dem Warenkorb zusammenhängen.
//* Der Cart ist eine leere Arrayliste, die sich nach und nach mit hinzugefügten Artikeln füllt.
//***/
//@Named(value = "cartBean")
//@SessionScoped
//public class cartBeanOLD implements Serializable {         // Serialisierbar ermöglicht die Objektspeicherung und Verkehr im Netzwerk
//
//    private List<Product> cart;
//    private user curUser;
//    private FacesContext context;
//    private LocalDate delDate;
//    private Customer customer;
//    private String comment;
//    private Timestamp changeDate;
//    private Timestamp orderDate;
//    private int amount;
//    private Product product;
//    private Orders order;
//    private Orderdetail orderDetail;
//    
//    
//    @Inject
//    private loginBean cartLogin;
//    
//    @Inject
//    private sqlBean cartData;
//    
//    @PostConstruct
//    public void init(){
//        context = FacesContext.getCurrentInstance();
//    }
//    
//    public cartBeanOLD() {
//        cartData = new sqlBean();
//        cartLogin = new loginBean();
//        cart = new ArrayList<>();
//    }
//    
//    // Fügt das Produkt zum Warenkorb hinzu
//    // Checkt ob der Warenkorb schon denselben Artikel enthält und erhöhr dann nur die Variable Quantity um 1
//    public void addToCart(Product product){
//        FacesMessage msg = new FacesMessage("Product added",product.getPrname() + " has been added to your cart.");
//        if(!cart.isEmpty()){
//            boolean match = false;          // match: Variable zum Abgleich von schon vorhandenen Artikeln
//            for(Product p : cart) {         // for-Schleife geht jeden Artikel durch im Warenkorb und vergleicht die IDs
//                if(p.getPrid() == product.getPrid()) {
//                    match = true;
//                    p.setProdQuant(p.getProdQuant() + 1);
//                    FacesContext.getCurrentInstance().addMessage(null, msg);    // Der User sieht die msg im Growl
//                    break;
//                }
//            }
//            if(match == false){
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//                cart.add(product);
//            }
//        } 
//        else{
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            cart.add(product);
//        }
//    }
//    
//    // Entfernt das alle Artikel (auch Duplikate) mit derselben ID aus dem Warenkorb
//    public void delItem(Product product){
//        FacesMessage msg = new FacesMessage("Product removed",product.getProdName() + " has been removed from your cart.");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//	cart.remove(product);
//    }
//    
//    // Berechnet und gibt den gesamten Warenwert im Warenkorb an
//    public double cartPrice(){
//        double total = 0.00;
//        for(Product p : cart) {
//            total = total + (p.getPrpricenetto() * p.getProdQuant());
//        }
//        return total;
//    }
//    
//    // Entfernt den Artikel aus dem Warenkorb wenn die Quantity durch einen Spinner oder manueller Eingabe
//    // 0 erreicht hat und der Sync button betätigt wurde (Seite wird neugeladen)
//    public void spinnerChangeListener(Product p) {
//       // cartPrice();
//        if(p.getProdQuant() == 0)
//        {
//            FacesMessage msg = new FacesMessage("Product removed",p.getProdName() + " has been removed from your cart.");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            cart.remove(p);
//        }
//    }
//
//    public String checkout(){
//        context = FacesContext.getCurrentInstance();
//        FacesMessage facesMessage;
//        
//        Customer curCustomer = cartLogin.getLoggedInCustomer();
//        customer = curCustomer;
//        
//        Orders newOrder = new Orders(delDate, customer, comment, changeDate);
//        order = newOrder;
//        
//        Orderdetail newOrderDetail = new Orderdetail(orderDate, amount, product);
//        order.setOrderdetail(newOrderDetail);
//
//        cartData.insertCheckout(order, customer, this);
//        facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Checkout successful", "Thanks for shopping!");
//        context.addMessage(null, facesMessage);
//        cart = new ArrayList<>();
//        
//        return "checkout.xhtml";
//    }
//    
//    //Getter and Setter
//    public List<Product> getCart() {
//        return cart;
//    }
//}
