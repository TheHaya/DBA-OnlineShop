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
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOid", query = "SELECT o FROM Orders o WHERE o.oid = :oid"),
    @NamedQuery(name = "Orders.findByOdeldate", query = "SELECT o FROM Orders o WHERE o.odeldate = :odeldate"),
    @NamedQuery(name = "Orders.findByOstatus", query = "SELECT o FROM Orders o WHERE o.ostatus = :ostatus"),
    @NamedQuery(name = "Orders.findByOcomment", query = "SELECT o FROM Orders o WHERE o.ocomment = :ocomment"),
    @NamedQuery(name = "Orders.findByOchangedate", query = "SELECT o FROM Orders o WHERE o.ochangedate = :ochangedate")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OID")
    private Integer oid;
    @Column(name = "ODELDATE")
    @Temporal(TemporalType.DATE)
    private Date odeldate;
    @Column(name = "OSTATUS")
    private String ostatus;
    @Column(name = "OCOMMENT")
    private String ocomment;
    @Basic(optional = false)
    @Column(name = "OCHANGEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ochangedate;
    @JoinColumn(name = "FK_CID", referencedColumnName = "CID")
    @ManyToOne
    private Customer fkCid;
    @OneToMany(mappedBy = "fkOid")
    private Collection<Orderdetail> orderdetailCollection;

    public Orders() {
    }

    public Orders(Integer oid) {
        this.oid = oid;
    }

    public Orders(Integer oid, Date ochangedate) {
        this.oid = oid;
        this.ochangedate = ochangedate;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Date getOdeldate() {
        return odeldate;
    }

    public void setOdeldate(Date odeldate) {
        this.odeldate = odeldate;
    }

    public String getOstatus() {
        return ostatus;
    }

    public void setOstatus(String ostatus) {
        this.ostatus = ostatus;
    }

    public String getOcomment() {
        return ocomment;
    }

    public void setOcomment(String ocomment) {
        this.ocomment = ocomment;
    }

    public Date getOchangedate() {
        return ochangedate;
    }

    public void setOchangedate(Date ochangedate) {
        this.ochangedate = ochangedate;
    }

    public Customer getFkCid() {
        return fkCid;
    }

    public void setFkCid(Customer fkCid) {
        this.fkCid = fkCid;
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
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Orders[ oid=" + oid + " ]";
    }
    
}
