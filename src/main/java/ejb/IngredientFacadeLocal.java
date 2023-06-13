/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejb;

import java.util.List;
import newModel.Ingredient;

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
