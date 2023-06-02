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
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Account;
import model.Address;
import model.Customer;
import model.user;
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
    private java.sql.Date birthdate;
    private int rights;
    private List<user> userDataList;
    private user newUser;
    private Customer customer;
    private Account account;
    private Address address;
    private FacesContext context;

    @Inject                             //Inject damit sich Registrierung in der dataBean speichert
    private sqlBean registerData;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        userDataList = registerData.getUserList();
    }

    public registerBean() {
        registerData = new sqlBean();
    }

    // Registrierungsfunktion für Account
    public String createAccount() {
        context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;

        // Create new account object
        Account newAccount = new Account(accountname, password);

        // Check if username or email is already taken
        for (user u : this.userDataList) {
            if (u.getUsername().equals(accountname)) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Username is already taken");
                context.addMessage(null, facesMessage);
                return "register.xhtml";
            }
        }

        account = newAccount;

        return "register2.xhtml";
    }

    // Registrierungsfunktion zur Erstellung des Kunden und Abschluss der Registrierung
    public String createCustomer() {
        context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;

        // Create new account object
        Customer newCustomer = new Customer(accountname, password, email, firstname, lastname, salutation, phone, birthdate, account);

        // Check if username or email is already taken
        for (user u : this.userDataList) {
            if (u.getEmail().equals(email)) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email is already taken");
                context.addMessage(null, facesMessage);
                return "register.xhtml";
            }
        }
        customer = newCustomer;
        return "register3.xhtml";
    }

    public String completeRegistration() {
        context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;

        Address newAddress = new Address(street, fedstate, citycode, country);
        customer.setaddress(newAddress);
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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

    public List<user> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<user> userDataList) {
        this.userDataList = userDataList;
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

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
// */
//package controller;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.inject.Named;
//import jakarta.enterprise.context.SessionScoped;
//import jakarta.faces.application.FacesMessage;
//import jakarta.faces.context.FacesContext;
//import jakarta.inject.Inject;
//import java.io.Serializable;
//import java.util.List;
//import model.user;
//import util.dataBean;
//import util.sqlBean;
//
///**
// *
// * @author Haya
// * 
// * Die registerBean kümmert sich hauptsächlich um Registrierungsangelegenheiten von noch nicht kreierten Benutzerkonten.
// * 
// */
//@Named(value = "registerBean")
//@SessionScoped
//public class registerBean implements Serializable {
//
//    private String username;
//    private String password;
//    private String email;
//    private int rights;
//    private List<user> userDataList;
//    private user newUser;
//    private FacesContext context;
//    
//    @Inject                             //Inject damit sich Registrierung in der dataBean speichert
//    private sqlBean registerData;
//    
//    @PostConstruct
//    public void init(){
//        context = FacesContext.getCurrentInstance();
//        userDataList = registerData.getUserList();
//    }
//    
//    public registerBean() {
//        registerData = new sqlBean();
//    }
//
//    // Registrierungsfunktion
//    // Es wird gecheckt ob der Benutzername oder die Email schon vergeben wurden und gibt dementsprechend Error Growls zurück
//    // und der User wird wieder auf die leere Registerseite refreshed.
//    // Ist dies aber nicht der fall, werden die Eingabedaten als neues Benutzerkonto auf die dataBean Liste addiert. 
//    public String register(){
//        context = FacesContext.getCurrentInstance();
//        FacesMessage facesMessage;
//        String curName = username;
//        String curEmail = email;
//        String following = "index.xhtml";
//        
//        newUser = new user(username, password, email, rights);
//        newUser.setUsername(username);
//        newUser.setPassword(password);
//        newUser.setEmail(email);
//        
//        for(user u : this.userDataList) {
//            if(u.getUsername().equals(curName)){
//                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
//                        "","Username is already taken");
//                context.addMessage(null, facesMessage);
//                return "register.xhtml";
//            }
//            if(u.getEmail().equals(curEmail)){
//                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
//                        "","E-Mail is already in use");
//                context.addMessage(null, facesMessage);
//                return "register.xhtml";
//            }
//        }
//        registerData.insertUser(newUser);
//        facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, 
//                "Registration successful","Welcome to our shop!");
//        context.addMessage(null, facesMessage);
//        return following;
//    }
//        
//    public void resetInput() {
//    username = "";
//    email = "";
//    }
//    
//    //Getter und Setter
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public List<user> getUserDataList() {
//        return userDataList;
//    }
//
//    public void setUserDataList(List<user> userDataList) {
//        this.userDataList = userDataList;
//    }
//
//    public int getRights() {
//        return rights;
//    }
//
//    public void setRights(int rights) {
//        this.rights = rights;
//    }
//    
//    
//    
//    
//}
