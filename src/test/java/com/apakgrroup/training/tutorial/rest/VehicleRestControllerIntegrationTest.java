package com.apakgrroup.training.tutorial.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.apakgroup.training.tutorial.rest.VehicleRestController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
@WebAppConfiguration
public class VehicleRestControllerIntegrationTest {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private VehicleRestController vehicleRestController;

    private MockMvc mockMvc;

    @Before
    public void beforeMethod() {
        mockMvc = MockMvcBuilders.standaloneSetup(this.vehicleRestController).build();
    }

    @Test
    public final void testValueVehicleResult() throws Exception {
        MvcResult result = mockMvc.perform(get("/valueVehicle/1/lookUpCode0")).andReturn();
        String valuation = result.getResponse().getContentAsString();
        // assert against record in DB
        assertEquals(valuation, "100000");
    }

    @Test
    public final void testValueVehicleStatusCodes() throws Exception {
        mockMvc.perform(get("/valueVehicle/1/lookUpCode0")).andExpect(status().isOk());
        mockMvc.perform(get("/valueVehicle/-1/lookUpCode0")).andExpect(status().isBadRequest());
        mockMvc.perform(get("/valueVehicle/1/luc")).andExpect(status().isNotFound());
    }
}
