/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wrede
 */
public class ProductCategory {

    private int id;
    private String categoryName;

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
        if ("Pizza".equals(categoryName)) {
            id = 0;
        }
        if ("Ice Cream".equals(categoryName)) {
            id = 1;
        }
        if ("Cake".equals(categoryName)) {
            id = 2;
        }
        if ("Fruit".equals(categoryName)) {
            id = 3;
        }
        if ("Fish".equals(categoryName)) {
            id = 4;
        }
        if ("empty".equals(categoryName)) {
            id = 99;
        }
    }

    public ProductCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName; // Rückgabe des Kategorienamens als Anzeigewert
    }
}
