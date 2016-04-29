package com.apakgroup.training.tutorial.webapp.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

@FacesConverter("mileageConverter")
public class MileageConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value)
            throws ConverterException {
        boolean isANumber = StringUtils.isNumeric(value);
        // validation for non numeric input takes place here
        if (!isANumber || value.equals("")) {
            return null;
        }
        // return it as a string to be validated for its length by the validator
        String mileage = String.valueOf((Integer.valueOf(value) / 1000));
        if (mileage.equals("0")) {
            return "1";
        }
        return mileage;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value)
            throws ConverterException {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

}
