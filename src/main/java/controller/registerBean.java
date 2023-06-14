/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import newModel.Account;
import newModel.Address;
import newModel.Customer;
import util.sqlBean;

/**
 *
 * Die registerBean kümmert sich hauptsächlich um Registrierungsangelegenheiten
 * von noch nicht kreierten Benutzerkonten.
 *
 */
@Named(value = "registerBean")
@SessionScoped
public class registerBean implements Serializable {

    private String accountname;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String salutation;
    private String phone;
    private String street;
    private String fedstate;
    private String citycode;
    private String country;
    private java.util.Date birthdate;
    private int rights;
    private Customer customer;
    private Account account;
    private Address address;
    private FacesContext context;

    @Inject                             //Inject damit sich Registrierung in der dataBean speichert
    private sqlBean registerData;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
    }

    public registerBean() {
        
    }

    // Registrierungsfunktion für Account
    public String createAccount() {

        // Create new account object
        Account newAccount = new Account();


        account = newAccount;

        return "register2.xhtml";
    }

    // Registrierungsfunktion zur Erstellung des Kunden und Abschluss der Registrierung
    public String createCustomer() {

        // Create new account object
        Customer newCustomer = new Customer();

        customer = newCustomer;
        return "register3.xhtml";
    }

    public String completeRegistration() {
        context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;

        Address newAddress = new Address();
        Collection<Address> addressCollection = new ArrayList<>();
        addressCollection.add(newAddress);
        customer.setAddressCollection(addressCollection);
        registerData.persistCustomer(customer);
        facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful", "Welcome to our shop!");
        context.addMessage(null, facesMessage);

        return "index.xhtml";
    }

    // Getter and Setter
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFedstate() {
        return fedstate;
    }

    public void setFedstate(String fedState) {
        this.fedstate = fedState;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public java.util.Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(java.util.Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String username) {
        this.accountname = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public sqlBean getRegisterData() {
        return registerData;
    }

    public void setRegisterData(sqlBean registerData) {
        this.registerData = registerData;
    }
}
