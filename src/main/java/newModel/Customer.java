/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
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
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCid", query = "SELECT c FROM Customer c WHERE c.cid = :cid"),
    @NamedQuery(name = "Customer.findByCfirstname", query = "SELECT c FROM Customer c WHERE c.cfirstname = :cfirstname"),
    @NamedQuery(name = "Customer.findByCfamname", query = "SELECT c FROM Customer c WHERE c.cfamname = :cfamname"),
    @NamedQuery(name = "Customer.findByCsalutation", query = "SELECT c FROM Customer c WHERE c.csalutation = :csalutation"),
    @NamedQuery(name = "Customer.findByCemail", query = "SELECT c FROM Customer c WHERE c.cemail = :cemail"),
    @NamedQuery(name = "Customer.findByCphone", query = "SELECT c FROM Customer c WHERE c.cphone = :cphone"),
    @NamedQuery(name = "Customer.findByCbirthdate", query = "SELECT c FROM Customer c WHERE c.cbirthdate = :cbirthdate")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CID")
    private Integer cid;
    @Basic(optional = false)
    @Column(name = "CFIRSTNAME")
    private String cfirstname;
    @Basic(optional = false)
    @Column(name = "CFAMNAME")
    private String cfamname;
    @Basic(optional = false)
    @Column(name = "CSALUTATION")
    private String csalutation;
    @Basic(optional = false)
    @Column(name = "CEMAIL")
    private String cemail;
    @Basic(optional = false)
    @Column(name = "CPHONE")
    private String cphone;
    @Basic(optional = false)
    @Column(name = "CBIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date cbirthdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCid")
    private Collection<Address> addressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCid")
    private Collection<Orders> ordersCollection;
    @JoinColumn(name = "FK_ACCID", referencedColumnName = "ACCID")
    @ManyToOne(optional = false)
    private Account fkAccid;

    public Customer() {
    }

    public Customer(Integer cid) {
        this.cid = cid;
    }

    public Customer(Integer cid, String cfirstname, String cfamname, String csalutation, String cemail, String cphone, Date cbirthdate) {
        this.cid = cid;
        this.cfirstname = cfirstname;
        this.cfamname = cfamname;
        this.csalutation = csalutation;
        this.cemail = cemail;
        this.cphone = cphone;
        this.cbirthdate = cbirthdate;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCfirstname() {
        return cfirstname;
    }

    public void setCfirstname(String cfirstname) {
        this.cfirstname = cfirstname;
    }

    public String getCfamname() {
        return cfamname;
    }

    public void setCfamname(String cfamname) {
        this.cfamname = cfamname;
    }

    public String getCsalutation() {
        return csalutation;
    }

    public void setCsalutation(String csalutation) {
        this.csalutation = csalutation;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public Date getCbirthdate() {
        return cbirthdate;
    }

    public void setCbirthdate(Date cbirthdate) {
        this.cbirthdate = cbirthdate;
    }

    public Collection<Address> getAddressCollection() {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection) {
        this.addressCollection = addressCollection;
    }

    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    public Account getFkAccid() {
        return fkAccid;
    }

    public void setFkAccid(Account fkAccid) {
        this.fkAccid = fkAccid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newnewmodel.Customer[ cid=" + cid + " ]";
    }
    
}
