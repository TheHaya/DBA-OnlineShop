package controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

import util.sqlBean;

@Named(value = "adminInfo")
@RequestScoped
public class AdminInfo implements Serializable {

    private List<ProductInfo> productInfo;
    private List<UserInfo> userInfo;
    @Inject
    private sqlBean sqlBean;
    private String selectedDataType;
    private String selectedUserInfoType;

    @PostConstruct
    public void init() {
        refreshData();  
    }
    
    public void loadProductInfo() {
        if (selectedDataType.equals("bestseller")) {
            productInfo = sqlBean.findBestsellers();
        } else if (selectedDataType.equals("lowSelling")) {
            productInfo = sqlBean.findLeastSoldProducts();
        }
    }
    
    public void loadUserInfo() {
        if (selectedUserInfoType.equals("bestCustomers")) {
            userInfo = sqlBean.findBestCustomers();
        }
        else if(selectedUserInfoType.equals("worstCustomers")) {
            userInfo = sqlBean.findInactiveCustomers();
        }
    }
    
    // Getter and Setter
    public List<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    public void refreshData() {
        productInfo = sqlBean.findBestsellers();
        userInfo = sqlBean.findBestCustomers();
    }

    public String getSelectedDataType() {
        return selectedDataType;
    }

    public void setSelectedDataType(String selectedDataType) {
        this.selectedDataType = selectedDataType;
    }

    public String getSelectedUserInfoType() {
        return selectedUserInfoType;
    }

    public void setSelectedUserInfoType(String selectedUserInfoType) {
        this.selectedUserInfoType = selectedUserInfoType;
    }
}
