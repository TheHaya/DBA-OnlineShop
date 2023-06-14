//package converter;
//
//import controller.productBean;
//import jakarta.faces.component.UIComponent;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.convert.Converter;
//import jakarta.faces.convert.FacesConverter;
//import newModel.ProductCategory;
//import util.sqlBean;
//
//@FacesConverter(value = "categoryConverter", forClass = ProductCategory.class)
//public class CategoryConverter implements Converter {
//
//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//        if (value == null || value.length() == 0) {
//            return null;
//        }
//        productBean controller
//                = (productBean) facesContext.getApplication()
//                        .getELResolver().getValue(facesContext.getELContext(), null, "productBean");
//        ProductCategory newCategory = controller.getCategory(getKey(value));
//        return newCategory;
//    }
//
//    java.lang.Integer getKey(String value) {
//        java.lang.Integer key;
//        key = Integer.valueOf(value);
//        return key;
//    }
//
//    String getStringKey(java.lang.Integer value) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(value);
//        return sb.toString();
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//        if (object == null) {
//            return null;
//        }
//        if (object instanceof ProductCategory) {
//            ProductCategory o = (ProductCategory) object;
//            return getStringKey(o.getId());
//        } else {
//            return null;
//        }
//    }
//}
