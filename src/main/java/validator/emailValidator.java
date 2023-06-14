package validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import util.sqlBean;

@FacesValidator(value = "emailValidator")
public class emailValidator implements Validator {

    @Inject
    private sqlBean registerData;
    
    public emailValidator() {

    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj) throws ValidatorException {
        String email = obj.toString();
        FacesMessage facesMessage;

        if (!isValidEmail(email)) {
            facesMessage = new FacesMessage("Invalid Email", "Please enter a valid email address");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }

        boolean emailExists = registerData.findEmail(email);
        if (emailExists) {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email is already taken");
            throw new ValidatorException(facesMessage);
        }
    }

    private boolean isValidEmail(String email) {
    if (email.length() >= 5 && email.length() <= 40 && email.contains("@") && email.contains(".")) {
        return true;
    }
    return false;
}
}
