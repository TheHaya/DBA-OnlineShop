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
@Table(name = "orderdetail")
@NamedQueries({
    @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o"),
    @NamedQuery(name = "Orderdetail.findByOdid", query = "SELECT o FROM Orderdetail o WHERE o.odid = :odid"),
    @NamedQuery(name = "Orderdetail.findByOddate", query = "SELECT o FROM Orderdetail o WHERE o.oddate = :oddate"),
    @NamedQuery(name = "Orderdetail.findByOdamount", query = "SELECT o FROM Orderdetail o WHERE o.odamount = :odamount")})
public class Orderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ODID")
    private Integer odid;
    @Basic(optional = false)
    @Column(name = "ODDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date oddate;
    @Column(name = "ODAMOUNT")
    private Integer odamount;
    @JoinColumn(name = "FK_OID", referencedColumnName = "OID")
    @ManyToOne
    private Orders fkOid;
    @JoinColumn(name = "FK_PRID", referencedColumnName = "PRID")
    @ManyToOne
    private Product_1 fkPrid;

    public Orderdetail() {
    }

    public Orderdetail(Integer odid) {
        this.odid = odid;
    }

    public Orderdetail(Integer odid, Date oddate) {
        this.odid = odid;
        this.oddate = oddate;
    }

    public Integer getOdid() {
        return odid;
    }

    public void setOdid(Integer odid) {
        this.odid = odid;
    }

    public Date getOddate() {
        return oddate;
    }

    public void setOddate(Date oddate) {
        this.oddate = oddate;
    }

    public Integer getOdamount() {
        return odamount;
    }

    public void setOdamount(Integer odamount) {
        this.odamount = odamount;
    }

    public Orders getFkOid() {
        return fkOid;
    }

    public void setFkOid(Orders fkOid) {
        this.fkOid = fkOid;
    }

    public Product_1 getFkPrid() {
        return fkPrid;
    }

    public void setFkPrid(Product_1 fkPrid) {
        this.fkPrid = fkPrid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (odid != null ? odid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetail)) {
            return false;
        }
        Orderdetail other = (Orderdetail) object;
        if ((this.odid == null && other.odid != null) || (this.odid != null && !this.odid.equals(other.odid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Orderdetail[ odid=" + odid + " ]";
    }
    
}
