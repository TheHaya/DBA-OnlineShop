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
public interface IngredientFacadeLocal {

    void create(Ingredient ingredient);

    void edit(Ingredient ingredient);

    void remove(Ingredient ingredient);

    Ingredient find(Object id);

    List<Ingredient> findAll();

    List<Ingredient> findRange(int[] range);

    int count();
    
}
