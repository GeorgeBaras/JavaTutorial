//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.25 at 02:55:09 PM BST 
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
 *         &lt;element name="priceRecords" type="{http://localhost:8080/webservice/}PriceRecordListWire"/>
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
    "priceRecords"
})
@XmlRootElement(name = "addPriceRecordListRequest")
public class AddPriceRecordListRequest {

    @XmlElement(required = true)
    protected PriceRecordListWire priceRecords;

    /**
     * Gets the value of the priceRecords property.
     * 
     * @return
     *     possible object is
     *     {@link PriceRecordListWire }
     *     
     */
    public PriceRecordListWire getPriceRecords() {
        return priceRecords;
    }

    /**
     * Sets the value of the priceRecords property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceRecordListWire }
     *     
     */
    public void setPriceRecords(PriceRecordListWire value) {
        this.priceRecords = value;
    }

}