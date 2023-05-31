/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
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
    @NamedQuery(name = "Supplierproduct.findBySupprodid", query = "SELECT s FROM Supplierproduct s WHERE s.supprodid = :supprodid"),
    @NamedQuery(name = "Supplierproduct.findBySupprodts", query = "SELECT s FROM Supplierproduct s WHERE s.supprodts = :supprodts")})
public class Supplierproduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUPPRODID")
    private Integer supprodid;
    @Basic(optional = false)
    @Column(name = "SUPPRODTS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date supprodts;
    @JoinColumn(name = "PRID", referencedColumnName = "PRID")
    @ManyToOne
    private Product_1 prid;
    @JoinColumn(name = "SUPID", referencedColumnName = "SUPID")
    @ManyToOne
    private Supplier supid;

    public Supplierproduct() {
    }

    public Supplierproduct(Integer supprodid) {
        this.supprodid = supprodid;
    }

    public Supplierproduct(Integer supprodid, Date supprodts) {
        this.supprodid = supprodid;
        this.supprodts = supprodts;
    }

    public Integer getSupprodid() {
        return supprodid;
    }

    public void setSupprodid(Integer supprodid) {
        this.supprodid = supprodid;
    }

    public Date getSupprodts() {
        return supprodts;
    }

    public void setSupprodts(Date supprodts) {
        this.supprodts = supprodts;
    }

    public Product_1 getPrid() {
        return prid;
    }

    public void setPrid(Product_1 prid) {
        this.prid = prid;
    }

    public Supplier getSupid() {
        return supid;
    }

    public void setSupid(Supplier supid) {
        this.supid = supid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supprodid != null ? supprodid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplierproduct)) {
            return false;
        }
        Supplierproduct other = (Supplierproduct) object;
        if ((this.supprodid == null && other.supprodid != null) || (this.supprodid != null && !this.supprodid.equals(other.supprodid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Supplierproduct[ supprodid=" + supprodid + " ]";
    }
    
}
