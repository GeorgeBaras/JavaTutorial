package com.apakgroup.training.tutorial.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PriceRecordServiceTest {

    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private PriceRecordService priceRecordService;

    @Transactional
    @Ignore //@Rollback(false) // 
    @Test
    public final void populateDBfromXML() throws JAXBException {
        File file = new File("10kEntriesXML.xml");
        int recordsInserted = priceRecordService.addPriceRecordsFromXMLFile(file).size();
        assertEquals(10000, recordsInserted); // fixed size from the file
    }

    @Transactional
    @Rollback //// @Ignore //  
    @Test
    public final void addPriceRecordTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        priceRecordService.addPriceRecord(priceRecord);

        // To get the latest addition to the database, the records will be ordered descendingly and the first one will be pulled
        // sessionFactory.getCurrentSession();
        Session session = sessionFactory.getCurrentSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.addOrder(Order.desc("id"));
        criterion.setMaxResults(1);
        PriceRecordImpl priceRercordFromDB = (PriceRecordImpl) criterion.uniqueResult();

        assertTrue(priceRecord.compare(priceRercordFromDB));

    }

    @Transactional
    @Rollback
    @Test
    public final void addPriceRecordListTest() {
        PriceRecordList priceRecordList = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(3, 2));
        // Add the List
        int recordsInserted = priceRecordService.addPriceRecordList(priceRecordList).size();
        assertEquals(priceRecordList.getListOfPriceRecords().size(), recordsInserted);
    }

    @Transactional
    @Rollback
    @Test
    public final void addListOfPriceRecordsTest() {
        List<PriceRecord> listOfPriceRecords = PriceRecordsGenerators.listOfPriceRecordGenerator(2, 2);

        // Add the List
        int recordsInserted = priceRecordService.addListOfPriceRecords(listOfPriceRecords).size();
        assertEquals(listOfPriceRecords.size(), recordsInserted);
    }

    // addPriceRecordsFromXMLFile
    @Transactional
    @Rollback
    @Test
    public final void addPriceRecordsFromXMLFileTest() throws JAXBException {
        // Add the List
        File file = new File("EntriesToUnmarhal.xml");
        int recordsInserted = priceRecordService.addPriceRecordsFromXMLFile(file).size();
        assertEquals(10, recordsInserted); // fixed size from the file
    }

    @Transactional
    @Rollback
    @Test
    public final void getPriceRecordByLookupcodeTest() {
        // add a stabbed record to the database
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        long priceRecordID = priceRecordService.addPriceRecord(priceRecord);
        // get it by lookupcode
        PriceRecordImpl priceRecordFromDB = (PriceRecordImpl) priceRecordService
                .getPriceRecordByLookupcode(priceRecord.getLookupCode());
        assertTrue(priceRecordFromDB.compare(priceRecord));
    }

    @Transactional
    @Rollback
    @Test
    public final void getPriceRecordByIDTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        long priceRecordID = priceRecordService.addPriceRecord(priceRecord);
        PriceRecordImpl priceRecordFromDB = (PriceRecordImpl) priceRecordService.getPriceRecordByID(priceRecordID);
        assertTrue(priceRecordFromDB.compare(priceRecord));
    }

    @Transactional
    @Rollback
    @Test
    public final void getAllPriceRecordsTest() {
        // Get the count of all entries
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(PriceRecordImpl.class);
        criterion.setProjection(Projections.rowCount());
        Long countBefore = (Long) criterion.uniqueResult();
        int countBeforeGetAll = new Integer(countBefore.toString());
        // Get all entries from DB as a list and check the size
        assertEquals(priceRecordService.getAllPriceRecords().size(), countBeforeGetAll);
    }

    @Transactional
    @Rollback
    @Test
    public final void getIDbyLookupcodeTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        long actualID = priceRecordService.addPriceRecord(priceRecord);
        long id = priceRecordService.getIDbyLookupcode(priceRecord.getLookupCode());
        assertEquals(actualID, id);

    }

    @Transactional
    @Rollback
    @Test
    public final void updateLookupcodeByLookupcodeTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        long id = priceRecordService.addPriceRecord(priceRecord);

        priceRecordService.updateLookupcodeByLookupcode(priceRecord.getLookupCode(), "AlteredLookUpCode");

        String updatedLookUpCode = priceRecordService.getPriceRecordByID(id).getLookupCode();
        assertEquals(updatedLookUpCode, "AlteredLookUpCode");
    }

    @Transactional
    @Rollback
    @Test
    public final void updateLookupcodeByIDTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        long id = priceRecordService.addPriceRecord(priceRecord);

        priceRecordService.updateLookupcodeByID(id, "AlteredLookUpCodeById");

        String updatedLookUpCode = priceRecordService.getPriceRecordByID(id).getLookupCode();
        assertEquals(updatedLookUpCode, "AlteredLookUpCodeById");
    }

    @Transactional
    @Rollback
    @Test
    public final void addPriceBandByLookupcodeTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        long id = priceRecordService.addPriceRecord(priceRecord);
        PriceBandImpl priceBand = (PriceBandImpl) PriceRecordsGenerators.priceBandGenerator(
                priceRecord.getPriceBands().get(priceRecord.getPriceBands().size() - 1).getMileage(),
                priceRecord.getPriceBands().get(priceRecord.getPriceBands().size() - 1).getValuation());
        //Add the priceBand to the record
        priceRecordService.addPriceBandByLookupcode(priceBand, priceRecord.getLookupCode());
        //Retrieve it
        PriceBandImpl updatedLastPriceBand = (PriceBandImpl) priceRecordService.getPriceRecordByID(id).getPriceBands()
                .get(priceRecordService.getPriceRecordByID(id).getPriceBands().size() - 1);
        // Compare
        assertTrue(priceBand.compare(updatedLastPriceBand));
    }

    @Transactional
    @Rollback
    @Test
    public final void addPriceBandByIDTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        long id = priceRecordService.addPriceRecord(priceRecord);
        PriceBandImpl priceBand = (PriceBandImpl) PriceRecordsGenerators.priceBandGenerator(
                priceRecord.getPriceBands().get(priceRecord.getPriceBands().size() - 1).getMileage(),
                priceRecord.getPriceBands().get(priceRecord.getPriceBands().size() - 1).getValuation());
        //Add the priceBand to the record
        priceRecordService.addPriceBandByID(priceBand, id);
        //Retrieve it
        PriceBandImpl updatedLastPriceBand = (PriceBandImpl) priceRecordService.getPriceRecordByID(id).getPriceBands()
                .get(priceRecordService.getPriceRecordByID(id).getPriceBands().size() - 1);
        // Compare
        assertTrue(priceBand.compare(updatedLastPriceBand));
    }

    @Transactional
    @Rollback
    @Test
    public final void removeLastPriceBandByLookupcodeTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(3); // 3 priceBands
        long id = priceRecordService.addPriceRecord(priceRecord);

        //Remove the priceBand to the record
        priceRecordService.removeLastPriceBandByLookupcode(priceRecord.getLookupCode());
        //Retrieve the number of priceBands
        int numberOfPriceBands = priceRecordService.getPriceRecordByID(id).getPriceBands().size();
        // Compare
        assertEquals(numberOfPriceBands, 2);
    }

    @Transactional
    @Rollback
    @Test
    public final void removeLastPriceBandByIDTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(3); // 3 priceBands
        long id = priceRecordService.addPriceRecord(priceRecord);

        //Remove the priceBand to the record
        priceRecordService.removeLastPriceBandByID(id);
        //Retrieve the number of priceBands
        int numberOfPriceBands = priceRecordService.getPriceRecordByID(id).getPriceBands().size();
        // Compare
        assertEquals(numberOfPriceBands, 2);
    }

    @Transactional
    @Rollback
    @Test
    public final void deletePriceRecordByLookupcodeTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        long id = priceRecordService.addPriceRecord(priceRecord);
        int numberOfRecordsBeforeDeletion = priceRecordService.getAllPriceRecords().size();
        //Remove the priceBand to the record
        priceRecordService.deletePriceRecordByLookupcode(priceRecord.getLookupCode());
        int numberOfRecordsAfterDeletion = priceRecordService.getAllPriceRecords().size();
        // Check that one record has been deleted from the DB
        assertEquals(numberOfRecordsBeforeDeletion, numberOfRecordsAfterDeletion + 1);
    }

    @Transactional
    @Rollback
    @Test
    public final void deletePriceRecordByIDTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        long id = priceRecordService.addPriceRecord(priceRecord);
        int numberOfRecordsBeforeDeletion = priceRecordService.getAllPriceRecords().size();
        //Remove the priceBand to the record
        priceRecordService.deletePriceRecordByID(id);
        int numberOfRecordsAfterDeletion = priceRecordService.getAllPriceRecords().size();
        // Check that one record has been deleted from the DB
        assertEquals(numberOfRecordsBeforeDeletion, numberOfRecordsAfterDeletion + 1);
    }

    @Transactional
    @Rollback
    @Test
    public final void deleteListOfPriceRecordsTest() {
        //Create a list of priceRecords
        PriceRecordImpl priceRecord1 = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        PriceRecordImpl priceRecord2 = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        List<PriceRecord> priceRecords = new ArrayList<>();
        priceRecords.add(priceRecord1);
        priceRecords.add(priceRecord2);
        // Add it to the DB
        priceRecordService.addListOfPriceRecords(priceRecords);

        int numberOfRecordsBeforeDeletion = priceRecordService.getAllPriceRecords().size();
        //Remove the list of records
        priceRecordService.deleteListOfPriceRecords(priceRecords);
        int numberOfRecordsAfterDeletion = priceRecordService.getAllPriceRecords().size();
        // Check that 2 records have been deleted from the DB
        assertEquals(numberOfRecordsBeforeDeletion, numberOfRecordsAfterDeletion + 2);
    }

    @Transactional
    @Rollback
    @Test
    public final void deleteAllPriceRecordsTest() {
        //Create a list of priceRecords
        PriceRecordImpl priceRecord1 = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        PriceRecordImpl priceRecord2 = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        PriceRecordImpl priceRecord3 = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(2);
        List<PriceRecord> priceRecords = new ArrayList<>();
        priceRecords.add(priceRecord1);
        priceRecords.add(priceRecord2);
        priceRecords.add(priceRecord3);
        // Add it to the DB
        priceRecordService.addListOfPriceRecords(priceRecords);
        //Call the delete method
        priceRecordService.deleteAllPriceRecords();
        int numberOfRecordsAfterDeletion = priceRecordService.getAllPriceRecords().size();
        // Check that all records have been deleted from the DB
        assertEquals(0, numberOfRecordsAfterDeletion);
    }

}
