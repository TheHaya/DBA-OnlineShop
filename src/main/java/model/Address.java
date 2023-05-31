/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
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

/**
 *
 * @author Haya
 */
@Entity
@Table(name = "address")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findByAdrid", query = "SELECT a FROM Address a WHERE a.adrid = :adrid"),
    @NamedQuery(name = "Address.findByAstreet", query = "SELECT a FROM Address a WHERE a.astreet = :astreet"),
    @NamedQuery(name = "Address.findByAfedstate", query = "SELECT a FROM Address a WHERE a.afedstate = :afedstate"),
    @NamedQuery(name = "Address.findByAcitycode", query = "SELECT a FROM Address a WHERE a.acitycode = :acitycode"),
    @NamedQuery(name = "Address.findByAcountry", query = "SELECT a FROM Address a WHERE a.acountry = :acountry")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ADRID")
    private Integer adrid;
    @Column(name = "ASTREET")
    private String astreet;
    @Column(name = "AFEDSTATE")
    private String afedstate;
    @Column(name = "ACITYCODE")
    private String acitycode;
    @Column(name = "ACOUNTRY")
    private String acountry;
    @JoinColumn(name = "FK_CID", referencedColumnName = "CID")
    @ManyToOne
    private Customer fkCid;

    public Address() {
    }

    public Address(Integer adrid) {
        this.adrid = adrid;
    }

    public Integer getAdrid() {
        return adrid;
    }

    public void setAdrid(Integer adrid) {
        this.adrid = adrid;
    }

    public String getAstreet() {
        return astreet;
    }

    public void setAstreet(String astreet) {
        this.astreet = astreet;
    }

    public String getAfedstate() {
        return afedstate;
    }

    public void setAfedstate(String afedstate) {
        this.afedstate = afedstate;
    }

    public String getAcitycode() {
        return acitycode;
    }

    public void setAcitycode(String acitycode) {
        this.acitycode = acitycode;
    }

    public String getAcountry() {
        return acountry;
    }

    public void setAcountry(String acountry) {
        this.acountry = acountry;
    }

    public Customer getFkCid() {
        return fkCid;
    }

    public void setFkCid(Customer fkCid) {
        this.fkCid = fkCid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adrid != null ? adrid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.adrid == null && other.adrid != null) || (this.adrid != null && !this.adrid.equals(other.adrid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Address[ adrid=" + adrid + " ]";
    }
    
}
