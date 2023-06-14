package validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator(value="passValidator")
public class passValidator implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj) throws ValidatorException {
        String pass = obj.toString();
        FacesMessage facesMessage;
        
        // The regular expression to validate the password
        String passwordPattern = "^(?=.*\\d).{5,16}$";
        
        // Check if the password matches the regular expression
        if(!pass.matches(passwordPattern)){
            facesMessage = new FacesMessage("Invalid Password", "Please input between 5-16 characters and use at least one number");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }
}
