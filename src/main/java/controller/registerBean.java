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
import java.util.List;
import model.user;
import util.dataBean;

/**
 *
 * @author Haya
 * 
 * Die registerBean kümmert sich hauptsächlich um Registrierungsangelegenheiten von noch nicht kreierten Benutzerkonten.
 * 
 */
@Named(value = "registerBean")
@SessionScoped
public class registerBean implements Serializable {

    private String username;
    private String password;
    private String email;
    private int rights;
    private List<user> userDataList;
    private user newUser;
    private FacesContext context;
    
    @Inject                             //Inject damit sich Registrierung in der dataBean speichert
    private dataBean registerData;
    
    @PostConstruct
    public void init(){
        context = FacesContext.getCurrentInstance();
        userDataList = registerData.getUserList();
    }
    
    public registerBean() {
        registerData = new dataBean();
    }

    // Registrierungsfunktion
    // Es wird gecheckt ob der Benutzername oder die Email schon vergeben wurden und gibt dementsprechend Error Growls zurück
    // und der User wird wieder auf die leere Registerseite refreshed.
    // Ist dies aber nicht der fall, werden die Eingabedaten als neues Benutzerkonto auf die dataBean Liste addiert. 
    public String register(){
        context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage;
        int id = registerData.getNextUserID();
        String curName = username;
        String curEmail = email;
        String following = "index.xhtml";
        
        newUser = new user(username, password, email, id, rights);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setUserID(id);
        newUser.setRights(2);
        
        for(user u : this.userDataList) {
            if(u.getUsername().equals(curName)){
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "","Username is already taken");
                context.addMessage(null, facesMessage);
                return "register.xhtml";
            }
            if(u.getEmail().equals(curEmail)){
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "","E-Mail is already in use");
                context.addMessage(null, facesMessage);
                return "register.xhtml";
            }
        }
        registerData.addUser(newUser);
        facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Registration successful","Welcome to our shop!");
        context.addMessage(null, facesMessage);
        return following;
    }
        
    public void resetInput() {
    username = "";
    email = "";
    }
    
    //Getter und Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<user> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<user> userDataList) {
        this.userDataList = userDataList;
    }

    public int getRights() {
        return rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }
    
    
    
    
}
