/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Haya
 */
@Entity
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByPrid", query = "SELECT p FROM Product p WHERE p.prid = :prid"),
    @NamedQuery(name = "Product.findByPrname", query = "SELECT p FROM Product p WHERE p.prname = :prname"),
    @NamedQuery(name = "Product.findByPrpricenetto", query = "SELECT p FROM Product p WHERE p.prpricenetto = :prpricenetto"),
    @NamedQuery(name = "Product.findByPrmodifdate", query = "SELECT p FROM Product p WHERE p.prmodifdate = :prmodifdate"),
    @NamedQuery(name = "Product.findByPrimg", query = "SELECT p FROM Product p WHERE p.primg = :primg"),
    @NamedQuery(name = "Product.findByPrimagepath", query = "SELECT p FROM Product p WHERE p.primagepath = :primagepath"),
    @NamedQuery(name = "Product.findByPrcomment", query = "SELECT p FROM Product p WHERE p.prcomment = :prcomment"),
    @NamedQuery(name = "Product.findByPcatenum", query = "SELECT p FROM Product p WHERE p.pcatenum = :pcatenum")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRID")
    private Integer prid;
    @Basic(optional = false)
    @Column(name = "PRNAME")
    private String prname;
    @Basic(optional = false)
    @Column(name = "PRPRICENETTO")
    private double prpricenetto;
    @Basic(optional = false)
    @Column(name = "PRMODIFDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prmodifdate;
    @Basic(optional = false)
    @Column(name = "PRIMG")
    private String primg;
    @Basic(optional = false)
    @Column(name = "PRIMAGEPATH")
    private String primagepath;
    @Basic(optional = false)
    @Column(name = "PRCOMMENT")
    private String prcomment;
    @Basic(optional = false)
    @Column(name = "PCATENUM")
    private String pcatenum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<Productingredient> productingredientCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<Supplierproduct> supplierproductCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPrid")
    private Collection<Orderdetail> orderdetailCollection;

    public Product() {
    }

    public Product(Integer prid) {
        this.prid = prid;
    }

    public Product(Integer prid, String prname, double prpricenetto, Date prmodifdate, String primg, String primagepath, String prcomment, String pcatenum) {
        this.prid = prid;
        this.prname = prname;
        this.prpricenetto = prpricenetto;
        this.prmodifdate = prmodifdate;
        this.primg = primg;
        this.primagepath = primagepath;
        this.prcomment = prcomment;
        this.pcatenum = pcatenum;
    }

    public Integer getPrid() {
        return prid;
    }

    public void setPrid(Integer prid) {
        this.prid = prid;
    }

    public String getPrname() {
        return prname;
    }

    public void setPrname(String prname) {
        this.prname = prname;
    }

    public double getPrpricenetto() {
        return prpricenetto;
    }

    public void setPrpricenetto(double prpricenetto) {
        this.prpricenetto = prpricenetto;
    }

    public Date getPrmodifdate() {
        return prmodifdate;
    }

    public void setPrmodifdate(Date prmodifdate) {
        this.prmodifdate = prmodifdate;
    }

    public String getPrimg() {
        return primg;
    }

    public void setPrimg(String primg) {
        this.primg = primg;
    }

    public String getPrimagepath() {
        return primagepath;
    }

    public void setPrimagepath(String primagepath) {
        this.primagepath = primagepath;
    }

    public String getPrcomment() {
        return prcomment;
    }

    public void setPrcomment(String prcomment) {
        this.prcomment = prcomment;
    }

    public String getPcatenum() {
        return pcatenum;
    }

    public void setPcatenum(String pcatenum) {
        this.pcatenum = pcatenum;
    }

    public Collection<Productingredient> getProductingredientCollection() {
        return productingredientCollection;
    }

    public void setProductingredientCollection(Collection<Productingredient> productingredientCollection) {
        this.productingredientCollection = productingredientCollection;
    }

    public Collection<Supplierproduct> getSupplierproductCollection() {
        return supplierproductCollection;
    }

    public void setSupplierproductCollection(Collection<Supplierproduct> supplierproductCollection) {
        this.supplierproductCollection = supplierproductCollection;
    }

    public Collection<Orderdetail> getOrderdetailCollection() {
        return orderdetailCollection;
    }

    public void setOrderdetailCollection(Collection<Orderdetail> orderdetailCollection) {
        this.orderdetailCollection = orderdetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prid != null ? prid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.prid == null && other.prid != null) || (this.prid != null && !this.prid.equals(other.prid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newnewmodel.Product[ prid=" + prid + " ]";
    }
    
}
