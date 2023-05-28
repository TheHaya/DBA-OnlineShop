/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 *
 * @author Haya
 * Ähnlich wie emailValidator.
 */
@FacesValidator(value = "usernameValidator")
public class usernameValidator implements Validator {

    /**
     * Creates a new instance of usernameValidator
     * @param fc
     * @param uic
     * @param obj
     */
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj) 
            throws ValidatorException {
        
        String username = obj.toString();
        FacesMessage facesMessage;
        
        if(!(username.length() >= 5 && username.length() <= 16))
        {
            facesMessage = new FacesMessage("Invalid Username", "Please input between 5-16 characters");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }
}
