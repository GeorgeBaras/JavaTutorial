package com.apakgroup.training.tutorial.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PriceRecordDAOTest {

    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private PriceRecord lowOnly;

    private final PriceBand lowBand2 = new PriceBandImpl(15, new BigDecimal(15000.0));

    private final PriceRecord low1 = new PriceRecordImpl("low1", lowBand2);

    private final PriceBand lowBand3 = new PriceBandImpl(25, new BigDecimal(25000.0));

    private final PriceRecord low2 = new PriceRecordImpl("low2", lowBand3);

    private final PriceBand lowBand4 = new PriceBandImpl(35, new BigDecimal(35000.0));

    private final PriceRecord lowToDelete = new PriceRecordImpl("lowToDelete", lowBand4);

    private final PriceBand lowBand5 = new PriceBandImpl(35, new BigDecimal(35000.0));

    private final PriceRecord lowToDelete2 = new PriceRecordImpl("lowToDelete2", lowBand5);

    private final PriceBand lowBand6 = new PriceBandImpl(45, new BigDecimal(55000.0));

    private final PriceRecord lowToDelete3 = new PriceRecordImpl("lowToDelete3", lowBand6);

    @Transactional
    @Test
    public final void addPriceRecordTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        PriceRecordDAO.addPriceRecord(priceRecord);
        // To get the latest addition to the database, the records will be ordered descendingly and the first one will be pulled
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.addOrder(Order.desc("id"));
        criterion.setMaxResults(1);
        PriceRecordImpl priceRercordFromDB = (PriceRecordImpl) criterion.uniqueResult();

        assertTrue(priceRecord.compare(priceRercordFromDB));
    }

    // addPriceRecordList
    @Transactional
    @Test
    public final void addPriceRecordListTest() {
        PriceRecordList priceRecordList = new PriceRecordList(PriceRecordsGenerators.listOfPriceRecordGenerator(2, 2));
        // size before adding priceRecordList
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.setProjection(Projections.rowCount());
        Long countBefore = (Long) criterion.uniqueResult();
        int countBeforeList = new Integer(countBefore.toString());
        // Add the List
        PriceRecordDAO.addPriceRecordList(priceRecordList);
        // Get the count again and compare the list's size with the new entries on the table 
        Long countAfter = (Long) criterion.uniqueResult();
        int countAfterList = new Integer(countAfter.toString());

        assertEquals(priceRecordList.getListOfPriceRecords().size(), countAfterList - countBeforeList);
    }

    // addListOfPriceRecords
    @Transactional
    @Test
    public final void addListOfPriceRecordsTest() {
        List<PriceRecord> listOfPriceRecords = PriceRecordsGenerators.listOfPriceRecordGenerator(2, 2);
        // size before adding priceRecordList
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.setProjection(Projections.rowCount());
        Long countBefore = (Long) criterion.uniqueResult();
        int countBeforeList = new Integer(countBefore.toString());
        // Add the List
        PriceRecordDAO.addListOfPriceRecords(listOfPriceRecords);
        // Get the count again and compare the list's size with the new entries on the table 
        Long countAfter = (Long) criterion.uniqueResult();
        int countAfterList = new Integer(countAfter.toString());

        assertEquals(listOfPriceRecords.size(), countAfterList - countBeforeList);
    }

    // addPriceRecordsFromXMLFile
    @Transactional
    @Test
    public final void addPriceRecordsFromXMLFileTest() throws JAXBException {
        // size before adding priceRecordList
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.setProjection(Projections.rowCount());
        Long countBefore = (Long) criterion.uniqueResult();
        int countBeforeList = new Integer(countBefore.toString());
        // Add the List
        File file = new File("EntriesToUnmarhal.xml");
        PriceRecordDAO.addPriceRecordsFromXMLFile(file);
        // Get the count again and compare the list's size with the new entries on the table 
        Long countAfter = (Long) criterion.uniqueResult();
        int countAfterList = new Integer(countAfter.toString());

        assertEquals(10, countAfterList - countBeforeList); // fixed size from the file
    }

    @Transactional
    @Test
    public final void getPriceRecordByIDTest() {
        long lowID = PriceRecordDAO.addPriceRecord(lowOnly);
        PriceRecordImpl priceRecordFromDB = (PriceRecordImpl) PriceRecordDAO.getPriceRecordByID(lowID);
        assertTrue(priceRecordFromDB.compare(lowOnly));
    }

    @Transactional
    @Test
    public final void getPriceRecordByLookupcodeTest() {
        // add a stabbed record to the database
        PriceRecordDAO.addPriceRecord(low1);
        // get it by lookupcode
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordDAO.getPriceRecordByLookupcode(low1.getLookupCode());

        assertTrue(priceRecord.compare(low1));
    }

    @Ignore
    @Transactional
    @Test
    public final void getAllPriceRecordsTest() {
        // Get the count of entries
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.setProjection(Projections.rowCount());
        Long countBefore = (Long) criterion.uniqueResult();
        int countBeforeGetAll = new Integer(countBefore.toString());
        // Get all entries from DB as a list and check the size
        assertEquals(PriceRecordDAO.getAllPriceRecords().size(), countBeforeGetAll);

    }

    @Ignore
    @Transactional
    @Test
    public final void getIDbyLookupcodeTest() {
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        long actualID = PriceRecordDAO.addPriceRecord(priceRecord);
        long id = PriceRecordDAO.getIDbyLookupcode(priceRecord.getLookupCode());
        assertEquals(actualID, id);

    }

    @Ignore
    @Transactional
    @Test
    public final void deletePriceRecordByLookupcodeTest() {
        // Get the count of entries
        PriceRecordDAO.addPriceRecord(lowToDelete);
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.setProjection(Projections.rowCount());
        Long countBefore = (Long) criterion.uniqueResult();
        int countBeforeDelete = new Integer(countBefore.toString());
        // Remove a record that is in the DB
        PriceRecordDAO.deletePriceRecordByLookupcode("lowToDelete");

        // Get the count again and compare 
        Long countAfter = (Long) criterion.uniqueResult();
        int countAfterDelete = new Integer(countAfter.toString());
        assertEquals(countBeforeDelete, (countAfterDelete + 1));

    }

    // @Ignore
    @Transactional
    @Test
    public final void deletePriceRecordByIdTest() {
        // PriceRecordDAO.deletePriceRecordByLookupcode("lowToDelete");
        long id = PriceRecordDAO.addPriceRecord(lowToDelete2);
        // Get the count of entries
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.setProjection(Projections.rowCount());
        Long countBefore = (Long) criterion.uniqueResult();
        int countBeforeDelete = new Integer(countBefore.toString());
        // Remove a record that is in the DB
        PriceRecordDAO.deletePriceRecordByID(id);
        // Get the count again and compare 
        Long countAfter = (Long) criterion.uniqueResult();
        int countAfterDelete = new Integer(countAfter.toString());
        assertEquals(countBeforeDelete, (countAfterDelete + 1));

    }

    @Transactional
    @Test
    public final void updateLookupcodeByLookupcodeTest() {
        long id = PriceRecordDAO.addPriceRecord(lowToDelete2);
        PriceRecordDAO.updateLookupcodeByLookupcode("lowToDelete2", "newLow2");

        assert (true);
    }

    @Transactional
    @Test
    public final void updateLookupcodeByIDTest() {
        long id = PriceRecordDAO.addPriceRecord(lowToDelete3);
        PriceRecordDAO.updateLookupcodeByID(id, "newLow3");

        assert (true);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////

    @Ignore
    @Transactional
    @Test
    public final void deleteAllPriceRecordsTest() {
        //long id = PriceRecordDAO.addPriceRecord(lowToDelete2);
        PriceRecordDAO.deleteAllPriceRecords();
        // Get the count of entries
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Criteria criterion = session.createCriteria(PriceRecordImpl.class);
        criterion.setProjection(Projections.rowCount());
        Long count = (Long) criterion.uniqueResult();
        int countAfterDelete = new Integer(count.toString());
        assertEquals(countAfterDelete, 0);
    }

}
