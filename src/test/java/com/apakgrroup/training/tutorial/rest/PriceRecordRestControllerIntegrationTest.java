package com.apakgrroup.training.tutorial.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordService;
import com.apakgroup.training.tutorial.rest.PriceRecordRestController;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
@WebAppConfiguration
public class PriceRecordRestControllerIntegrationTest {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private PriceRecordService priceRecordService;

    @Resource
    private PriceRecordRestController priceRecordRestController;

    private MockMvc mockMvc;

    private PriceRecordImpl testPriceRecord;

    private long testPriceRecordID;

    private long testID;

    @Before
    public void beforeMethod() {
        mockMvc = MockMvcBuilders.standaloneSetup(this.priceRecordRestController).build();

        testPriceRecord = new PriceRecordImpl();
        testPriceRecord.setPriceBands(PriceRecordsGenerators.priceRecordGenerator(6).getPriceBands());
        testPriceRecord.setLookupCode("testPriceRecordLookup");
        testPriceRecordID = this.priceRecordService.addPriceRecord(testPriceRecord);
    }

    // addPriceBandByLookUpCode
    // TODO
    // addPriceRecordByLookUpCode  
    // TODO
    // deletePriceBandByLookUpCode

    @Test
    public final void testDeletePriceBandByLookUpCodeStatusCodes() throws Exception {
        mockMvc.perform(delete("/deletePriceBand/luc")).andExpect(status().isNotFound());
    }

    @Test
    public final void testDeletePriceBandByLookUpCodeResult() throws Exception {
        mockMvc.perform(delete("/deletePriceBand/testPriceRecordLookup")).andExpect(status().isCreated());
        int priceBands = this.priceRecordService.getPriceRecordByIDEAGER(testPriceRecordID).getPriceBands().size();
        assertEquals(priceBands, 5);
    }

    // deletePriceRecordByLookUpCode

    @Test
    public final void testDeletePriceRecordByLookUpCodeStatusCodes() throws Exception {
        mockMvc.perform(delete("/deletePriceRecord/luc")).andExpect(status().isNotFound());
    }

    @Test
    public final void testDeletePriceRecordByLookUpCodeResult() throws Exception {
        PriceRecordImpl testPriceRecord1 = new PriceRecordImpl();
        testPriceRecord1.setPriceBands(PriceRecordsGenerators.priceRecordGenerator(6).getPriceBands());
        testPriceRecord1.setLookupCode("RecordToDELETE");
        testID = this.priceRecordService.addPriceRecord(testPriceRecord1);

        mockMvc.perform(delete("/deletePriceRecord/RecordToDELETE")).andExpect(status().isCreated());
        boolean hasBeenDeleted = !this.priceRecordService.lookUpCodeIsInDB("RecordToDELETE");
        assert (hasBeenDeleted);
    }

    // getPriceRecords

    @Ignore
    @Test
    public final void testGetPriceRecordsStatusCodes() throws Exception {
        mockMvc.perform(get("/getPriceRecords")).andExpect(status().isOk());
    }

    // getPriceRecordByLookUpCode

    @Test
    public final void testGetPriceRecordByLookUpCodeStatusCodes() throws Exception {
        mockMvc.perform(get("/getPriceRecord/testPriceRecordLookup")).andExpect(status().isOk());
        mockMvc.perform(get("/getPriceRecord/testPriceRecord")).andExpect(status().isNotFound());
    }

    @Test
    public final void testGetPriceRecordByLookUpCodeResult() throws Exception {
        MvcResult result = mockMvc.perform(get("/getPriceRecord/testPriceRecordLookup")).andReturn();
        String priceRecordJSON = result.getResponse().getContentAsString();
        assertTrue(isJSONValid(priceRecordJSON));

    }

    // getPriceBandByLookUpCode
    @Test
    public final void testGetPriceBandByLookUpCodeStatusCodes() throws Exception {
        mockMvc.perform(get("/getPriceBand/testPriceRecordLookup")).andExpect(status().isOk());
        mockMvc.perform(get("/getPriceBand/testPriceRecord")).andExpect(status().isNotFound());
    }

    @Test
    public final void testGetPriceBandByLookUpCodeResult() throws Exception {
        MvcResult result = mockMvc.perform(get("/getPriceBand/testPriceRecordLookup")).andReturn();
        String priceBandJSON = result.getResponse().getContentAsString();
        System.out.println(priceBandJSON);
        assertTrue(isJSONValid(priceBandJSON));
    }

    // Helper method
    public PriceRecordImpl JsonStringToPriceRecord(String jsonInString)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PriceRecordImpl priceRecord = mapper.readValue(jsonInString, PriceRecordImpl.class);
        return priceRecord;
    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @After
    public void destroy() {
        this.priceRecordService.deletePriceRecordByID(testPriceRecordID);

    }

}
