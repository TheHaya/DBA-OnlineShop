/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wrede
 */
public class Address {

    private String street;
    private String fedState;
    private String cityCode;
    private String country;

    public Address(String street, String fedState, String cityCode, String country) {
        this.street = street;
        this.fedState = fedState;
        this.cityCode = cityCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFedState() {
        return fedState;
    }

    public void setFedState(String fedState) {
        this.fedState = fedState;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
