package com.apakgroup.training.tutorial.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.apakgroup.bolsterdoc.BD;
import com.apakgroup.bolsterdoc.annotation.TestDocTitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
@WebAppConfiguration
@TestDocTitle("Integration Test for the VehicleRestController.") // added for BolsterDoc
public class VehicleRestControllerIntegrationTest {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private VehicleRestController vehicleRestController;

    private MockMvc mockMvc;

    @BeforeClass // added for BolsterDoc
    public static void preamble() {
        BD.doc("The following sections will provide an overview of the testing of the REST valueVehicle url path.");
    }

    @Before
    public void beforeMethod() {
        mockMvc = MockMvcBuilders.standaloneSetup(this.vehicleRestController).build();
    }

    @Test
    @TestDocTitle("Testing the Result") // added for BolsterDoc
    public final void testValueVehicleResult() throws Exception {
        BD.doc("=== Create a valueVehicle url-path get request for a priceRecord which is already in the Database");
        BD.doc("/valueVehicle/1/lookUpCode0");
        BD.doc("Expected result: the value of the vehicle as it has been calculated");
        MvcResult result = mockMvc.perform(get("/valueVehicle/1/lookUpCode0")).andReturn();
        String valuation = result.getResponse().getContentAsString();
        // assert against record in DB
        assertEquals(valuation, "100000");
    }

    @Test
    @TestDocTitle("Testing the returned Status Codes") // added for BolsterDoc
    public final void testValueVehicleStatusCodes() throws Exception {
        BD.doc("=== Create valueVehicle url-path get requests");
        BD.doc("==== Expect status OK if the request is valid");
        BD.doc("/valueVehicle/1/lookUpCode0");
        BD.doc("Expected status: OK");
        mockMvc.perform(get("/valueVehicle/1/lookUpCode0")).andExpect(status().isOk());
        BD.doc("==== Expect status Bad Request if the given miles is an invalid input, eg. a negative number");
        BD.doc("/valueVehicle/-1/lookUpCode0");
        BD.doc("Expected status: BAD REQUEST");
        mockMvc.perform(get("/valueVehicle/-1/lookUpCode0")).andExpect(status().isBadRequest());
        BD.doc("==== Expect status Not Found if the given lookupCode is not in the Database");
        BD.doc("/valueVehicle/1/luc");
        BD.doc("Expected status: NOT FOUND");
        mockMvc.perform(get("/valueVehicle/1/luc")).andExpect(status().isNotFound());
    }
}
