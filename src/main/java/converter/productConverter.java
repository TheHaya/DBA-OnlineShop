/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package converter;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.io.Serializable;
import model.Product_1;
import model.Productcategory;

/**
 *
 * @author Haya
 */
@FacesConverter(value = "productConverter")
public class productConverter implements Converter {
    
    java.lang.Integer getKey(String value){
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }
    
    String getStringKey(java.lang.Integer value){
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }
    /**
     * Creates a new instance of productConverter
     */
    public productConverter() {
    }
    
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        
        Product_1 product = (Product_1) context.getApplication()
                .getELResolver().getValue(context.getELContext(), null, "Product_1");
        
        return product.getFkPtype();
//        return null; // PLACEHOLDER
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object== null) {
            return null;
        }
        if (object instanceof Productcategory) {
            Productcategory o = (Productcategory) object;
            return getStringKey(o.getPcatid());
        }
        else {
            return null;
        }
    }
    
}
