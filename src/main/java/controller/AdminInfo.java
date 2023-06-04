package controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import model.ProductInfo;
import model.UserInfo;
import util.sqlBean;

@Named(value = "adminInfo")
@RequestScoped
public class AdminInfo implements Serializable {

    private List<ProductInfo> productInfo;
    private List<UserInfo> userInfo;
    private sqlBean sqlBean;
    private String selectedDataType;
    private String selectedUserInfoType;

    @PostConstruct
    public void init() {
        sqlBean = new sqlBean();
        refreshData();
    }

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
        productInfo = sqlBean.getBestsellers();
        userInfo = sqlBean.getUserInfoList();
    }

    public String getSelectedDataType() {
        return selectedDataType;
    }

    public void setSelectedDataType(String selectedDataType) {
        this.selectedDataType = selectedDataType;
    }

    public void loadProductInfo() {
        if ("bestseller".equals(selectedDataType)) {
            productInfo = sqlBean.getBestsellers();
        } else if ("lowSelling".equals(selectedDataType)) {
            productInfo = sqlBean.getLeastSoldProducts();
        }
    }

    public String getSelectedUserInfoType() {
        return selectedUserInfoType;
    }

    public void setSelectedUserInfoType(String selectedUserInfoType) {
        this.selectedUserInfoType = selectedUserInfoType;
    }

    public void loadUserInfo() {
        if (null != selectedUserInfoType) switch (selectedUserInfoType) {
            case "allCustomers":
                userInfo = sqlBean.getAllUsers();
                break;
            case "bestCustomers":
                userInfo = sqlBean.getUserInfoList();
                break;
            case "worstCustomers":
                userInfo = sqlBean.getInactiveCustomers();
                break;
            default:
                break;
        
        }
        
    }

}
