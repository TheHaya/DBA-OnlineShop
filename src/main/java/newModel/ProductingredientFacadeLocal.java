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
public interface ProductingredientFacadeLocal {

    void create(Productingredient productingredient);

    void edit(Productingredient productingredient);

    void remove(Productingredient productingredient);

    Productingredient find(Object id);

    List<Productingredient> findAll();

    List<Productingredient> findRange(int[] range);

    int count();
    
}
