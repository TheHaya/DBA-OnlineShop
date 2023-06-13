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
@Table(name = "ingredient")
@NamedQueries({
    @NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i"),
    @NamedQuery(name = "Ingredient.findByIngid", query = "SELECT i FROM Ingredient i WHERE i.ingid = :ingid"),
    @NamedQuery(name = "Ingredient.findByIngname", query = "SELECT i FROM Ingredient i WHERE i.ingname = :ingname"),
    @NamedQuery(name = "Ingredient.findByOrigin", query = "SELECT i FROM Ingredient i WHERE i.origin = :origin")})
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INGID")
    private Integer ingid;
    @Basic(optional = false)
    @Column(name = "INGNAME")
    private String ingname;
    @Column(name = "ORIGIN")
    private String origin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIngid")
    private Collection<Ingredientallergen> ingredientallergenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredient")
    private Collection<Productingredient> productingredientCollection;

    public Ingredient() {
    }

    public Ingredient(Integer ingid) {
        this.ingid = ingid;
    }

    public Ingredient(Integer ingid, String ingname) {
        this.ingid = ingid;
        this.ingname = ingname;
    }

    public Integer getIngid() {
        return ingid;
    }

    public void setIngid(Integer ingid) {
        this.ingid = ingid;
    }

    public String getIngname() {
        return ingname;
    }

    public void setIngname(String ingname) {
        this.ingname = ingname;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Collection<Ingredientallergen> getIngredientallergenCollection() {
        return ingredientallergenCollection;
    }

    public void setIngredientallergenCollection(Collection<Ingredientallergen> ingredientallergenCollection) {
        this.ingredientallergenCollection = ingredientallergenCollection;
    }

    public Collection<Productingredient> getProductingredientCollection() {
        return productingredientCollection;
    }

    public void setProductingredientCollection(Collection<Productingredient> productingredientCollection) {
        this.productingredientCollection = productingredientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingid != null ? ingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.ingid == null && other.ingid != null) || (this.ingid != null && !this.ingid.equals(other.ingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newModel.Ingredient[ ingid=" + ingid + " ]";
    }
    
}
