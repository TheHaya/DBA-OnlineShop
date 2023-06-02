/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;

import java.io.Serializable;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.product;
import model.user;
import model.Customer;

/**
 * SqlBean handles the database operations using SQL queries and statements. It
 * provides methods to retrieve and manipulate data from the database.
 */
@Named(value = "sqlBean")
@SessionScoped
public class sqlBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(sqlBean.class.getName());
    private Connection conn = null;
    private List<user> userList = new ArrayList<>();
    private List<product> productList = new ArrayList<>();

    /**
     * Establishes the JDBC database connection.
     */
    public sqlBean() {
        try {
            String driver = "org.mariadb.jdbc.Driver";
            Class.forName(driver); // Register the DB driver
            String dbUrl = "jdbc:mariadb://localhost:3306/onlineshop";
            conn = DriverManager.getConnection(dbUrl, "dba", "dba");
            LOGGER.info("Connection to the database established successfully!");
        } catch (ClassNotFoundException | SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves a list of users from the database.
     *
     * @return List of User objects
     */
    public List<user> getUserList() {

        try {
            String sql = "SELECT C.CFIRSTNAME, C.CFAMNAME, C.CEMAIL, C.CID, A.ACCTYPE, A.ACCPWD, A.ACCNAME "
                    + "FROM Customer C "
                    + "JOIN Account A ON C.FK_ACCID = A.ACCID";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("ACCNAME");
                String password = resultSet.getString("ACCPWD");
                String email = resultSet.getString("CEMAIL");
                String firstName = resultSet.getString("CFIRSTNAME");
                String famName = resultSet.getString("CFAMNAME");
                int userID = resultSet.getInt("CID");
                int rights = resultSet.getInt("ACCTYPE");

                user user = new user(username, password, email, firstName, famName, userID, rights);
                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userList;
    }

public void insertCustomer(Customer customer) {
    try {
        // Insert in Account table
        String accountSql = "INSERT INTO Account (ACCPWD, ACCNAME) VALUES (?, ?)";
        PreparedStatement accountStatement = conn.prepareStatement(accountSql);
        accountStatement.setString(1, customer.getpassword());
        accountStatement.setString(2, customer.getusername());
        accountStatement.executeUpdate();

        // Get the generated account ID
        int accountID;
        String accountIDSql = "SELECT LAST_INSERT_ID()";
        PreparedStatement accountIDStatement = conn.prepareStatement(accountIDSql);
        try (ResultSet accountIDResultSet = accountIDStatement.executeQuery()) {
            if (accountIDResultSet.next()) {
                accountID = accountIDResultSet.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve generated account ID.");
            }
        }

        // Insert in Customer table
        String customerSql = "INSERT INTO Customer (FK_ACCID, CEMAIL, CFIRSTNAME, CFAMNAME, CSALUTATION, CBIRTHDATE) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement customerStatement = conn.prepareStatement(customerSql);
        customerStatement.setInt(1, accountID);
        customerStatement.setString(2, customer.getemail());
        customerStatement.setString(3, customer.getfirstname());
        customerStatement.setString(4, customer.getlastname());
        customerStatement.setString(5, customer.getsalutation());
        customerStatement.setDate(6, customer.getbirthdate());
        customerStatement.executeUpdate();

        // Get the generated customer ID (CID)
        int customerID;
        String customerIDSql = "SELECT LAST_INSERT_ID()";
        PreparedStatement customerIDStatement = conn.prepareStatement(customerIDSql);
        try (ResultSet customerIDResultSet = customerIDStatement.executeQuery()) {
            if (customerIDResultSet.next()) {
                customerID = customerIDResultSet.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve generated customer ID.");
            }
        }

        // Insert in Address table
        String addressSql = "INSERT INTO Address (FK_CID, ASTREET, AFEDSTATE, ACITYCODE, ACOUNTRY) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement addressStatement = conn.prepareStatement(addressSql);
        addressStatement.setInt(1, customerID); // Use customerID as FK_CID
        addressStatement.setString(2, customer.getaddress().getStreet());
        addressStatement.setString(3, customer.getaddress().getFedState());
        addressStatement.setString(4, customer.getaddress().getCityCode());
        addressStatement.setString(5, customer.getaddress().getCountry());
        addressStatement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    public List<product> getProductList() {

        try {
            String sql = "SELECT PRNAME, PCATENUM, PRCOMMENT, PRIMAGEPATH, PRPRICENETTO, PRID, PRNO FROM Product";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String prodName = resultSet.getString("PRNAME");
                String prodType = resultSet.getString("PCATENUM");
                String prodDesc = resultSet.getString("PRCOMMENT");
                String prodPic = resultSet.getString("PRIMAGEPATH");
                double prodPrice = resultSet.getDouble("PRPRICENETTO");
                int prodID = resultSet.getInt("PRID");
                int prodQuant = resultSet.getInt("PRNO");

                product prod = new product(prodName, prodType, prodDesc, prodPic, prodPrice, prodID, prodQuant);
                productList.add(prod);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return productList;
    }

    public void updateProduct(product product) {
        try {
            String sql = "UPDATE Product SET PRNAME = ?, PRPRICENETTO = ? WHERE PRID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, product.getProdName());
            statement.setDouble(2, product.getProdPrice());
            statement.setInt(3, product.getProdID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }
}
