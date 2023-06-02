/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.product;
import org.primefaces.event.RowEditEvent;
import util.dataBean;
/**
 *
 * @author Haya
 * 
 * Die productBean ist die Schnittstelle zwischen dem User und der Datenbank bezüglich der Produktansicht.
 * Hier sind  dazu noch Funktionen zum DataTable RowEdit und löschen eines Produkts aus dem Data Table.
 */
@Named(value = "productBean")
@ApplicationScoped
public class productBean implements Serializable {
    
    private List<product> productDataList;
    private dataBean productData;
    
    public productBean(){
        productData = new dataBean();
        productDataList = productData.getProductList();
    }
    
    // Löschen eines Produkts mit einer Growl Bestätigung.
    public void deleteProduct(product selectedProduct){
	FacesMessage msg = new FacesMessage("Product Removed", selectedProduct.getProdName()+" has been removed");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        productDataList.remove(selectedProduct);
    }
    
    // Gibt eine Growl Bestätigung wenn der Benutzer mit Adminrechten ein Produkt geändert hat
    public void onRowEdit(RowEditEvent<product> event){
        FacesMessage msg = new FacesMessage("Product Edited", "Product with ID: " +
                String.valueOf(event.getObject().getProdID())+ " has been edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // Gibt eine Growl Bestätigung wenn der Benutzer mit Adminrechten ein Produkt doch nicht geändert hat
    public void onRowCancel(RowEditEvent<product> event){
        FacesMessage msg = new FacesMessage("Editing Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void saveCurrentProduct(product currentProduct) {
        productData.updateProduct(currentProduct);
        FacesMessage msg = new FacesMessage("Product Edited", currentProduct.getProdName()+" has been edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    //Getter und Setter
    public List<product> getProductDataList() {
        return productDataList;
    }

    public void setProductDataList(List<product> productDataList) {
        this.productDataList = productDataList;
    }

    
}
