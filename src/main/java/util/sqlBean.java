/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import newModel.UserInfo;
import newModel.ProductInfo;
import newModel.CartItem;
import controller.cartBean;
import newModel.*;

/**
 * SqlBean handles the database operations using SQL queries and statements. It
 * provides methods to retrieve and manipulate data from the database.
 */
@Named(value = "sqlBean")
@RequestScoped
public class sqlBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(sqlBean.class.getName());
    private List<Product> productList = new ArrayList<>();

    @Resource
    private UserTransaction ut;

    @PersistenceContext
    private EntityManager em;

    public sqlBean() {

    }

    /*Funktion für die Registrieung
      fügt neu angelegte Objekte Customer, Account, Address in DB ein
      rollback im Fehlerfall
     */
    public void persistCustomer(Customer curCustomer) {
        try {
            ut.begin(); // Begin transaction

            // Insert in Account table
            Account account = new Account();
            account.setAccpwd(curCustomer.getFkAccid().getAccpwd());
            account.setAccname(curCustomer.getFkAccid().getAccname());
            account.setAcctype(1);
            em.persist(account);

            // Insert in Customer table
            Customer customer = new Customer();
            customer.setFkAccid(account);
            customer.setCemail(curCustomer.getCemail());
            customer.setCfirstname(curCustomer.getCfirstname());
            customer.setCfamname(curCustomer.getCfamname());
            customer.setCsalutation(curCustomer.getCsalutation());
            customer.setCphone(curCustomer.getCphone());
            customer.setCbirthdate(curCustomer.getCbirthdate());
            em.persist(customer);

            // Convert Collection to List
            List<Address> addressList = new ArrayList<>(curCustomer.getAddressCollection());

            // Get the first address
            Address firstAddress = addressList.get(0);

            // Set values
            Address address = new Address();
            address.setFkCid(customer);
            address.setAstreet(firstAddress.getAstreet());
            address.setAfedstate(firstAddress.getAfedstate());
            address.setAcitycode(firstAddress.getAcitycode());
            address.setAcountry(firstAddress.getAcountry());
            em.persist(address);

            ut.commit(); // Commit transaction
        } catch (Exception ex) {
            if (ut != null) {
                try {
                    ut.rollback(); // Rollback transaction in case of error
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Failed to rollback transaction");
                }
            }
            LOGGER.log(Level.SEVERE, "Persist Error in persist customer");
            ex.printStackTrace();
        }
    }

    /*Funktion für den Checkout
      erstellt für Product objekte in Warenkorb Orders und Orderdetail
      rollback im Fehlerfall
     */
    public void persistCheckout(Orders curOrder, Customer customer, cartBean cart) {
        try {
            ut.begin();
            // Insert into orders table
            Orders order = new Orders();
            order.setFkCid(customer);
            order.setOstatus("Pending");
            order.setOdeldate(curOrder.getOdeldate());
            order.setOcomment("Order #");

            for (CartItem item : cart.getCart()) {
                Product p = item.getProduct();
                int quantity = item.getQuantity();
                Orderdetail orderDetail = new Orderdetail();
                orderDetail.setFkOid(order);
                orderDetail.setFkPrid(p);
                orderDetail.setOdamount(quantity);
                em.persist(orderDetail);
            }

            em.persist(order);
            ut.commit();
        } catch (Exception ex) {
            if (ut != null) {
                try {
                    ut.rollback(); // Rollback transaction in case of error
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Failed to rollback transaction");
                }
            }
            LOGGER.log(Level.SEVERE, "Persist Error in Checkout");
            ex.printStackTrace();
        }
    }

    /*
    Füllt Produktliste für anzeige in Datatable auf Website
     */
    public List<Product> findAllProducts() {
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
            productList = query.getResultList();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving product list from database", ex);
            ex.printStackTrace();
        }
        return productList;
    }

    /*
    Check ob AccName bereits vorhanden ist
    Aufruf aus Validator-Klasse
     */
    public boolean findAccName(String accountname) {
        try {
            String jpql = "SELECT COUNT(a) FROM Account a WHERE a.accname = :accname";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("accname", accountname);
            long erg = query.getSingleResult();
            if (erg >= 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving account names", ex);
            ex.printStackTrace();
        }
        return false;
    }

    /*
    Check ob Email bereits vorhanden ist
    Aufruf aus Validator-Klasse
     */
    public boolean findEmail(String email) {
        try {
            TypedQuery<Customer> query = em.createNamedQuery("Customer.findByCemail", Customer.class);
            query.setParameter("cemail", email);
            return query.getResultList().isEmpty();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving emails", ex);
            ex.printStackTrace();
        }
        return false;
    }

    /*
    Check ob Phone bereits vorhanden ist
    Aufruf aus Validator-Klasse
     */
    public boolean findPhone(String phone) {
        try {
            TypedQuery<Customer> query = em.createNamedQuery("Customer.findByCphone", Customer.class);
            query.setParameter("cphone", phone);
            return query.getResultList().isEmpty();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving phone numbers", ex);
            ex.printStackTrace();
        }
        return false;
    }

    /*
    Funktion zur Anmeldung
    Params: String Username, String Password
    Return: Objekt Customer
     */
    public Customer findCustomer(String username, String password) {
        try {

            TypedQuery<Customer> query
                    = em.createQuery("SELECT c FROM Customer c JOIN c.fkAccid a WHERE a.accname = :accname AND a.accpwd = :accpwd", Customer.class);
            query.setParameter("accname", username);
            query.setParameter("accpwd", password);
            List<Customer> customers = query.getResultList();
            if (!customers.isEmpty()) {
                return customers.get(0);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving customer", ex);
            ex.printStackTrace();
        }
        return null;
    }

    /*
    Funktion zu aenderung von Producten
    */
    public void updateProduct(Product product) {
        try {
            ut.begin();
            em.merge(product);
            ut.commit();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error updating product", ex);
            ex.printStackTrace();
        }
    }
    
    /*
    Fuellt ProductInfo liste fuer Admin-Dashboard mit besten Produkten
    */
    public List<ProductInfo> findBestsellers() {
        List<ProductInfo> leastSoldProducts = new ArrayList<>();

        try {
            TypedQuery<Product> query
                    = em.createQuery("SELECT p "
                            + "FROM Product p "
                            + "JOIN p.orderdetailCollection od "
                            + "GROUP BY p "
                            + "ORDER BY SUM(od.odamount) DESC", Product.class);
            query.setMaxResults(5);

            List<Product> products = query.getResultList();
            
            for (Product product : products) { //Iteration durch alle Produkte
                int totalAmount = 0;
                for (Orderdetail orderdetail : product.getOrderdetailCollection()) { //Iteration durch alle Orderdetails des aktuellen Produkts
                    totalAmount += orderdetail.getOdamount();
                }
                ProductInfo item = new ProductInfo(product.getPrname(), totalAmount, product.getPcatenum());
                leastSoldProducts.add(item);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error while retrieving least sold product list from the database", ex);
        }

        return leastSoldProducts;
    }

    /*
    Fuellt ProductInfo liste fuer Admin-Dashboard mit schlechtesten Produkten
    */
    public List<ProductInfo> findLeastSoldProducts() {
        List<ProductInfo> leastSoldProducts = new ArrayList<>();

        try {
            TypedQuery<Product> query
                    = em.createQuery("SELECT p "
                            + "FROM Product p "
                            + "LEFT JOIN p.orderdetailCollection od "
                            + "GROUP BY p "
                            + "ORDER BY SUM(od.odamount) ASC", Product.class);
            query.setMaxResults(5);
            List<Product> products = query.getResultList();
            for (Product product : products) {
                int totalAmount = 0;
                for (Orderdetail orderdetail : product.getOrderdetailCollection()) {
                    totalAmount += orderdetail.getOdamount();
                }
                ProductInfo item = new ProductInfo(product.getPrname(), totalAmount, product.getPcatenum());
                leastSoldProducts.add(item);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error while retrieving least sold product list from the database", ex);
        }

        return leastSoldProducts;
    }
    
    //Fuellt UserInfo liste fuer Admin-Dashboard mit besten Kunden
    public List<UserInfo> findBestCustomers() {
        List<UserInfo> userInfoList = new ArrayList<>();

        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c "
                            + "FROM Customer c "
                            + "JOIN c.ordersCollection o "
                            + "JOIN o.orderdetailCollection od "
                            + "WHERE o.odeldate >= :twoMonthsAgo "
                            + "GROUP BY c "
                            + "ORDER BY SIZE(c.ordersCollection) DESC", Customer.class);

            // LocalDate in Date wandeln
            LocalDate currentLocalDate = LocalDate.now();
            LocalDate recentLocalDate = currentLocalDate.minusMonths(2);
            Date recentDate;
            recentDate = Date.from(recentLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            //Parameter für Date setzen in query
            query.setParameter("twoMonthsAgo", recentDate);
            query.setMaxResults(5);
            
            List<Customer> customers = query.getResultList();

            //Iteration durch alle Benutzer Obejekte
            for (Customer customer : customers) {
                
                int ordersAmount = customer.getOrdersCollection().size();
                double revenue = findRevenue(customer.getCid()); // Methodenaufruf findRevenue
                UserInfo userInfo = new UserInfo((customer.getCfirstname() + " " + customer.getCfamname()),
                        ordersAmount, revenue);
                userInfoList.add(userInfo);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error while retrieving best customer list from the database", ex);
        }
        return userInfoList;
    }

    //Fuellt UserInfo liste fuer Admin-Dashboard mit besten Kunden
    public List<UserInfo> findInactiveCustomers() {
        List<UserInfo> inactiveCustomers = new ArrayList<>();

        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c "
                            + "FROM Customer c "
                            + "LEFT JOIN c.ordersCollection o "
                            + "LEFT JOIN o.orderdetailCollection od "
                            + "WHERE o.odeldate <= :oneYearAgo OR o.odeldate IS NULL "
                            + "GROUP BY c "
                            + "ORDER BY SIZE(c.ordersCollection) ASC", Customer.class);

            // LocalDate in Date wandeln
            LocalDate currentLocalDate = LocalDate.now();
            LocalDate recentLocalDate = currentLocalDate.minusYears(1);
            Date recentDate;
            recentDate = Date.from(recentLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            query.setParameter("oneYearAgo", recentDate);
            List<Customer> customers = query.getResultList();
            
            //Iteration durch Benutzer Objekte
            for (Customer customer : customers) {
                int ordersAmount = customer.getOrdersCollection().size();
                double revenue = findRevenue(customer.getCid()); //Methodenaufruf findRevenue
                UserInfo userInfo = new UserInfo((customer.getCfirstname() + " " + customer.getCfamname()), ordersAmount, revenue);
                inactiveCustomers.add(userInfo);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error while retrieving inactive customer list from the database", ex);
        }

        return inactiveCustomers;
    }
    
    //Findet den Gesamtumsatz eines Kunden
    public double findRevenue(int cid) {
        double totalRevenue = 0.0;
        try {
            TypedQuery<Double> query
                    = em.createQuery("SELECT SUM(p.prpricenetto * od.odamount) "
                            + "FROM Customer c "
                            + "JOIN c.ordersCollection o "
                            + "JOIN o.orderdetailCollection od "
                            + "JOIN od.fkPrid p "
                            + "WHERE c.cid = :cid ", Double.class);
            query.setParameter("cid", cid);

            List<Double> revenues = query.getResultList();
            for (Double revenue : revenues) {
                totalRevenue += revenue;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving customer revenue", ex);
        }
        return totalRevenue;
    }

    //Fuellt die KategorieListe
    public List<ProductCategory> findCategories() {
        List<ProductCategory> categories = new ArrayList<>();
        try {
            String jpql = "SELECT DISTINCT p.pcatenum FROM Product p";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            List<String> categoryNames = query.getResultList();
            for (String categoryName : categoryNames) {
                ProductCategory productCategory = new ProductCategory(categoryName);
                categories.add(productCategory);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving categories", ex);
        }
        return categories;
    }
}
