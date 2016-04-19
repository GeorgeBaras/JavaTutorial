package com.apakgroup.training.tutorial.model;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.xml.XMLcreation;

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
        //sessionFactory.getCurrentSession();
        Session session = sessionFactory.getCurrentSession();
        List priceRecordsFromDB = session.createCriteria(PriceRecordImpl.class).list();
        return priceRecordsFromDB;
    }

    // Update
    // update specific Record's fields

    // Delete
    // delete priceRecord by ID, by lookupcode

    // delete priceBand from PriceRecord

}
