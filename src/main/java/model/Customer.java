package model;

import java.util.Date;

public class Customer {

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String salutation;
    private String phone;
    private Date birthdate;
    private Address address;
    private Account account;
    private int cid;
    
    public Customer(){};

    public Customer(String username, String password, String email, String firstname, String lastname, String salutation, String phone, Date birthdate, Address address, Account account) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salutation = salutation;
        this.phone = phone;
        this.birthdate = birthdate;
        this.address = address;
        this.account = account;
    }

    public Customer(String username, String password, String email, String firstname, String lastname, String salutation, String phone, Date birthdate, Account account) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salutation = salutation;
        this.phone = phone;
        this.birthdate = birthdate;
        this.account = account;
    }
    public Customer(int cid){
        this.cid = cid;
    }
    
    public Customer(int cid, String username, String password, String email, String firstname, String lastname, String salutation, String phone, Date birthdate, Account account) {
        this.cid = cid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salutation = salutation;
        this.phone = phone;
        this.birthdate = birthdate;
        this.account = account;
    }
    
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
    
    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getsalutation() {
        return salutation;
    }

    public void setsalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public Date getbirthdate() {
        return birthdate;
    }

    public void setbirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Address getaddress() {
        return address;
    }

    public void setaddress(Address address) {
        this.address = address;
    }

    public Account getaccount() {
        return account;
    }

    public void setaccount(Account account) {
        this.account = account;
    }
}
