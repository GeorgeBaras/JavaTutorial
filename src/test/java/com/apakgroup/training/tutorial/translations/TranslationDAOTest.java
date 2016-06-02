package com.apakgroup.training.tutorial.translations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.apakgroup.wfs.model.translations.Translation;
import com.apakgroup.wfs.translations.dao.TranslationDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/hibernateContext.xml" })
public class TranslationDAOTest {

    @Resource
    private TranslationDAOImpl translationDAO;

    @Resource
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    public final void testKeyIsMissing() {
        List<Translation> translations = translationDAO.getAllTranslations();
        System.out.println(translations.size());
        boolean exists = false;
        for (Translation translation : translations) {
            if (translation.getKey().equals("new_test_key")) {
                exists = true;
            }
        }
        assertFalse(exists);
    }

    @Test
    @Transactional
    public final void testCreateNewEntry() {
        Translation translation = new Translation();
        translation.setKey("new_test_key");
        translation.setLanguage("en");
        translation.setCountry(null);
        translation.setValue("Test value");
        this.translationDAO.saveOrUpdate(translation);
        // System.out.println(translationDAO.getAllTranslations().size());
        boolean exists = false;
        for (Translation myTranslation : translationDAO.getAllTranslations()) {
            if (myTranslation.getKey().equals("new_test_key")) {
                exists = true;
            }
        }
        assertTrue(exists);
    }

    // #{sysxmessage.Please_select_your_model}
    // #{sysxmessage.Vehicle_Valuation}
    // #{sysxmessage.Enter_your_vehicle_miles}

    @Ignore
    @Test
    @Transactional
    @Rollback(false)
    public final void addTranslations() {
        // English Translation
        this.translationDAO.saveOrUpdate(
                new Translation("Please_select_your_model", "EN", "United Kingdom", "Please select your model"));
        this.translationDAO
                .saveOrUpdate(new Translation("Vehicle_Valuation", "EN", "United Kingdom", "Vehicle Valuation"));
        this.translationDAO.saveOrUpdate(
                new Translation("Enter_your_vehicle_miles", "EN", "United Kingdom", "Enter your vehicle miles"));
        // Greeklish Translation
        this.translationDAO.saveOrUpdate(
                new Translation("Please_select_your_model", "GR", "Greece", "Parakalw epilexte to montelo sas"));
        this.translationDAO.saveOrUpdate(new Translation("Vehicle_Valuation", "GR", "Greece", "Kostologhsh Oxhmatos"));
        this.translationDAO.saveOrUpdate(
                new Translation("Enter_your_vehicle_miles", "GR", "Greece", "Plhktologhste ta milia tou oxhmatos sas"));
    }

}
