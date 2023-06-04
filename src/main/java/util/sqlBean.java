/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;

import controller.cartBean;
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
import model.Account;
import model.product;
import model.user;
import model.Customer;
import model.Orders;
import model.ProductCategory;
import model.ProductInfo;
import model.UserInfo;

/**
 * SqlBean handles the database operations using SQL queries and statements. It
 * provides methods to retrieve and manipulate data from the database.
 */
@Named(value = "sqlBean")
@SessionScoped
public class sqlBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(sqlBean.class.getName());
    private Connection conn = null;
//    private List<user> userList = new ArrayList<>();
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
            LOGGER.log(Level.SEVERE, "Error while establishing the database connection", ex);
        }
    }

//    /**
//     * Retrieves a list of users from the database.
//     *
//     * @return List of User objects
//     */
//    public List<user> getUserList() {
//
//        try {
//            String sql = "SELECT C.CFIRSTNAME, C.CFAMNAME, C.CEMAIL, C.CID, A.ACCTYPE, A.ACCPWD, A.ACCNAME "
//                    + "FROM Customer C "
//                    + "JOIN Account A ON C.FK_ACCID = A.ACCID";
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                String username = resultSet.getString("ACCNAME");
//                String password = resultSet.getString("ACCPWD");
//                String email = resultSet.getString("CEMAIL");
//                String firstName = resultSet.getString("CFIRSTNAME");
//                String famName = resultSet.getString("CFAMNAME");
//                int userID = resultSet.getInt("CID");
//                int rights = resultSet.getInt("ACCTYPE");
//
//                user user = new user(username, password, email, firstName, famName, userID, rights);
//                userList.add(user);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return userList;
//    }
    public void insertCustomer(Customer customer) {
        try {

            conn.setAutoCommit(false); // Disable autocommit

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
            String customerSql = "INSERT INTO Customer (FK_ACCID, CEMAIL, CFIRSTNAME, CFAMNAME, CSALUTATION, CPHONE, CBIRTHDATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
            //String customerSql = "INSERT INTO Customer (FK_ACCID, CEMAIL, CFIRSTNAME, CFAMNAME) VALUES (?, ?, ?, ?)";
            PreparedStatement customerStatement = conn.prepareStatement(customerSql);
            customerStatement.setInt(1, accountID);
            customerStatement.setString(2, customer.getemail());
            customerStatement.setString(3, customer.getfirstname());
            customerStatement.setString(4, customer.getlastname());
            customerStatement.setString(5, customer.getsalutation());
            customerStatement.setString(6, customer.getphone());
            customerStatement.setDate(7, new java.sql.Date(customer.getbirthdate().getTime()));;
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

            conn.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while inserting customer into the database", ex);

            try {
                conn.rollback(); // Rollback the transaction in case of an exception
            } catch (SQLException rollbackEx) {
                LOGGER.log(Level.SEVERE, "Error while rolling back the transaction", rollbackEx);
            }
        } finally {
            try {
                conn.setAutoCommit(true); // Enable autocommit
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Error while enabling autocommit", ex);
            }
        }
    }

    public void insertCheckout(Orders order, Customer customer, cartBean cart) {
        try {
            conn.setAutoCommit(false);
            if (customer == null) {
                LOGGER.log(Level.SEVERE, "Customer object is null");
                return;
            }

//            if (customer != null) {
//                LOGGER.log(Level.SEVERE, "Customer ID IS" + customer.getCid() + " END", customer.getCid());
//            return;
//            }
            // Insert into orders table
            String ordersSql = "INSERT INTO orders (FK_CID, OSTATUS, ODELDATE, OCOMMENT) VALUES (?, ?, ?, ?)";
            PreparedStatement ordersStatement = conn.prepareStatement(ordersSql);
            ordersStatement.setInt(1, customer.getCid());
            ordersStatement.setString(2, "Pending");
            ordersStatement.setObject(3, order.getDelDate());
            ordersStatement.setString(4, "Order #");
            ordersStatement.executeUpdate();

            // Get the generated order ID
            int orderID;
            String orderIDSql = "SELECT LAST_INSERT_ID()";
            PreparedStatement orderIDStatement = conn.prepareStatement(orderIDSql);
            try (ResultSet orderIDResultSet = orderIDStatement.executeQuery()) {
                if (orderIDResultSet.next()) {
                    orderID = orderIDResultSet.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated order ID.");
                }
            }

            if (cart == null) {
                LOGGER.log(Level.SEVERE, "Cart is null");
                return;
            }

            for (product p : cart.getCart()) {
                String orderDetailsSql = "INSERT INTO orderdetail (FK_OID, FK_PRID, ODAMOUNT) VALUES (?, ?, ?)";
                PreparedStatement orderDetailsStatement = conn.prepareStatement(orderDetailsSql);
                orderDetailsStatement.setInt(1, orderID);
                orderDetailsStatement.setInt(2, p.getProdID());
                orderDetailsStatement.setInt(3, p.getProdQuant());
                orderDetailsStatement.executeUpdate();
            }
            conn.commit();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while inserting checkout into the database", ex);

            try {
                conn.rollback(); // Rollback the transaction in case of an exception
            } catch (SQLException rollbackEx) {
                LOGGER.log(Level.SEVERE, "Error while rolling back the transaction", rollbackEx);
            }
        } finally {
            try {
                conn.setAutoCommit(true); // Enable autocommit
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Error while enabling autocommit", ex);
            }
        }
    }

    public List<product> getProductList() {

        try {
            String sql = "SELECT PRNAME, PCATENUM, PRCOMMENT, PRIMAGEPATH, PRPRICENETTO, PRID FROM Product";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String prodName = resultSet.getString("PRNAME");
                ProductCategory prodType = new ProductCategory( resultSet.getString("PCATENUM"));
                String prodDesc = resultSet.getString("PRCOMMENT");
                String prodPic = resultSet.getString("PRIMAGEPATH");
                double prodPrice = resultSet.getDouble("PRPRICENETTO");
                int prodID = resultSet.getInt("PRID");

                product prod = new product(prodName, prodType, prodDesc, prodPic, prodPrice, prodID);
                productList.add(prod);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while retrieving product list from the database", ex);
        }

        return productList;
    }

    public void updateProduct(product product) {
        try {
            String sql = "UPDATE Product SET PRNAME = ?, PRCOMMENT = ?, PRPRICENETTO = ?, PCATENUM = ? WHERE PRID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, product.getProdName());
            statement.setString(2, product.getProdDesc());
            statement.setDouble(3, product.getProdPrice());
            statement.setString(4, product.getProdType().getCategoryName());
            statement.setInt(5, product.getProdID());
            statement.executeUpdate();
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while updating Product in the database", ex);
        }
    }

    public boolean findAccName(String accountname) {
        try {
            String sql = "SELECT COUNT(*) FROM Account WHERE ACCNAME = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, accountname);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving AccountName from the database", ex);
        }
        return false;
    }

    public boolean findEmail(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM Customer WHERE CEMAIL = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving Email from the database", ex);
        }
        return false;
    }

    public boolean findPhone(String phone) {
        try {
            String sql = "SELECT COUNT(*) FROM Customer WHERE CPHONE = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, phone);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving Phone from the database", ex);
        }
        return false;
    }

    public Customer getCustomer(String username, String password) {
        try {
            String sql = "SELECT * FROM Customer C JOIN Account A ON C.FK_ACCID = A.ACCID WHERE A.ACCNAME = ? AND A.ACCPWD = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Customer customer = new Customer();
                Account account = new Account();
                customer.setaccount(account);
                customer.getaccount().setAccountname(resultSet.getString("ACCNAME"));
                customer.getaccount().setPassword(resultSet.getString("ACCPWD"));
                customer.getaccount().setRights(resultSet.getInt("ACCTYPE"));

                customer.setCid(resultSet.getInt("CID"));

                customer.setemail(resultSet.getString("CEMAIL"));
                customer.setfirstname(resultSet.getString("CFIRSTNAME"));
                customer.setlastname(resultSet.getString("CFAMNAME"));
                customer.setsalutation(resultSet.getString("CSALUTATION"));
                customer.setphone(resultSet.getString("CPHONE"));
                customer.setbirthdate(resultSet.getDate("CBIRTHDATE"));

                return customer;
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving customer list from the database", ex);
        }

        return null;
    }

    public List<ProductInfo> getBestsellers() {
        List<ProductInfo> bestsellers = new ArrayList<>();

        try {
            String sql = "SELECT p.PRNAME AS ProductName, SUM(od.ODAMOUNT) AS TotalAmount, p.PCATENUM AS Category "
                    + "FROM Product p "
                    + "JOIN OrderDetail od ON od.FK_PRID = p.PRID "
                    + "JOIN Orders o ON o.OID = od.FK_OID "
                    + "GROUP BY p.PRNAME, p.PCATENUM "
                    + "HAVING TotalAmount > 0 " // Filter für Artikel mit mindestens einem Verkauf
                    + "ORDER BY TotalAmount DESC "
                    + "LIMIT 5";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String productName = resultSet.getString("ProductName");
                int totalAmount = resultSet.getInt("TotalAmount");
                String category = resultSet.getString("Category");

                ProductInfo bestseller = new ProductInfo(productName, totalAmount, category);
                bestsellers.add(bestseller);
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving bestseller list from the database", ex);
        }

        return bestsellers;
    }

    public List<ProductInfo> getLeastSoldProducts() {
        List<ProductInfo> leastSoldProducts = new ArrayList<>();

        try {
            String sql = "SELECT p.PRNAME AS ProductName, SUM(od.ODAMOUNT) AS TotalAmount, p.PCATENUM AS Category "
                    + "FROM Product p "
                    + "LEFT JOIN OrderDetail od ON od.FK_PRID = p.PRID "
                    + "GROUP BY p.PRNAME, p.PCATENUM "
                    + "HAVING TotalAmount > 0 " // Filter für Artikel mit mindestens einem Verkauf
                    + "ORDER BY TotalAmount ASC "
                    + "LIMIT 5";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String productName = resultSet.getString("ProductName");
                int totalAmount = resultSet.getInt("TotalAmount");
                String category = resultSet.getString("Category");

                ProductInfo product = new ProductInfo(productName, totalAmount, category);
                leastSoldProducts.add(product);
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving least sold product list from the database", ex);
        }

        return leastSoldProducts;
    }

    public List<UserInfo> getUserInfoList() {
        List<UserInfo> userInfoList = new ArrayList<>();

        try {
            String sql = "SELECT c.CFAMNAME AS LastName, c.CFIRSTNAME AS FirstName, COUNT(DISTINCT o.OID) AS OrdersAmount, c.CID "
                    + "FROM Customer c "
                    + "LEFT JOIN Orders o ON o.FK_CID = c.CID "
                    + "LEFT JOIN OrderDetail od ON od.FK_OID = o.OID "
                    + "LEFT JOIN Product p ON p.PRID = od.FK_PRID "
                    + "WHERE YEAR(o.ODELDATE) = YEAR(CURRENT_DATE() - INTERVAL 2 MONTH) "
                    + "GROUP BY c.CID "
                    + "HAVING OrdersAmount > 1 "
                    + "ORDER BY OrdersAmount DESC";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String lastName = resultSet.getString("LastName");
                String firstName = resultSet.getString("FirstName");
                int cid = resultSet.getInt("CID");
                int ordersAmount = resultSet.getInt("OrdersAmount");
                double totalRevenue = getRevenue(cid);

                UserInfo userInfo = new UserInfo(firstName + " " + lastName, ordersAmount, totalRevenue);
                userInfoList.add(userInfo);
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving best customer list from the database", ex);
        }

        return userInfoList;
    }

    public List<UserInfo> getInactiveCustomers() {
        List<UserInfo> inactiveCustomers = new ArrayList<>();

        try {
            String sql = "SELECT c.CID, c.CFAMNAME AS LastName, c.CFIRSTNAME AS FirstName, COUNT(o.OID) AS OrdersAmount "
                    + "FROM Customer c "
                    + "LEFT JOIN Orders o ON o.FK_CID = c.CID "
                    + "LEFT JOIN OrderDetail od ON od.FK_OID = o.OID "
                    + "LEFT JOIN Product p ON p.PRID = od.FK_PRID "
                    + "WHERE YEAR(o.ODELDATE) <> YEAR(CURRENT_DATE()) OR o.ODELDATE IS NULL "
                    + "GROUP BY c.CID, LastName, FirstName";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int cid = resultSet.getInt("CID");
                String lastName = resultSet.getString("LastName");
                String firstName = resultSet.getString("FirstName");
                int ordersAmount = resultSet.getInt("OrdersAmount");
                double totalRevenue = getRevenue(cid);

                UserInfo userInfo = new UserInfo(firstName + " " + lastName, ordersAmount, totalRevenue);
                inactiveCustomers.add(userInfo);
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving worst customer list from the database", ex);
        }

        return inactiveCustomers;
    }

    public double getRevenue(int cid) {
        double totalRevenue = 0.0;

        try {
            String sql = "SELECT SUM(p.PRPRICENETTO * od.ODAMOUNT) AS TotalPrice "
                    + "FROM Customer c "
                    + "LEFT JOIN Orders o ON o.FK_CID = c.CID "
                    + "LEFT JOIN OrderDetail od ON od.FK_OID = o.OID "
                    + "LEFT JOIN Product p ON p.PRID = od.FK_PRID "
                    + "WHERE c.CID = ? "
                    + "AND YEAR(o.ODELDATE) = YEAR(CURRENT_DATE()) "
                    + "GROUP BY c.CID, o.OID "
                    + "HAVING COUNT(o.OID) > 1 "
                    + "ORDER BY COUNT(o.OID) DESC, o.OID";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cid);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                double revenue = resultSet.getDouble("TotalPrice");
                totalRevenue += revenue;
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving totalPrice from the database", ex);
        }

        return totalRevenue;
    }

    public List<ProductCategory> getCategories() {
        List<ProductCategory> categories = new ArrayList<>();
        String query = "SELECT DISTINCT PCATENUM FROM product";

        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String categoryName = rs.getString("PCATENUM");

                ProductCategory productCategory = new ProductCategory(categoryName);
                categories.add(productCategory);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error while retrieving category list from the database", ex);
        }

        return categories;
    }

//    public ProductCategory getCategory(String categoryName) {
//        String query = "SELECT DISTINCT PCATENUM FROM product WHERE PCATENUM = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, categoryName);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String name = rs.getString("PCATENUM");
//                    return new ProductCategory(name);
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
    
    public List<UserInfo> getAllUsers() {
        List<UserInfo> allUserList = new ArrayList<>();

        try {
            String sql = "SELECT c.CFAMNAME AS LastName, c.CFIRSTNAME AS FirstName, COUNT(DISTINCT o.OID) AS OrdersAmount, c.CID "
                    + "FROM Customer c "
                    + "LEFT JOIN Orders o ON o.FK_CID = c.CID "
                    + "LEFT JOIN OrderDetail od ON od.FK_OID = o.OID "
                    + "LEFT JOIN Product p ON p.PRID = od.FK_PRID "
                    + "GROUP BY c.CID "
                    + "ORDER BY OrdersAmount DESC";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String lastName = resultSet.getString("LastName");
                String firstName = resultSet.getString("FirstName");
                int cid = resultSet.getInt("CID");
                int ordersAmount = resultSet.getInt("OrdersAmount");
                double totalRevenue = getRevenue(cid);

                UserInfo userInfo = new UserInfo(firstName + " " + lastName, ordersAmount, totalRevenue);
                allUserList.add(userInfo);
            }
        } catch (SQLException ex) {
             LOGGER.log(Level.SEVERE, "Error while retrieving best customer list from the database", ex);
        }

        return allUserList;
    }
    
    public Connection getConn() {
        return conn;
    }
}
