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
public class SupplierproductPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "SUPID")
    private int supid;
    @Basic(optional = false)
    @Column(name = "PRID")
    private int prid;

    public SupplierproductPK() {
    }

    public SupplierproductPK(int supid, int prid) {
        this.supid = supid;
        this.prid = prid;
    }

    public int getSupid() {
        return supid;
    }

    public void setSupid(int supid) {
        this.supid = supid;
    }

    public int getPrid() {
        return prid;
    }

    public void setPrid(int prid) {
        this.prid = prid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) supid;
        hash += (int) prid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierproductPK)) {
            return false;
        }
        SupplierproductPK other = (SupplierproductPK) object;
        if (this.supid != other.supid) {
            return false;
        }
        if (this.prid != other.prid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newModel.SupplierproductPK[ supid=" + supid + ", prid=" + prid + " ]";
    }
    
}
