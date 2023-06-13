/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author Haya
 */
@Embeddable
public class ProductingredientPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "FK_PRID")
    private int fkPrid;
    @Basic(optional = false)
    @Column(name = "FK_INGID")
    private int fkIngid;

    public ProductingredientPK() {
    }

    public ProductingredientPK(int fkPrid, int fkIngid) {
        this.fkPrid = fkPrid;
        this.fkIngid = fkIngid;
    }

    public int getFkPrid() {
        return fkPrid;
    }

    public void setFkPrid(int fkPrid) {
        this.fkPrid = fkPrid;
    }

    public int getFkIngid() {
        return fkIngid;
    }

    public void setFkIngid(int fkIngid) {
        this.fkIngid = fkIngid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkPrid;
        hash += (int) fkIngid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductingredientPK)) {
            return false;
        }
        ProductingredientPK other = (ProductingredientPK) object;
        if (this.fkPrid != other.fkPrid) {
            return false;
        }
        if (this.fkIngid != other.fkIngid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newModel.ProductingredientPK[ fkPrid=" + fkPrid + ", fkIngid=" + fkIngid + " ]";
    }
    
}
