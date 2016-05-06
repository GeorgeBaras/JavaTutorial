package com.apakgroup.training.tutorial.webapp.jsf;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class JSFcontrollerTest {

    @Resource
    private JSFcontroller jSFcontroller;

    @Transactional
    @Ignore
    @Test
    public final void testGetPriceBands() {
        int numberOfBands = this.jSFcontroller.getPriceBands().size();
        int expectedNumberOfBands = 6;
        assertEquals(numberOfBands, expectedNumberOfBands);
    }

}
