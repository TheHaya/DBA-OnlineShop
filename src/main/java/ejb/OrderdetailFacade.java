/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import newModel.Orderdetail;

/**
 *
 * @author Haya
 */
@jakarta.ejb.Stateless
public class OrderdetailFacade extends AbstractFacade<Orderdetail> implements OrderdetailFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderdetailFacade() {
        super(Orderdetail.class);
    }
    
}
