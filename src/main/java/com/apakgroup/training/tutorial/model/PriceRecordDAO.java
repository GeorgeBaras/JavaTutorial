package com.apakgroup.training.tutorial.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.apakgroup.training.tutorial.pricing.PriceBand;
import com.apakgroup.training.tutorial.pricing.PriceRecord;
import com.apakgroup.training.tutorial.xml.XMLcreation;

@Repository
public class PriceRecordDAO {

    //private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PriceRecordDAO.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public PriceRecordDAO() {
    }

    // Create

    public void flushAndClear() {
        Session session = sessionFactory.getCurrentSession();
        session.flush();
        session.clear();
    }

    public long addPriceRecord(PriceRecord priceRecord) {
        long identifier = (long) sessionFactory.getCurrentSession().save(priceRecord);
        //LOGGER.info("PriceRecord saved to database");
        return identifier;
    }

    public ArrayList<Long> addPriceRecordList(PriceRecordList priceRecordList) {
        ArrayList<Long> identifiers = new ArrayList<>();
        for (PriceRecord priceRecord : priceRecordList.getListOfPriceRecords()) {
            identifiers.add(addPriceRecord(priceRecord));
        }
        return identifiers;
    }

    public ArrayList<Long> addListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        ArrayList<Long> identifiers = new ArrayList<>();
        for (PriceRecord priceRecord : listOfPriceRecords) {
            identifiers.add(addPriceRecord(priceRecord));
        }
        return identifiers;
    }

    // Add priceRecord from xml file
    public ArrayList<Long> addPriceRecordsFromXMLFile(File file) throws JAXBException {
        // call the unmarshaller for the file given as parameter
        PriceRecordList priceRecordList = (PriceRecordList) XMLcreation.unmarshalFromXMLfileJAXB(file,
                PriceRecordList.class);
        ArrayList<Long> identifiers = new ArrayList<>();
        for (PriceRecord priceRecord : priceRecordList.getListOfPriceRecords()) {
            identifiers.add(addPriceRecord(priceRecord));
        }
        return identifiers;
    }

    // get priceRecord by lookupcode
    public PriceRecord getPriceRecordByLookupcode(String lookupcode) {
        List priceRecordsFromDB = sessionFactory.getCurrentSession().createCriteria(PriceRecordImpl.class)
                .add(Restrictions.like("lookupCode", lookupcode)).list();
        //LOGGER.info("PriceRecord retrieved from the database");
        return (PriceRecord) priceRecordsFromDB.get(0);
    }

    // get priceRecord by id
    public PriceRecord getPriceRecordByID(long ID) {
        List priceRecordsFromDB = sessionFactory.getCurrentSession().createCriteria(PriceRecordImpl.class)
                .add(Restrictions.like("id", ID)).list();
        //LOGGER.info("PriceRecord retrieved from the database");
        return (PriceRecord) priceRecordsFromDB.get(0);
    }

    // get all priceRecords
    public List<PriceRecordImpl> getAllPriceRecords() {
        List priceRecordsFromDB = sessionFactory.getCurrentSession().createCriteria(PriceRecordImpl.class).list();
        //LOGGER.info("PriceRecords retrieved from the database");
        return priceRecordsFromDB;
    }

    public long getIDbyLookupcode(String lookupcode) {
        PriceRecordImpl priceRecord = (PriceRecordImpl) getPriceRecordByLookupcode(lookupcode);
        //LOGGER.info("PriceRecord retrieved from the database");
        return priceRecord.getID();
    }

    public void updateLookupcodeByLookupcode(String oldLookupcode, String newLookupcode) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("update PriceRecordImpl set lookupCode = :lookupCode where lookupCode = :oldcode");
        query.setParameter("lookupCode", newLookupcode);
        query.setParameter("oldcode", oldLookupcode);
        query.executeUpdate();
        flushAndClear();
        //LOGGER.info("PriceRecordImpl.lookupCode updated");
    }

    public void updateLookupcodeByID(long id, String newLookupcode) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("update PriceRecordImpl set lookupCode = :lookupCode where id = :id");
        query.setParameter("lookupCode", newLookupcode);
        query.setParameter("id", id);
        query.executeUpdate();
        flushAndClear();
        //LOGGER.info("PriceRecordImpl.lookupCode updated");
    }

    public void addPriceBandByLookupcode(PriceBand priceBand, String lookupcode) {
        Session session = sessionFactory.getCurrentSession();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class,
                getIDbyLookupcode(lookupcode));
        priceRecord.addPriceBand(priceBand);
        session.update(priceRecord);
        //LOGGER.info("PriceBand added to the database");
    }

    public void addPriceBandByID(PriceBand priceBand, long id) {
        Session session = sessionFactory.getCurrentSession();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        priceRecord.addPriceBand(priceBand);
        session.update(priceRecord);
        //LOGGER.info("PriceBand added to the database");
    }

    public void removeLastPriceBandByLookupcode(String lookupcode) {
        Session session = sessionFactory.getCurrentSession();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class,
                getIDbyLookupcode(lookupcode));
        priceRecord.removeLastPriceBand();
        session.update(priceRecord);
        //LOGGER.info("PriceBand removed from the database");
    }

    public void removeLastPriceBandByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        PriceRecordImpl priceRecord = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        priceRecord.removeLastPriceBand();
        session.update(priceRecord);
        //LOGGER.info("PriceBand removed from the database");
    }

    public void deletePriceRecordByLookupcode(String lookupcode) {
        Session session = sessionFactory.getCurrentSession();
        long id = getIDbyLookupcode(lookupcode);
        PriceRecordImpl priceRecordToDelete = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        session.delete(priceRecordToDelete);
        //LOGGER.info("PriceRecord removed from the database");
    }

    public void deletePriceRecordByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        PriceRecordImpl priceRecordToDelete = (PriceRecordImpl) session.load(PriceRecordImpl.class, id);
        session.delete(priceRecordToDelete);
        //LOGGER.info("PriceRecord removed from the database");
    }

    public void deleteListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        for (PriceRecord priceRecord : listOfPriceRecords) {
            deletePriceRecordByLookupcode(priceRecord.getLookupCode());
        }
    }

    public void deleteAllPriceRecords() {
        for (PriceRecord priceRecord : getAllPriceRecords()) {
            deletePriceRecordByLookupcode(priceRecord.getLookupCode());
        }

    }

}
