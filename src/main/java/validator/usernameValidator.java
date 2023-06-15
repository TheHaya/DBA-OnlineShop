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
public class usernameValidator implements Validator<String> {

    private sqlBean registerData;

    public usernameValidator() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        registerData = (sqlBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{sqlBean}", sqlBean.class);
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, String value) throws ValidatorException {
        boolean usernameExists = registerData.findAccName(value);
        if (usernameExists) {
            FacesMessage msg = new FacesMessage("Username is already taken");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
