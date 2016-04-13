package com.apakgroup.training.tutorial.pricing;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apakgroup.training.tutorial.model.PriceRecordImpl;

@XmlRootElement(name = "PriceRecordsList")
public class PriceRecordList {

    private List<PriceRecord> listOfPriceRecords;

    public PriceRecordList() {
    }

    public PriceRecordList(List<PriceRecord> listOfPriceRecords) {
        this.listOfPriceRecords = listOfPriceRecords;
    }

    @XmlElement(type = PriceRecordImpl.class)
    public List<PriceRecord> getListOfPriceRecords() {
        return listOfPriceRecords;
    }

    public void setListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        this.listOfPriceRecords = listOfPriceRecords;
    }

}
