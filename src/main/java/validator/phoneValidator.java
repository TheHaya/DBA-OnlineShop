package validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import util.sqlBean;

@FacesValidator(value = "phoneValidator")
public class phoneValidator implements Validator {

    @Inject
    private sqlBean registerData;
    
    public phoneValidator() {
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj) throws ValidatorException {
        String phoneNum = obj.toString();
        FacesMessage facesMessage;

        if (!isValidPhone(phoneNum)) {
            facesMessage = new FacesMessage("Invalid phone number", "Please enter a valid phone number");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }

        boolean phoneExists = registerData.findPhone(phoneNum);
        if (phoneExists) {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Phone number is already in use");
            throw new ValidatorException(facesMessage);
        }
    }

    private boolean isValidPhone(String phone) {
        if (phone.length() >= 7 && phone.length() <= 16 && phone.matches("[0-9]+")){ 
            return true;
        }
    return false;
    }
}
