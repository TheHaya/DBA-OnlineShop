/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import newModel.Ingredientallergen;

/**
 *
 * @author Haya
 */
@jakarta.ejb.Stateless
public class IngredientallergenFacade extends AbstractFacade<Ingredientallergen> implements IngredientallergenFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngredientallergenFacade() {
        super(Ingredientallergen.class);
    }
    
}
