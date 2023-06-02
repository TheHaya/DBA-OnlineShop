package converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import model.product;
import util.sqlBean;



@FacesConverter(value = "categoryConverter", forClass = product.class)
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        
        sqlBean sqlBean = (sqlBean) facesContext.getApplication()
                .evaluateExpressionGet(facesContext, "#{sqlBean}", sqlBean.class);
        
        //return sqlBean.getProductByCategory(value);
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        
        if (object instanceof product) {
            product product = (product) object;
            return product.getProdType();
        }
        
        return null;
    }
}
