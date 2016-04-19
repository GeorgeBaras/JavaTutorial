package com.apakgroup.training.tutorial.model;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.xml.XMLcreation;

@Repository
public class PriceRecordDAO {

    // private static final Logger LOGGER = LoggerFactory.getLogger(PriceRecordDAO.class);

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        PriceRecordDAO.sessionFactory = sessionFactory;
    }

    // Create
    // Add priceRecord

    public static long addPriceRecord(PriceRecord priceRecord) {
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        long identifier = (long) session.save(priceRecord);
        //session.persist(priceRecord);
        session.flush();
        return identifier;
    }

    // Add priceRecordList
    public static void addPriceRecordList(PriceRecordList priceRecordList) {
        for (PriceRecord priceRecord : priceRecordList.getListOfPriceRecords()) {
            PriceRecordDAO.addPriceRecord(priceRecord);
        }
    }

    // Add list of PriceRecords
    public static void addListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        for (PriceRecord priceRecord : listOfPriceRecords) {
            PriceRecordDAO.addPriceRecord(priceRecord);
        }
    }

    // Add priceRecord from xml file
    public static void addPriceRecordsFromXMLFile(File file) throws JAXBException {
        // call the unmarshaller for the file given as parameter
        PriceRecordList priceRecordList = (PriceRecordList) XMLcreation.unmarshalFromXMLfileJAXB(file,
                PriceRecordList.class);
        for (PriceRecord priceRecord : priceRecordList.getListOfPriceRecords()) {
            PriceRecordDAO.addPriceRecord(priceRecord);
        }
    }

    // Read
    // get priceRecord by lookupcode
    public static PriceRecord getPriceRecordByLookupcode(String lookupcode) {
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        List priceRecordsFromDB = session.createCriteria(PriceRecordImpl.class)
                .add(Restrictions.like("lookupCode", lookupcode)).list();
        return (PriceRecord) priceRecordsFromDB.get(0);
    }

    // get priceRecord by id
    public static PriceRecord getPriceRecordByID(long ID) {
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        List priceRecordsFromDB = session.createCriteria(PriceRecordImpl.class).add(Restrictions.like("id", ID)).list();
        return (PriceRecord) priceRecordsFromDB.get(0);
    }

    // get all priceRecords
    public static List<PriceRecordImpl> getAllPriceRecords() {
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        List priceRecordsFromDB = session.createCriteria(PriceRecordImpl.class).list();
        return priceRecordsFromDB;
    }

    public static long getIDbyLookupcode(String lookupcode) {
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        PriceRecordImpl priceRecord = (PriceRecordImpl) PriceRecordDAO.getPriceRecordByLookupcode(lookupcode);

        return priceRecord.getID();
    }

    // Update
    // Update lookupCode by lookupcode  WORKING
    public static void updateLookupcodeByLookupcode(String oldLookupcode, String newLookupcode) {
        // Prep work to load the required record
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class,
                PriceRecordDAO.getIDbyLookupcode(oldLookupcode));
        transaction.commit();

        // Update
        Transaction transaction1 = session.beginTransaction();
        priceRecord.setLookupCode(newLookupcode);
        session.update(priceRecord);
        transaction1.commit();
    }

    // Update lookupCode by id  
    public static void updateLookupcodeByID(long id, String newLookupcode) {
        // HQL NOT WORKING/ NOT COMING UP WITH ERROR EITHER
        //        Query query = sessionFactory.getCurrentSession()
        //                .createQuery("update PriceRecordImpl set lookupCode = :lookupCode where id = :id");
        //        query.setParameter("lookupCode", newLookupcode);
        //        query.setParameter("id", id);
        //        query.executeUpdate();
        // Prep work to load the required record
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        transaction.commit();

        // Update
        Transaction transaction1 = session.beginTransaction();
        priceRecord.setLookupCode(newLookupcode);
        session.update(priceRecord);
        transaction1.commit();

    }

    // Add priceBand by lookupcode
    public static void addPriceBandByLookupcode(PriceBand priceBand, String lookupcode) {
        // Prep work to load the required record
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class,
                PriceRecordDAO.getIDbyLookupcode(lookupcode));
        transaction.commit();

        // Update
        Transaction transaction1 = session.beginTransaction();
        priceRecord.addPriceBand(priceBand);
        session.update(priceRecord);
        transaction1.commit();
    }

    // Add priceBand by id
    public static void addPriceBandByID(PriceBand priceBand, long id) {
        // Prep work to load the required record
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        transaction.commit();

        // Update
        Transaction transaction1 = session.beginTransaction();
        priceRecord.addPriceBand(priceBand);
        session.update(priceRecord);
        transaction1.commit();

    }

    // Remove priceBand by lookupcode
    public static void removePriceBandByLookupcode(String lookupcode) {
        // Prep work to load the required record
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class,
                PriceRecordDAO.getIDbyLookupcode(lookupcode));
        transaction.commit();

        // Update
        Transaction transaction1 = session.beginTransaction();
        priceRecord.removeLastPriceBand();
        session.update(priceRecord);
        transaction1.commit();
    }

    // Remove priceBand by id
    public static void removePriceBandByID(long id) {
        // Prep work to load the required record
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        transaction.commit();

        // Update
        Transaction transaction1 = session.beginTransaction();
        priceRecord.removeLastPriceBand();
        session.update(priceRecord);
        transaction1.commit();
    }

    // Delete
    // delete priceRecord by lookupcode
    public static void deletePriceRecordByLookupcode(String lookupcode) {
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        long id = PriceRecordDAO.getIDbyLookupcode(lookupcode);
        PriceRecordImpl priceRecordToDelete = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        session.delete(priceRecordToDelete);
        session.flush();

    }

    // delete priceRecord by id
    public static void deletePriceRecordByID(long id) {
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        PriceRecordImpl priceRecordToDelete = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        session.delete(priceRecordToDelete);
        session.flush();

    }

    // delete list of priceRecords
    public static void deleteListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        for (PriceRecord priceRecord : listOfPriceRecords) {
            PriceRecordDAO.deletePriceRecordByLookupcode(priceRecord.getLookupCode());
        }
    }

    // delete all priceRecords
    public static void deleteAllPriceRecords() {
        sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();

        session.createQuery("delete from PriceBandImpl").executeUpdate();

        session.flush();

    }
}
