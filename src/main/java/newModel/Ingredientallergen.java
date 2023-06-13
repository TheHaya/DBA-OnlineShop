/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newModel;

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
@Table(name = "ingredientallergen")
@NamedQueries({
    @NamedQuery(name = "Ingredientallergen.findAll", query = "SELECT i FROM Ingredientallergen i"),
    @NamedQuery(name = "Ingredientallergen.findByIaid", query = "SELECT i FROM Ingredientallergen i WHERE i.iaid = :iaid"),
    @NamedQuery(name = "Ingredientallergen.findByIaamount", query = "SELECT i FROM Ingredientallergen i WHERE i.iaamount = :iaamount")})
public class Ingredientallergen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IAID")
    private Integer iaid;
    @Basic(optional = false)
    @Column(name = "IAAMOUNT")
    private int iaamount;
    @JoinColumn(name = "FK_ALLID", referencedColumnName = "ALLID")
    @ManyToOne(optional = false)
    private Allergen fkAllid;
    @JoinColumn(name = "FK_INGID", referencedColumnName = "INGID")
    @ManyToOne(optional = false)
    private Ingredient fkIngid;

    public Ingredientallergen() {
    }

    public Ingredientallergen(Integer iaid) {
        this.iaid = iaid;
    }

    public Ingredientallergen(Integer iaid, int iaamount) {
        this.iaid = iaid;
        this.iaamount = iaamount;
    }

    public Integer getIaid() {
        return iaid;
    }

    public void setIaid(Integer iaid) {
        this.iaid = iaid;
    }

    public int getIaamount() {
        return iaamount;
    }

    public void setIaamount(int iaamount) {
        this.iaamount = iaamount;
    }

    public Allergen getFkAllid() {
        return fkAllid;
    }

    public void setFkAllid(Allergen fkAllid) {
        this.fkAllid = fkAllid;
    }

    public Ingredient getFkIngid() {
        return fkIngid;
    }

    public void setFkIngid(Ingredient fkIngid) {
        this.fkIngid = fkIngid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iaid != null ? iaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredientallergen)) {
            return false;
        }
        Ingredientallergen other = (Ingredientallergen) object;
        if ((this.iaid == null && other.iaid != null) || (this.iaid != null && !this.iaid.equals(other.iaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newModel.Ingredientallergen[ iaid=" + iaid + " ]";
    }
    
}
