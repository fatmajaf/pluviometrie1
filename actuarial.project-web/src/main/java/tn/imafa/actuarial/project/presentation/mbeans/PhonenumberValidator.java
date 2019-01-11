package tn.imafa.actuarial.project.presentation.mbeans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
@FacesValidator("com.fatma.PhoneValidator")
public class PhonenumberValidator implements Validator{
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object o) throws ValidatorException {

		  if (o == null) {
		    return;
		  }

		  Integer phone = (Integer) o;
		  String phonenum= String.valueOf(phone);
		  boolean matches = phonenum.matches("\\d{9}");
		  if (!matches) {
		    FacesMessage msg = new FacesMessage(
		            FacesMessage.SEVERITY_FATAL, 
		            "Phone number is invalid", 
		            null);
		    throw new ValidatorException(msg);
		  }
		
	}

}
