/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejb;

import java.util.List;
import newModel.Orderdetail;

/**
 *
 * @author Haya
 */
@jakarta.ejb.Local
public interface OrderdetailFacadeLocal {

    void create(Orderdetail orderdetail);

    void edit(Orderdetail orderdetail);

    void remove(Orderdetail orderdetail);

    Orderdetail find(Object id);

    List<Orderdetail> findAll();

    List<Orderdetail> findRange(int[] range);

    int count();
    
}
