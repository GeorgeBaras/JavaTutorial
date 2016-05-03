package com.apakgroup.training.tutorial.webapp.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
@FacesValidator("mileageValidator")
public class MileageValidator implements Validator {

    private String numberInput;

    //constructor
    public MileageValidator() {
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        boolean isOffRange = false;
        if (value != null) {
            isOffRange = (value.toString().length() > 3);
        }

        if (value == null || isOffRange) {
            FacesMessage msg = new FacesMessage(
                    "Your input is invalid. Please enter an integer value within the [0-999999] range");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }

    }

    // Getters and Setters
    public String getNumberInput() {
        return numberInput;
    }

    public void setNumberInput(String number) {
        this.numberInput = number;
    }

}
