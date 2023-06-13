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
public interface SupplierproductFacadeLocal {

    void create(Supplierproduct supplierproduct);

    void edit(Supplierproduct supplierproduct);

    void remove(Supplierproduct supplierproduct);

    Supplierproduct find(Object id);

    List<Supplierproduct> findAll();

    List<Supplierproduct> findRange(int[] range);

    int count();
    
}
