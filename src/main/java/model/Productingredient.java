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
@Table(name = "productingredient")
@NamedQueries({
    @NamedQuery(name = "Productingredient.findAll", query = "SELECT p FROM Productingredient p"),
    @NamedQuery(name = "Productingredient.findByPringid", query = "SELECT p FROM Productingredient p WHERE p.pringid = :pringid"),
    @NamedQuery(name = "Productingredient.findByPringamountgram", query = "SELECT p FROM Productingredient p WHERE p.pringamountgram = :pringamountgram")})
public class Productingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRINGID")
    private Integer pringid;
    @Column(name = "PRINGAMOUNTGRAM")
    private Integer pringamountgram;
    @JoinColumn(name = "FK_INGID", referencedColumnName = "INGID")
    @ManyToOne
    private Ingredient fkIngid;
    @JoinColumn(name = "FK_PRID", referencedColumnName = "PRID")
    @ManyToOne
    private Product_1 fkPrid;

    public Productingredient() {
    }

    public Productingredient(Integer pringid) {
        this.pringid = pringid;
    }

    public Integer getPringid() {
        return pringid;
    }

    public void setPringid(Integer pringid) {
        this.pringid = pringid;
    }

    public Integer getPringamountgram() {
        return pringamountgram;
    }

    public void setPringamountgram(Integer pringamountgram) {
        this.pringamountgram = pringamountgram;
    }

    public Ingredient getFkIngid() {
        return fkIngid;
    }

    public void setFkIngid(Ingredient fkIngid) {
        this.fkIngid = fkIngid;
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
        hash += (pringid != null ? pringid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productingredient)) {
            return false;
        }
        Productingredient other = (Productingredient) object;
        if ((this.pringid == null && other.pringid != null) || (this.pringid != null && !this.pringid.equals(other.pringid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Productingredient[ pringid=" + pringid + " ]";
    }
    
}
