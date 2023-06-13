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
@Table(name = "account")
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccid", query = "SELECT a FROM Account a WHERE a.accid = :accid"),
    @NamedQuery(name = "Account.findByAcctimestamp", query = "SELECT a FROM Account a WHERE a.acctimestamp = :acctimestamp"),
    @NamedQuery(name = "Account.findByAcctype", query = "SELECT a FROM Account a WHERE a.acctype = :acctype"),
    @NamedQuery(name = "Account.findByAccpwd", query = "SELECT a FROM Account a WHERE a.accpwd = :accpwd"),
    @NamedQuery(name = "Account.findByAccname", query = "SELECT a FROM Account a WHERE a.accname = :accname")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACCID")
    private Integer accid;
    @Basic(optional = false)
    @Column(name = "ACCTIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acctimestamp;
    @Basic(optional = false)
    @Column(name = "ACCTYPE")
    private int acctype;
    @Basic(optional = false)
    @Column(name = "ACCPWD")
    private String accpwd;
    @Basic(optional = false)
    @Column(name = "ACCNAME")
    private String accname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAccid")
    private Collection<Customer> customerCollection;

    public Account() {
    }

    public Account(Integer accid) {
        this.accid = accid;
    }

    public Account(Integer accid, Date acctimestamp, int acctype, String accpwd, String accname) {
        this.accid = accid;
        this.acctimestamp = acctimestamp;
        this.acctype = acctype;
        this.accpwd = accpwd;
        this.accname = accname;
    }

    public Integer getAccid() {
        return accid;
    }

    public void setAccid(Integer accid) {
        this.accid = accid;
    }

    public Date getAcctimestamp() {
        return acctimestamp;
    }

    public void setAcctimestamp(Date acctimestamp) {
        this.acctimestamp = acctimestamp;
    }

    public int getAcctype() {
        return acctype;
    }

    public void setAcctype(int acctype) {
        this.acctype = acctype;
    }

    public String getAccpwd() {
        return accpwd;
    }

    public void setAccpwd(String accpwd) {
        this.accpwd = accpwd;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accid != null ? accid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accid == null && other.accid != null) || (this.accid != null && !this.accid.equals(other.accid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newnewmodel.Account[ accid=" + accid + " ]";
    }
    
}
