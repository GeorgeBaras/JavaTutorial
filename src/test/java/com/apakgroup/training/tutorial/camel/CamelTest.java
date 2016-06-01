package com.apakgroup.training.tutorial.camel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apakgroup.training.tutorial.batch.PriceRecordFieldSetMapper;
import com.apakgroup.training.tutorial.model.PriceRecordImpl;
import com.apakgroup.training.tutorial.model.PriceRecordList;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class CamelTest {

    @Resource
    private PriceRecordFieldSetMapper priceRecordFieldSetMapper;

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    private Job flatFileToSoapRequestJob;

    @Resource
    private BatchPriceRecordToSoapXMLRequest batchPriceRecordToSoapXMLRequest;

    @Resource(name = "camel")
    CamelContext context;

    @Test
    public void runTest() throws Exception {
        context.start();
        Thread.sleep(20000);
        context.stop();
    }

    public void setContext(CamelContext context) {
        this.context = context;
    }

    @Ignore
    @Test
    public final void testFlatFileToXMLJob() {
        try {
            JobExecution execution = jobLauncher.run(flatFileToSoapRequestJob, new JobParameters());
            System.out.println("Execution Status : " + execution.getStatus());
            assertEquals(BatchStatus.COMPLETED, execution.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
            fail("Batch Job Failed");
        }
    }

    @Ignore
    @Test
    public final void testWriteMethod() throws Exception {
        // Create a PriceRecordList
        PriceRecordList priceRecordList = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(5, 2));
        this.batchPriceRecordToSoapXMLRequest
                .write((List<? extends PriceRecordImpl>) priceRecordList.getListOfPriceRecords());
        // String path = "\\request.xml";
        Path path = FileSystems.getDefault().getPath("REQUEST", "1.xml");
        assertTrue(Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS }));
    }

}
