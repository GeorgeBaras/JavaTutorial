package com.apakgroup.training.tutorial.webapp.examples;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class UserBeanTest {

    @Resource
    UserBean userBean;

    @Test
    public final void testPrintMsgFromSpring() {
        assertEquals(userBean.printMsgFromSpring(), "JSF 2 + SpringIntegration!");
    }

}
