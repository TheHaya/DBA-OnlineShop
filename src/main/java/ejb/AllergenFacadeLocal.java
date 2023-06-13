/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejb;

import java.util.List;
import newModel.Allergen;

/**
 *
 * @author Haya
 */
@jakarta.ejb.Local
public interface AllergenFacadeLocal {

    void create(Allergen allergen);

    void edit(Allergen allergen);

    void remove(Allergen allergen);

    Allergen find(Object id);

    List<Allergen> findAll();

    List<Allergen> findRange(int[] range);

    int count();
    
}
