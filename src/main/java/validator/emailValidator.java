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

    private sqlBean registerData;

    public emailValidator() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        registerData = (sqlBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{sqlBean}", sqlBean.class);
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
        if (!emailExists) {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email is already taken");
            throw new ValidatorException(facesMessage);
        }
    }

    private boolean isValidEmail(String email) {
        // This pattern matches basic email addresses
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        // Check if the email matches the pattern
        if (email.matches(emailPattern)) {
            return true;
        }
        return false;
    }
}
