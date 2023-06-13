/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Haya
 */
@Entity
@Table(name = "supplierproduct")
@NamedQueries({
    @NamedQuery(name = "Supplierproduct.findAll", query = "SELECT s FROM Supplierproduct s"),
    @NamedQuery(name = "Supplierproduct.findBySupid", query = "SELECT s FROM Supplierproduct s WHERE s.supplierproductPK.supid = :supid"),
    @NamedQuery(name = "Supplierproduct.findByPrid", query = "SELECT s FROM Supplierproduct s WHERE s.supplierproductPK.prid = :prid"),
    @NamedQuery(name = "Supplierproduct.findBySuppamount", query = "SELECT s FROM Supplierproduct s WHERE s.suppamount = :suppamount"),
    @NamedQuery(name = "Supplierproduct.findBySuppdeliverydate", query = "SELECT s FROM Supplierproduct s WHERE s.suppdeliverydate = :suppdeliverydate")})
public class Supplierproduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SupplierproductPK supplierproductPK;
    @Basic(optional = false)
    @Column(name = "SUPPAMOUNT")
    private int suppamount;
    @Basic(optional = false)
    @Column(name = "SUPPDELIVERYDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date suppdeliverydate;
    @JoinColumn(name = "PRID", referencedColumnName = "PRID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "SUPID", referencedColumnName = "SUPID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Supplier supplier;

    public Supplierproduct() {
    }

    public Supplierproduct(SupplierproductPK supplierproductPK) {
        this.supplierproductPK = supplierproductPK;
    }

    public Supplierproduct(SupplierproductPK supplierproductPK, int suppamount, Date suppdeliverydate) {
        this.supplierproductPK = supplierproductPK;
        this.suppamount = suppamount;
        this.suppdeliverydate = suppdeliverydate;
    }

    public Supplierproduct(int supid, int prid) {
        this.supplierproductPK = new SupplierproductPK(supid, prid);
    }

    public SupplierproductPK getSupplierproductPK() {
        return supplierproductPK;
    }

    public void setSupplierproductPK(SupplierproductPK supplierproductPK) {
        this.supplierproductPK = supplierproductPK;
    }

    public int getSuppamount() {
        return suppamount;
    }

    public void setSuppamount(int suppamount) {
        this.suppamount = suppamount;
    }

    public Date getSuppdeliverydate() {
        return suppdeliverydate;
    }

    public void setSuppdeliverydate(Date suppdeliverydate) {
        this.suppdeliverydate = suppdeliverydate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierproductPK != null ? supplierproductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplierproduct)) {
            return false;
        }
        Supplierproduct other = (Supplierproduct) object;
        if ((this.supplierproductPK == null && other.supplierproductPK != null) || (this.supplierproductPK != null && !this.supplierproductPK.equals(other.supplierproductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newModel.Supplierproduct[ supplierproductPK=" + supplierproductPK + " ]";
    }
    
}
