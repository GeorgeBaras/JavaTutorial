package com.apakgroup.training.tutorial.batch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class BatchTest {

    @Resource
    private PriceRecordFieldSetMapper priceRecordFieldSetMapper;

    @Resource
    JobLauncher jobLauncher;

    @Resource
    Job flatFilePriceRecordJob;

    //@Ignore
    @Test
    public final void testBatchJob() {
        try {
            JobExecution execution = jobLauncher.run(flatFilePriceRecordJob, new JobParameters());
            System.out.println("Execution Status : " + execution.getStatus());
            assertEquals(BatchStatus.COMPLETED, execution.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
            fail("Batch Job Failed");
        }

    }

    @Test
    public final void testconvertToMileage() {
        int miles = priceRecordFieldSetMapper.convertToMileage("00001789");
        assertEquals(miles, 200);

    }

    @Test
    public final void testconvertToLookUpCode() {
        String lookUpCode = priceRecordFieldSetMapper.convertToLookUpCode("VAL1234☃☃☃");
        System.out.println(lookUpCode);
        assertEquals("VAL1234", lookUpCode);
    }

    @Test
    public final void testconvertToValuation() {
        BigDecimal valuation = priceRecordFieldSetMapper.convertToValuation("01200000");
        BigDecimal expected = new BigDecimal("12000.00");
        assertEquals(expected, valuation);
    }

    @Test
    public final void testpriceBandIsBlank() {
        boolean isBlank = priceRecordFieldSetMapper.priceBandIsBlank("1200☂0");
        assertTrue(isBlank);
    }

}
