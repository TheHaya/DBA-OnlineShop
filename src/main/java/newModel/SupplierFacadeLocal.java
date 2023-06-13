/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package newModel;

import java.util.List;

/**
 *
 * @author Haya
 */
@jakarta.ejb.Local
public interface SupplierFacadeLocal {

    void create(Supplier supplier);

    void edit(Supplier supplier);

    void remove(Supplier supplier);

    Supplier find(Object id);

    List<Supplier> findAll();

    List<Supplier> findRange(int[] range);

    int count();
    
}
