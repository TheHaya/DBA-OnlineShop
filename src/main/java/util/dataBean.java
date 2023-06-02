/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.product;
import model.user;
/**
 *
 * @author Haya
 * 
 * Die dataBean beinhaltet die Details zu den einzelnen Produkten und Benutzerkonten
 * und besitzt in diesem Praktikum zwei hard-coded ArrayListen mit DummyObjekten drin. 
 */
@Named(value = "dataBean")
@ApplicationScoped
public class dataBean implements Serializable {
        private List<product> productList;
        private List<user> userList;
        private int nextUserID;
        private String sortOrder = "idAsc";
        private List<user> sortedUserList;
        private List<product> sortedProductList;
        
    //Hard coded product and user lists without DB
    public dataBean() {
        
        productList = new ArrayList<>();
        productList.add(new product("Mozzarella Pizza", "Pizza", "It's a mozzarella pizza!",
                "images/mozzarella-pizza.png", 2.99, 1,1));
        productList.add(new product("Tuna Pizza", "Pizza", "It's a tuna pizza!",
                "images/tuna-pizza.png", 3.49, 2,1));
        productList.add(new product("Pepperoni Pizza", "Pizza", "It's a pepperoni pizza!",
                "images/pepperoni-pizza.png",3.49, 3,1));
        productList.add(new product("Spinach Pizza", "Pizza", "It's a spinach pizza!",
                "images/spinach-pizza.png",3.29, 4,1));
        productList.add(new product("Chocolate Ice Cream", "Ice Cream", "It's a tub of chocolate ice cream!",
                "images/chocolate-ice-cream.png",4.89, 5,1));
        productList.add(new product("Vanilla Ice Cream", "Ice Cream", "It's a tub of vanilla ice cream!",
                "images/vanilla-ice-cream.png",4.49, 6,1));
        productList.add(new product("Strawberry Ice Cream", "Ice Cream", "It's a tub of strawberry ice cream!",
                "images/strawberry-ice-cream.png",4.89, 7,1));
        productList.add(new product("Chocolate Cake", "Cake", "It's a chocolate cake!",
                "images/chocolate-cake.png",6.49, 8,1));
        productList.add(new product("Cheesecake", "Cake", "It's a cheesecake!",
                "images/cheesecake.png",6.49, 9,1));
        productList.add(new product("Blueberry Muffin", "Cake", "It's four blueberry muffins!",
                "images/blueberry-muffins.png",4.79, 10,1));
        productList.add(new product("Strawberries", "Fruit", "It's a pack of frozen strawberries!",
                "images/strawberries.png",3.75, 11,1));
        productList.add(new product("Mangos", "Fruit", "It's a pack of frozen mangos!",
                "images/mangos.png",4.25, 12,1));
        productList.add(new product("Berry Mix", "Fruit", "It's a pack of frozen berries!",
                "images/berry-mix.png",3.75, 13,1));
        
        userList = new ArrayList<>();
        userList.add(new user("admin","admin","admin@shop.de",1, 0));
        userList.add(new user("mod111","mod111","mod111@shop.de",2, 1));
        userList.add(new user("aaa111","aaa111","aaa111@shop.de",3, 2));
        userList.add(new user("bbb222","bbb222","bbb222@shop.de",4, 2));
        nextUserID = 5;
    }
    
    @PostConstruct
    public void init() {
        sortUsers();
        sortProducts();
    }
    // Funktion um einen neuen Benutzer hinzuzufügen (Registrierung)
    public void addUser(user newUser){
        userList.add(newUser);
        nextUserID++;
    }

    
    // Getter und Setter
    public List<product> getProductList() {
        return productList;
    }

    public List<user> getUserList() {
        return userList;
    }

    public int getNextUserID() {
        return nextUserID;
    }
    
    public void updateProduct(product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProdID() == updatedProduct.getProdID()) {
                productList.set(i, updatedProduct);
                break;
            }
        }
    }
    public String getSortOrder() {
        return sortOrder;
    }
    
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<user> getSortedUserList() {
        return sortedUserList;
    }
    
    public List<product> getSortedProductList() {
        return sortedProductList;
    }
    
    public void sortUsers() {
        sortedUserList = new ArrayList<>(userList);
        if (sortOrder.equals("idAsc")) {
            sortedUserList.sort(Comparator.comparing(user::getUserID));
        } else if (sortOrder.equals("idDesc")) {
            sortedUserList.sort(Comparator.comparing(user::getUserID).reversed());
        }
    }
    
    public void sortProducts() {
        sortedProductList = new ArrayList<>(productList);
        if (sortOrder.equals("idAsc")) {
            sortedProductList.sort(Comparator.comparing(product::getProdID));
        } else if (sortOrder.equals("idDesc")) {
            sortedProductList.sort(Comparator.comparing(product::getProdID).reversed());
        }
    }
    
}
