//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.06 at 02:16:37 PM BST 
//


package com.apakgroup.training.tutorial.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="priceRecord" type="{com.apakgroup.training.tutorial.webservice}PriceRecordWire"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "priceRecord"
})
@XmlRootElement(name = "addPriceRecordRequest")
public class AddPriceRecordRequest {

    @XmlElement(required = true)
    protected PriceRecordWire priceRecord;

    /**
     * Gets the value of the priceRecord property.
     * 
     * @return
     *     possible object is
     *     {@link PriceRecordWire }
     *     
     */
    public PriceRecordWire getPriceRecord() {
        return priceRecord;
    }

    /**
     * Sets the value of the priceRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceRecordWire }
     *     
     */
    public void setPriceRecord(PriceRecordWire value) {
        this.priceRecord = value;
    }

}
