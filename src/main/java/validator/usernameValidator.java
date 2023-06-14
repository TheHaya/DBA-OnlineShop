package validator;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import util.sqlBean;

@FacesValidator(value = "usernameValidator")
public class usernameValidator implements Validator {

    @Inject
    private sqlBean registerData;

    public usernameValidator() {
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj) throws ValidatorException {
        String username = obj.toString();
        FacesMessage facesMessage;

        if (!(username.length() >= 5 && username.length() <= 16)) {
            facesMessage = new FacesMessage("Invalid Username", "Please input between 5-16 characters");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }

        boolean usernameExists = registerData.findAccName(username);
        if (usernameExists) {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Username is already taken");
            throw new ValidatorException(facesMessage);
        }
    }
}
