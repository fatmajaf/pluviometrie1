package tn.imafa.actuarial.project.presentation.mbeans;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("com.fatma.EmailValidator")
public class EmailValidator implements Validator {

 private static final Pattern EMAIL_PATTERN =
   Pattern.compile("^(.+)@(.+)$");

 @Override
 public void validate(FacesContext facesContext,
                      UIComponent uiComponent,
                      Object o)
         throws ValidatorException {

  if (o == null) {
    return;
  }

  String email = (String) o;
  boolean matches = EMAIL_PATTERN.matcher(email)
                    .matches();
  if (!matches) {
    FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_FATAL, 
            "Email is invalid", 
            null);
    throw new ValidatorException(msg);
  }

 }
}