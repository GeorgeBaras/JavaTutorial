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

    public PriceRecordList(PriceRecord priceRecord) {
        this.listOfPriceRecords.add(priceRecord);
    }

    @XmlElement(type = PriceRecordImpl.class)
    public List<PriceRecord> getListOfPriceRecords() {
        return listOfPriceRecords;
    }

    public void setListOfPriceRecords(List<PriceRecord> listOfPriceRecords) {
        this.listOfPriceRecords = listOfPriceRecords;
    }

    /**
     * Compare two priceRecordLists
     * 
     * @param priceRecordList
     * @return
     */
    public boolean compare(PriceRecordList priceRecordList) {
        boolean isSame = true;
        // for every priceRecord in the list
        for (int i = 0; i < this.listOfPriceRecords.size(); i++) {
            // for every priceBand in the priceRecord
            for (int j = 0; j < this.listOfPriceRecords.get(i).getPriceBands().size(); j++) {
                // if the mileage or the valuation of the priceBand do not match for the two lists set the siSame to false
                if (this.listOfPriceRecords.get(i).getPriceBands().get(j).getMileage() != priceRecordList.get(i)
                        .getPriceBands().get(j).getMileage()) {
                    isSame = false;
                }
                if (this.listOfPriceRecords.get(i).getPriceBands().get(j).getValuation()
                        .compareTo(priceRecordList.get(i).getPriceBands().get(j).getValuation()) != 0) {
                    isSame = false;
                }
            }
        }
        return isSame;
    }

    private PriceRecordImpl get(int i) {

        return (PriceRecordImpl) this.listOfPriceRecords.get(i);
    }

}
