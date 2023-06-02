/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
/**
 *
 * @author Haya
 * 
 * Die Produktklasse gibt alle benötigten Variablen vor, die von den BackingBeans benutzt werden.
 */
public class product implements Serializable{
    private String prodName;  // Product Name
    private String prodType;  // Product Type
    private String prodDesc;  // Product Description
    private String prodPic;   // Product Picture
    private double prodPrice; // Product Price
    private int prodID;       // Product ID
    private int prodQuant;    // Product Quantity
    
    public product(String prodName, 
                   String prodType, 
                   String prodDesc, 
                   String prodPic,
                   double prodPrice, 
                   int prodID,
                   int prodQuant){
        this.prodName = prodName;
        this.prodType = prodType;
        this.prodDesc = prodDesc;
        this.prodPic = prodPic;
        this.prodPrice = prodPrice;
        this.prodID = prodID;
        this.prodQuant = prodQuant;
    }
    
    // Getter und Setter
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }
    
    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public int getProdQuant() {
        return prodQuant;
    }

    public void setProdQuant(int prodQuant) {
        this.prodQuant = prodQuant;
    }

    public String getProdPic() {
        return prodPic;
    }

    public void setProdPic(String prodPic) {
        this.prodPic = prodPic;
    }
    
    @Override
    public String toString() {
        return this.getProdName();
    }
    
}
