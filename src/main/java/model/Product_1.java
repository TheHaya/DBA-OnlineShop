/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @NamedQuery(name = "Product_1.findAll", query = "SELECT p FROM Product_1 p"),
    @NamedQuery(name = "Product_1.findByPrid", query = "SELECT p FROM Product_1 p WHERE p.prid = :prid"),
    @NamedQuery(name = "Product_1.findByPrname", query = "SELECT p FROM Product_1 p WHERE p.prname = :prname"),
    @NamedQuery(name = "Product_1.findByPrno", query = "SELECT p FROM Product_1 p WHERE p.prno = :prno"),
    @NamedQuery(name = "Product_1.findByPrpricenetto", query = "SELECT p FROM Product_1 p WHERE p.prpricenetto = :prpricenetto"),
    @NamedQuery(name = "Product_1.findByPrmodifdate", query = "SELECT p FROM Product_1 p WHERE p.prmodifdate = :prmodifdate"),
    @NamedQuery(name = "Product_1.findByPrimg", query = "SELECT p FROM Product_1 p WHERE p.primg = :primg"),
    @NamedQuery(name = "Product_1.findByPrimagepath", query = "SELECT p FROM Product_1 p WHERE p.primagepath = :primagepath"),
    @NamedQuery(name = "Product_1.findByPrcomment", query = "SELECT p FROM Product_1 p WHERE p.prcomment = :prcomment"),
    @NamedQuery(name = "Product_1.findByFkPtype", query = "SELECT p FROM Product_1 p WHERE p.fkPtype = :fkPtype")})
public class Product_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRID")
    private Integer prid;
    @Column(name = "PRNAME")
    private String prname;
    @Column(name = "PRNO")
    private Integer prno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRPRICENETTO")
    private Double prpricenetto;
    @Basic(optional = false)
    @Column(name = "PRMODIFDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prmodifdate;
    @Column(name = "PRIMG")
    private String primg;
    @Column(name = "PRIMAGEPATH")
    private String primagepath;
    @Column(name = "PRCOMMENT")
    private String prcomment;
    @Column(name = "FK_PTYPE")
    private Integer fkPtype;
    @JoinColumn(name = "FK_CATID", referencedColumnName = "PCATID")
    @ManyToOne
    private Productcategory fkCatid;
    @OneToMany(mappedBy = "prid")
    private Collection<Supplierproduct> supplierproductCollection;
    @OneToMany(mappedBy = "fkPrid")
    private Collection<Productingredient> productingredientCollection;
    @OneToMany(mappedBy = "fkPrid")
    private Collection<Orderdetail> orderdetailCollection;

    public Product_1() {
    }

    public Product_1(Integer prid) {
        this.prid = prid;
    }

    public Product_1(Integer prid, Date prmodifdate) {
        this.prid = prid;
        this.prmodifdate = prmodifdate;
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

    public Integer getPrno() {
        return prno;
    }

    public void setPrno(Integer prno) {
        this.prno = prno;
    }

    public Double getPrpricenetto() {
        return prpricenetto;
    }

    public void setPrpricenetto(Double prpricenetto) {
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

    public Integer getFkPtype() {
        return fkPtype;
    }

    public void setFkPtype(Integer fkPtype) {
        this.fkPtype = fkPtype;
    }

    public Productcategory getFkCatid() {
        return fkCatid;
    }

    public void setFkCatid(Productcategory fkCatid) {
        this.fkCatid = fkCatid;
    }

    public Collection<Supplierproduct> getSupplierproductCollection() {
        return supplierproductCollection;
    }

    public void setSupplierproductCollection(Collection<Supplierproduct> supplierproductCollection) {
        this.supplierproductCollection = supplierproductCollection;
    }

    public Collection<Productingredient> getProductingredientCollection() {
        return productingredientCollection;
    }

    public void setProductingredientCollection(Collection<Productingredient> productingredientCollection) {
        this.productingredientCollection = productingredientCollection;
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
        if (!(object instanceof Product_1)) {
            return false;
        }
        Product_1 other = (Product_1) object;
        if ((this.prid == null && other.prid != null) || (this.prid != null && !this.prid.equals(other.prid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Product_1[ prid=" + prid + " ]";
    }
    
}
