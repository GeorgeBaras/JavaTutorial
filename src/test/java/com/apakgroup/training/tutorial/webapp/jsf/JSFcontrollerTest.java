package com.apakgroup.training.tutorial.webapp.jsf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.apakgroup.training.tutorial.webapp.jsf.JSFcontroller;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class JSFcontrollerTest {

    @Resource
    private JSFcontroller jSFcontroller;

    @Test
    public final void testGetVehicles() {
        fail("Not yet implemented"); // TODO
    }

    @Transactional
    @Test
    public final void testGetPriceBands() {
        int numberOfBands = this.jSFcontroller.getPriceBands().size();
        int expectedNumberOfBands = 6;
        assertEquals(numberOfBands, expectedNumberOfBands);
    }

}
