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
@Table(name = "allergen")
@NamedQueries({
    @NamedQuery(name = "Allergen.findAll", query = "SELECT a FROM Allergen a"),
    @NamedQuery(name = "Allergen.findByAllid", query = "SELECT a FROM Allergen a WHERE a.allid = :allid"),
    @NamedQuery(name = "Allergen.findByAllinfo", query = "SELECT a FROM Allergen a WHERE a.allinfo = :allinfo"),
    @NamedQuery(name = "Allergen.findByAllname", query = "SELECT a FROM Allergen a WHERE a.allname = :allname")})
public class Allergen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ALLID")
    private Integer allid;
    @Basic(optional = false)
    @Column(name = "ALLINFO")
    private String allinfo;
    @Basic(optional = false)
    @Column(name = "ALLNAME")
    private String allname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAllid")
    private Collection<Ingredientallergen> ingredientallergenCollection;

    public Allergen() {
    }

    public Allergen(Integer allid) {
        this.allid = allid;
    }

    public Allergen(Integer allid, String allinfo, String allname) {
        this.allid = allid;
        this.allinfo = allinfo;
        this.allname = allname;
    }

    public Integer getAllid() {
        return allid;
    }

    public void setAllid(Integer allid) {
        this.allid = allid;
    }

    public String getAllinfo() {
        return allinfo;
    }

    public void setAllinfo(String allinfo) {
        this.allinfo = allinfo;
    }

    public String getAllname() {
        return allname;
    }

    public void setAllname(String allname) {
        this.allname = allname;
    }

    public Collection<Ingredientallergen> getIngredientallergenCollection() {
        return ingredientallergenCollection;
    }

    public void setIngredientallergenCollection(Collection<Ingredientallergen> ingredientallergenCollection) {
        this.ingredientallergenCollection = ingredientallergenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (allid != null ? allid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Allergen)) {
            return false;
        }
        Allergen other = (Allergen) object;
        if ((this.allid == null && other.allid != null) || (this.allid != null && !this.allid.equals(other.allid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newnewmodel.Allergen[ allid=" + allid + " ]";
    }
    
}
