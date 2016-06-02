package com.apakgroup.training.resolvers;

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.PropertyNotWritableException;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.hibernate.PropertyNotFoundException;

import com.apakgroup.wfs.base.translation.TranslationManager;

/**
 * Resolver to translate sysxmessages Strings. Also will strip leading underscores from java
 * reservered words which are used in XHTML variables.
 */
public class ELMessageResolver extends ELResolver {

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return null;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property)
            throws NullPointerException, PropertyNotFoundException, ELException {
        if (base instanceof TranslationManager && property instanceof String) {
            context.setPropertyResolved(true);
            return Object.class;
        }
        return null;
    }

    /**
     * Method is called for all the #{key.value} expressions in xhtml and checks if "key" is
     * "sysxmessage". Then resolves each sysxmessage value using TranslationManager
     */
    @Override
    public Object getValue(ELContext context, Object base, Object property)
            throws NullPointerException, PropertyNotFoundException, ELException {
        if (base == null && property.equals("sysxmessage")) {
            context.setPropertyResolved(true);
            return createValueExpression("#{messageManager}", TranslationManager.class)
                    .getValue(FacesContext.getCurrentInstance().getELContext());
        }
        if (base instanceof TranslationManager && property instanceof String) {
            context.setPropertyResolved(true);

            // Remove leading underscores and use the rest of the String
            if (((String) property).substring(0, 1).equals("_")) {
                property = ((String) property).substring(1);
            }

            TranslationManager manager = (TranslationManager) base;
            return manager.getMessage((String) property, Locale.UK, null);
        }
        return null;
    }

    private static ValueExpression createValueExpression(String valueExpression, Class<?> cls) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(),
                valueExpression, cls);
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property)
            throws NullPointerException, PropertyNotFoundException, ELException {
        if (base instanceof TranslationManager) {
            context.setPropertyResolved(true);
            return true;
        }
        return false;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value)
            throws NullPointerException, PropertyNotFoundException, PropertyNotWritableException, ELException {
        if (base instanceof TranslationManager) {
            throw new PropertyNotWritableException("Unable to set a value on a readonly variable");
        }
    }

}