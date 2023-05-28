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


// Ähnlich wie emailValidator 
@FacesValidator(value="passValidator")
public class passValidator implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj) 
            throws ValidatorException {
        
        String pass = obj.toString();
        FacesMessage facesMessage;
        
        if(pass.length() >=5 && pass.length() <= 16)
        {
            // Checkt ob Passwort eine Nummber enthält, von Stackoverflow
            if(pass.matches(".*\\d.*")){
                // Do Nothing
            }
            else {
                facesMessage = new FacesMessage("Invalid Password", "Please use at least one number");
                facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(facesMessage);
            }
        }
        else
        {
            facesMessage = new FacesMessage("Invalid Password", "Please input between 5-16 characters");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
        
    }

}
