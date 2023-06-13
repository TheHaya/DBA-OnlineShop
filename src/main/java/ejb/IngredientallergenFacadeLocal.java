/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejb;

import java.util.List;
import newModel.Ingredientallergen;

/**
 *
 * @author Haya
 */
@jakarta.ejb.Local
public interface IngredientallergenFacadeLocal {

    void create(Ingredientallergen ingredientallergen);

    void edit(Ingredientallergen ingredientallergen);

    void remove(Ingredientallergen ingredientallergen);

    Ingredientallergen find(Object id);

    List<Ingredientallergen> findAll();

    List<Ingredientallergen> findRange(int[] range);

    int count();
    
}
