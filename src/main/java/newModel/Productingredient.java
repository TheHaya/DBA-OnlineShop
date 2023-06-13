/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
    @NamedQuery(name = "Productingredient.findByFkPrid", query = "SELECT p FROM Productingredient p WHERE p.productingredientPK.fkPrid = :fkPrid"),
    @NamedQuery(name = "Productingredient.findByFkIngid", query = "SELECT p FROM Productingredient p WHERE p.productingredientPK.fkIngid = :fkIngid"),
    @NamedQuery(name = "Productingredient.findByPringamountgram", query = "SELECT p FROM Productingredient p WHERE p.pringamountgram = :pringamountgram")})
public class Productingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductingredientPK productingredientPK;
    @Basic(optional = false)
    @Column(name = "PRINGAMOUNTGRAM")
    private int pringamountgram;
    @JoinColumn(name = "FK_INGID", referencedColumnName = "INGID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ingredient ingredient;
    @JoinColumn(name = "FK_PRID", referencedColumnName = "PRID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public Productingredient() {
    }

    public Productingredient(ProductingredientPK productingredientPK) {
        this.productingredientPK = productingredientPK;
    }

    public Productingredient(ProductingredientPK productingredientPK, int pringamountgram) {
        this.productingredientPK = productingredientPK;
        this.pringamountgram = pringamountgram;
    }

    public Productingredient(int fkPrid, int fkIngid) {
        this.productingredientPK = new ProductingredientPK(fkPrid, fkIngid);
    }

    public ProductingredientPK getProductingredientPK() {
        return productingredientPK;
    }

    public void setProductingredientPK(ProductingredientPK productingredientPK) {
        this.productingredientPK = productingredientPK;
    }

    public int getPringamountgram() {
        return pringamountgram;
    }

    public void setPringamountgram(int pringamountgram) {
        this.pringamountgram = pringamountgram;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productingredientPK != null ? productingredientPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productingredient)) {
            return false;
        }
        Productingredient other = (Productingredient) object;
        if ((this.productingredientPK == null && other.productingredientPK != null) || (this.productingredientPK != null && !this.productingredientPK.equals(other.productingredientPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newnewmodel.Productingredient[ productingredientPK=" + productingredientPK + " ]";
    }
    
}
