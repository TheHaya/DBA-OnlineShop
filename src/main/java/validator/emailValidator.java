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
 * 
 * Die Validierer stellen sicher, die Validierungsanforderungen erfüllt werden und 
 * dass der Benutzer bemerkt, falls eine Eingabe nicht korrekt ist oder bestimmte Regeln
 * bricht.
 */
@FacesValidator(value = "emailValidator")
public class emailValidator implements Validator {

    /**
     * Creates a new instance of usernameValidator
     * @param fc
     * @param uic
     * @param obj
     */
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj) 
            throws ValidatorException {             // Wenn die Validierung fehlschlägt wird eine ausnahme  (hier famesMessage)
        
        String email = obj.toString();
        FacesMessage facesMessage;
        
        if(email.length() > 7 && email.length() < 40&& email.contains("@")) {
            if(email.contains(".")){
                // Do Nothing
            }
            else {
            facesMessage = new FacesMessage("Invalid Email", "Please input a valid Email");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
            }
        }
        else {
            facesMessage = new FacesMessage("Invalid Email", "Please input a valid Email");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
        
    }
}
