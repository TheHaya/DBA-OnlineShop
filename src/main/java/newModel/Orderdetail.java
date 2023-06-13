/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

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
    @Basic(optional = false)
    @Column(name = "ODAMOUNT")
    private int odamount;
    @JoinColumn(name = "FK_OID", referencedColumnName = "OID")
    @ManyToOne(optional = false)
    private Orders fkOid;
    @JoinColumn(name = "FK_PRID", referencedColumnName = "PRID")
    @ManyToOne(optional = false)
    private Product fkPrid;

    public Orderdetail() {
    }

    public Orderdetail(Integer odid) {
        this.odid = odid;
    }

    public Orderdetail(Integer odid, Date oddate, int odamount) {
        this.odid = odid;
        this.oddate = oddate;
        this.odamount = odamount;
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

    public int getOdamount() {
        return odamount;
    }

    public void setOdamount(int odamount) {
        this.odamount = odamount;
    }

    public Orders getFkOid() {
        return fkOid;
    }

    public void setFkOid(Orders fkOid) {
        this.fkOid = fkOid;
    }

    public Product getFkPrid() {
        return fkPrid;
    }

    public void setFkPrid(Product fkPrid) {
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
        return "newModel.Orderdetail[ odid=" + odid + " ]";
    }
    
}
