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
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import model.user;
import util.dataBean;
import util.sqlBean;


/**
 *
 * @author Haya
 * 
 * Die loginBean beinhaltet die meisten Funktionen, die mit dem Login zusammenhängen.
 * Sie checkt, ob die Eingabedaten des Benutzers mit den Daten aus einer DB übereinstimmen und schaut auch noch ob die
 * Person Adminrechte besitzt.
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {
    //object of user currently logged in
    private user loggedInUser;
    
    private boolean loginCheck = false;
    private String loginName;
    private String loginPass;
    private FacesContext context;
    private boolean check;
    private List<user> userDataList;
    private HttpSession session;
    private boolean adminRights;
    @Inject
    private sqlBean loginData;
    


    
    @PostConstruct
    public void init(){
        context = FacesContext.getCurrentInstance();
        userDataList = loginData.getUserList();
        // Typecast sonst ClassCastException, Context wegen Servlet, wenn getSession true 
        // dann wird eine Session kreiert und eine Instanz zurückgegeben
        session = (HttpSession) context.getExternalContext().getSession(false);
    }
    
    public loginBean() {
        loginData = new sqlBean();
    }
    
    /* Aufgrund von neuem Login screen unbenutzt, vorher eine eigene Seite, nun ein Modal Dialog
     
    // Login Funktion, wird beim Login button aufgerufen
    // Schaut ob die eingegebenen Daten (Benutzername und Passwort) im Userinput mit einem Eintrag
    // in der (hier hard-coded) Datenbank  übereinstimmt
    public String login() {
        FacesMessage facesMessage;
        context = FacesContext.getCurrentInstance();
        check = false;
        String following = "index.xhtml";
        userDataList = loginData.getUserList();
        
        for(user u : userDataList) {
            if(u.getUsername().equals(loginName)
                    &&u.getPassword().equals(loginPass)){
                check = true;
                if(u.getRights() <= 1){         // Checkt ob der eingeloggte Benutzer Admin Rechte besitzt.    
                    adminRights = true;            
                }
                break;
            }
        }
        if(check == true){
            loginCheck = true;
            
            getSession().setAttribute("username", loginName);
            //Nachricht an Inputkomponente für richtiges Passwort
            //Validierungsmessage
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Login Successful", "Welcome " + loginName + "!");
            //FacesMessage an bestimmte Client Identifier wenn nicht null
            context.addMessage(null,facesMessage);
        }
        else {
            //Nachricht an Inputkomponente für falsche Anmeldeinformationen
            //Validierungsmessage
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Invalid", "Username or Password invalid");
            //FacesMessage an bestimmte Client Identifier wenn nicht null
            context.addMessage(null, facesMessage);
            following = null;
        }
        return following;
    }
    */
     
    
    
    // Login Funktion, wird beim Login button in einem Modal Dialogfenster aufgerufen
    // Schaut ob die eingegebenen Daten (Benutzername und Passwort) im Userinput mit einem Eintrag
    // in der (hier hard-coded) Datenbank  übereinstimmt
    public void login2() {
        FacesMessage facesMessage;
        context = FacesContext.getCurrentInstance();
        check = false;
        userDataList = loginData.getUserList();
        
        for(user u : userDataList) {
            if(u.getUsername().equals(loginName)
                    &&u.getPassword().equals(loginPass)){
                check = true;
                if(u.getRights() <= 1){
                    adminRights = true;
                }
                loggedInUser = u;
                break;
            }
        }
        if(check == true){
            loginCheck = true;
            
            getSession().setAttribute("username", loginName);
            //Nachricht an Inputkomponente für richtiges Passwort
            //Validierungsmessage
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Login Successful", "Welcome " + loginName + "!");
            //FacesMessage an bestimmte Client Identifier wenn nicht null
            context.addMessage(null,facesMessage);
        }
        else {
            //Nachricht an Inputkomponente für falsche Anmeldeinformationen
            //Validierungsmessage
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Invalid", "Username or Password invalid");
            //FacesMessage an bestimmte Client Identifier wenn nicht null
            context.addMessage(null, facesMessage);
        }
    }
    
    // Logout Funktion
    // Die Session wird beim Logout beendet und der User wird auf die Homepage geleitet.
    public String logout(){
        String following = "index.xhtml";
        context = FacesContext.getCurrentInstance();
        
        context.getExternalContext().invalidateSession();
        context.getExternalContext().getSession(true);
        
        adminRights = false;
        loginCheck = false;
        return following;
    }
    
    // Leert das InputText-Feld wenn das Dialogfenster mit Input geschlossen wird
    public void resetInput() {
    loginName = "";
    }
    
    
    // Getter und Setter
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getLoginCheck() {
        return loginCheck;
    }

    public void setLoginCheck(boolean loginCheck) {
        this.loginCheck = loginCheck;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public boolean isAdminRights() {
        return adminRights;
    }

    public void setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
    }
    
    
}
