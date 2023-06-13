/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author Haya
 */
@Entity
@Table(name = "supplier")
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findBySupid", query = "SELECT s FROM Supplier s WHERE s.supid = :supid"),
    @NamedQuery(name = "Supplier.findBySupname", query = "SELECT s FROM Supplier s WHERE s.supname = :supname"),
    @NamedQuery(name = "Supplier.findByContactname", query = "SELECT s FROM Supplier s WHERE s.contactname = :contactname"),
    @NamedQuery(name = "Supplier.findBySupwww", query = "SELECT s FROM Supplier s WHERE s.supwww = :supwww"),
    @NamedQuery(name = "Supplier.findBySupemail", query = "SELECT s FROM Supplier s WHERE s.supemail = :supemail"),
    @NamedQuery(name = "Supplier.findBySupphone", query = "SELECT s FROM Supplier s WHERE s.supphone = :supphone")})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUPID")
    private Integer supid;
    @Basic(optional = false)
    @Column(name = "SUPNAME")
    private String supname;
    @Basic(optional = false)
    @Column(name = "CONTACTNAME")
    private String contactname;
    @Basic(optional = false)
    @Column(name = "SUPWWW")
    private String supwww;
    @Basic(optional = false)
    @Column(name = "SUPEMAIL")
    private String supemail;
    @Basic(optional = false)
    @Column(name = "SUPPHONE")
    private String supphone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private Collection<Supplierproduct> supplierproductCollection;

    public Supplier() {
    }

    public Supplier(Integer supid) {
        this.supid = supid;
    }

    public Supplier(Integer supid, String supname, String contactname, String supwww, String supemail, String supphone) {
        this.supid = supid;
        this.supname = supname;
        this.contactname = contactname;
        this.supwww = supwww;
        this.supemail = supemail;
        this.supphone = supphone;
    }

    public Integer getSupid() {
        return supid;
    }

    public void setSupid(Integer supid) {
        this.supid = supid;
    }

    public String getSupname() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname = supname;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getSupwww() {
        return supwww;
    }

    public void setSupwww(String supwww) {
        this.supwww = supwww;
    }

    public String getSupemail() {
        return supemail;
    }

    public void setSupemail(String supemail) {
        this.supemail = supemail;
    }

    public String getSupphone() {
        return supphone;
    }

    public void setSupphone(String supphone) {
        this.supphone = supphone;
    }

    public Collection<Supplierproduct> getSupplierproductCollection() {
        return supplierproductCollection;
    }

    public void setSupplierproductCollection(Collection<Supplierproduct> supplierproductCollection) {
        this.supplierproductCollection = supplierproductCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supid != null ? supid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supid == null && other.supid != null) || (this.supid != null && !this.supid.equals(other.supid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newModel.Supplier[ supid=" + supid + " ]";
    }
    
}
