package com.apakgroup.training.resolvers;

import java.util.Locale;

import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;

import com.apakgroup.wfs.base.enums.WfsEnum;
import com.apakgroup.wfs.base.translation.TranslationManager;
import com.apakgroup.wfs.translations.controllers.TranslationContextHandler;

public class TranslationManagerImpl implements TranslationManager {

    private static final long serialVersionUID = 1838357863207688113L;

    private MessageSource messageSource;

    private final Logger logger = LoggerFactory.getLogger(TranslationManagerImpl.class);

    private boolean hideErrors = true;

    private boolean showKeys;

    /** To turn context handling off comment out in wiring */
    private TranslationContextHandler translationContextHandler;

    @Override
    public boolean isHideErrors() {
        return hideErrors;
    }

    @Override
    public void setHideErrors(boolean hideErrors) {
        this.hideErrors = hideErrors;
    }

    /**
     * Cleans passed in key. For example, transforms key "some_test_translation" into
     * "Some Test Translation"
     * 
     * @param key
     * @return
     */
    private String convertKey(String key) {
        if (hideErrors) {
            // sanitize the error
            char[] delim = { '_' };
            // captialize
            key = WordUtils.capitalize(key, delim);
            if (key != null) {
                // replace _ with spaces
                key = key.replace('_', ' ');
            }
        } else {
            key = "!" + key + "!";
        }
        return key;
    }

    private void handleTranslationContext(String key, String source) {
        if (translationContextHandler != null) {
            translationContextHandler.addSourceToContext(key, source);
        }
    }

    public void setMessageSource(MessageSource source) {
        messageSource = source;
    }

    public void setShowKeys(boolean showKeys) {
        this.showKeys = showKeys;
    }

    public void setTranslationContextHandler(TranslationContextHandler translationContextHandler) {
        this.translationContextHandler = translationContextHandler;
    }

    private String getDefaultMessage(String key, DefaultType defaultMessageType, Locale locale) {
        logger.debug("No translation for {}, {}", key, locale);
        switch (defaultMessageType) {
            case NULL:
                return null;
            case EMPTY_STRING:
                return "";
            case KEY:
                return key;
            case CONVERTED_KEY:
                return convertKey(key);
        }
        return null;
    }

    @Override
    public String getMessage(String key, Object[] args, DefaultType defaultMessageType, Locale locale, String source) {
        if (key == null) {
            logger.info("TranslationManager was asked to translate null message key from source {} ", source);
            return "";
        }
        if (showKeys) {
            return key;
        }
        handleTranslationContext(key, source);
        String message = messageSource.getMessage(key, args, null, locale);
        if (message == null || message.length() == 0) {
            return getDefaultMessage(key, defaultMessageType, locale);
        }
        return message;
    }

    @Override
    public String getMessage(MessageSourceResolvable msr, Locale locale, String source) {
        return getMessage(msr, DefaultType.CONVERTED_KEY, locale, source);
    }

    /**
     * Tries to find a translation for given key using given Locale. Translates using default locale
     * (en_GB) if fails to find translation using the desired one. If translation is missingfor both
     * desired Locale and default Locale, returns cleaned translation key (some_key -> Some Key)
     */
    @Override
    public String getMessage(MessageSourceResolvable msr, DefaultType defaultMessageType, Locale locale,
            String source) {
        if (msr == null) {
            logger.info("TranslationManager was asked to translate null messageSourceResolvable from source {} ",
                    source);
            return "";
        }
        if (showKeys) {
            return msr.getCodes()[0];
        }
        handleTranslationContext(msr.getCodes()[0], source);
        String message = messageSource.getMessage(msr, locale);
        if (message == null || message.length() == 0) {
            return getDefaultMessage(msr.getCodes()[0], defaultMessageType, locale);
        }
        return message;
    }

    @Override
    public String getEnumMessage(WfsEnum enumeration, Locale locale, String dcReference, String source) {
        String key = String.format("%s_%s", enumeration.getClass().getSimpleName(), enumeration.getName());
        String message = getMessage(key, null, DefaultType.NULL, locale, source);
        if (message == null) {
            return enumeration.getName();
        }
        return message;
    }

    @Override
    public String getMessage(String key, Locale locale, String source) {
        return getMessage(key, null, DefaultType.CONVERTED_KEY, locale, source);
    }

    @Override
    public String getMessage(String key, Object[] args, Locale locale, String source) {
        return getMessage(key, args, DefaultType.CONVERTED_KEY, locale, source);
    }

    @Override
    public String getEnumMessage(Enum enumeration, Locale locale, String dcReference, String source) {
        return getEnumMessage(enumeration, locale, dcReference, source);
    }

}