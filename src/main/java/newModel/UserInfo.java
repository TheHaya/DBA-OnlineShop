/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

import model.*;

/**
 *
 * @author wrede
 */
public class UserInfo {
    private String accName;
    private int ordersAmount;
    private double totalRevenue;

    public UserInfo(String accName, int ordersAmount, double totalRevenue) {
        this.accName = accName;
        this.ordersAmount = ordersAmount;
        this.totalRevenue = totalRevenue;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public int getOrdersAmount() {
        return ordersAmount;
    }

    public void setOrdersAmount(int ordersAmount) {
        this.ordersAmount = ordersAmount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}

