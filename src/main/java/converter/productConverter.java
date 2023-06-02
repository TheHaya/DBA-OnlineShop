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
import model.product;

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
        
        product prod = (product) context.getApplication()
                .getELResolver().getValue(context.getELContext(), null, "Product_1");
        
        return prod.getProdType();
//        return null; // PLACEHOLDER
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object== null) {
            return null;
        }
        if (object instanceof product) {
            product o = (product) object;
            return getStringKey(o.getPcatid());
        }
        else {
            return null;
        }
    }
    
}
