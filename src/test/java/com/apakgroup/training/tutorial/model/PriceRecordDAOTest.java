package com.apakgroup.training.tutorial.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.xml.PriceRecordsGenerators;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PriceRecordDAOTest {

    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private PriceRecord lowOnly;

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
    public final void getPriceRecordByLookupcodeTest() {
        // add a stabbed record to the database
        PriceRecordDAO.addPriceRecord(lowOnly);
        // get it by lookupcode
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordDAO
                .getPriceRecordByLookupcode(lowOnly.getLookupCode());
        assertTrue(priceRecord.compare(lowOnly));
    }

    @Transactional
    @Test
    public final void getPriceRecordByIDTest() {
        // add a stabbed record to the database and get its generated identifier
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordsGenerators.priceRecordGenerator(1);
        long identifier = PriceRecordDAO.addPriceRecord(priceRecord);
        // Retrieve it and compare
        PriceRecordImpl priceRecordFromDB = (PriceRecordImpl) PriceRecordDAO.getPriceRecordByID(identifier);
        assertTrue(priceRecordFromDB.compare(priceRecord));
    }

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

}
