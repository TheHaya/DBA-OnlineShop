/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author Haya
 */
@Entity
@Table(name = "productcategory")
@NamedQueries({
    @NamedQuery(name = "Productcategory.findAll", query = "SELECT p FROM Productcategory p"),
    @NamedQuery(name = "Productcategory.findByPcatid", query = "SELECT p FROM Productcategory p WHERE p.pcatid = :pcatid"),
    @NamedQuery(name = "Productcategory.findByPcatname", query = "SELECT p FROM Productcategory p WHERE p.pcatname = :pcatname"),
    @NamedQuery(name = "Productcategory.findByPcatorigin", query = "SELECT p FROM Productcategory p WHERE p.pcatorigin = :pcatorigin")})
public class Productcategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PCATID")
    private Integer pcatid;
    @Column(name = "PCATNAME")
    private String pcatname;
    @Column(name = "PCATORIGIN")
    private String pcatorigin;
    @OneToMany(mappedBy = "fkCatid")
    private Collection<Product_1> productCollection;

    public Productcategory() {
    }

    public Productcategory(Integer pcatid) {
        this.pcatid = pcatid;
    }

    public Integer getPcatid() {
        return pcatid;
    }

    public void setPcatid(Integer pcatid) {
        this.pcatid = pcatid;
    }

    public String getPcatname() {
        return pcatname;
    }

    public void setPcatname(String pcatname) {
        this.pcatname = pcatname;
    }

    public String getPcatorigin() {
        return pcatorigin;
    }

    public void setPcatorigin(String pcatorigin) {
        this.pcatorigin = pcatorigin;
    }

    public Collection<Product_1> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product_1> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pcatid != null ? pcatid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productcategory)) {
            return false;
        }
        Productcategory other = (Productcategory) object;
        if ((this.pcatid == null && other.pcatid != null) || (this.pcatid != null && !this.pcatid.equals(other.pcatid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Productcategory[ pcatid=" + pcatid + " ]";
    }
    
}
