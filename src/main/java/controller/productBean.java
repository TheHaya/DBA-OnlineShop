/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.transaction.RollbackException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import newModel.ProductCategory;
import newModel.Product;
import org.primefaces.event.RowEditEvent;
import util.sqlBean;

/**
 *
 * @author Haya
 *
 * Die productBean ist die Schnittstelle zwischen dem User und der Datenbank
 * bezüglich der Produktansicht. Hier sind dazu noch Funktionen zum DataTable
 * RowEdit und löschen eines Produkts aus dem Data Table.
 */
@Named(value = "productBean")
@SessionScoped
public class productBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(sqlBean.class.getName());
    private List<Product> productDataList;
    private List<ProductCategory> categoryList;
    private String sortOrder = "idAsc";

    @Inject
    private sqlBean productData;
   
    @PostConstruct
    public void init(){
            productDataList = productData.findAllProducts();
            categoryList = productData.findCategories();
    }

    // Löschen eines Produkts mit einer Growl Bestätigung.
//    public void deleteProduct(product selectedProduct){
//	FacesMessage msg = new FacesMessage("Product Removed", selectedProduct.getProdName()+" has been removed");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        productDataList.remove(selectedProduct);
//    }
    // Gibt eine Growl Bestätigung wenn der Benutzer mit Adminrechten ein Produkt geändert hat
    public void onRowEdit(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Edited", "Product with ID: "
                + String.valueOf(event.getObject().getPrid()) + " has been edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // Gibt eine Growl Bestätigung wenn der Benutzer mit Adminrechten ein Produkt doch nicht geändert hat
    public void onRowCancel(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Editing Cancelled", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void saveCurrentProduct(Product currentProduct) {
        try{
            
            LOGGER.log(Level.INFO, "currentProduct ID: " + currentProduct.getPrid());
            LOGGER.log(Level.INFO, "currentProduct name: " + currentProduct.getPrname());
            LOGGER.log(Level.INFO, "currentProduct price: " + currentProduct.getPrpricenetto());
            LOGGER.log(Level.INFO, "currentProduct desc: " + currentProduct.getPrcomment());
            LOGGER.log(Level.INFO, "currentProduct type: " + currentProduct.getPcatenum());
            
    
            productData.updateProduct(currentProduct);
            FacesMessage msg = new FacesMessage("Product Edited", currentProduct.getPrname() + " has been edited");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Error saving product", ex);
        }
    }


    public ProductCategory getCategory(int id) {
        if (categoryList == null) {
            // Handle this error as you see fit, perhaps load the category list
            return null;
        }

        for (int i = 0; i < categoryList.size(); i++) {
            ProductCategory category = categoryList.get(i);
            if (category.getId() == id) {
                return category;
            }
        }

        return null; // Return null if no matching category is found
    }

    //Getter und Setter
    public List<Product> getProductDataList() {
        return productDataList;
    }

    public void setProductDataList(List<Product> productDataList) {
        this.productDataList = productDataList;
    }

    public List<ProductCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ProductCategory> catgoryList) {
        this.categoryList = categoryList;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

}
