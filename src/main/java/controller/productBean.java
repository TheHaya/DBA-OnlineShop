/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.RowEditEvent;

import newModel.ProductCategory;
import newModel.Product;
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

    private List<Product> productDataList;
    private List<ProductCategory> categoryList;
    private String sortOrder = "idAsc";

    @Inject
    private sqlBean productData;
    
    
    // Laden der Produkte sowie Produktkategorien
    @PostConstruct
    public void init(){
            productDataList = productData.findAllProducts();
            categoryList = productData.findCategories();
    }

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
    
    // Das geanderte Produkt wird zum Speichern in der DB an die SQL-Bean weitergegen
    // Gibt eine Growl Bestaetigung aus wenn der Benutzer ein Produkt aendert
    public void saveCurrentProduct(Product currentProduct) {
        productData.updateProduct(currentProduct);
        FacesMessage msg = new FacesMessage("Product Edited", currentProduct.getPrname() + " has been edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
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
