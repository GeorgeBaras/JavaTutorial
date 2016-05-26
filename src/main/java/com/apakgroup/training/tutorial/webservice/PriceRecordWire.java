//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.25 at 02:55:09 PM BST 
//


package com.apakgroup.training.tutorial.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PriceRecordWire complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PriceRecordWire">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="lookUpCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element name="priceBands" type="{http://localhost:8080/webservice/}PriceBandWire" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PriceRecordWire", propOrder = {
    "id",
    "lookUpCode",
    "priceBands"
})
public class PriceRecordWire {

    protected long id;
    protected String lookUpCode;
    protected List<PriceBandWire> priceBands;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the lookUpCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLookUpCode() {
        return lookUpCode;
    }

    /**
     * Sets the value of the lookUpCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLookUpCode(String value) {
        this.lookUpCode = value;
    }

    /**
     * Gets the value of the priceBands property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the priceBands property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPriceBands().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PriceBandWire }
     * 
     * 
     */
    public List<PriceBandWire> getPriceBands() {
        if (priceBands == null) {
            priceBands = new ArrayList<PriceBandWire>();
        }
        return this.priceBands;
    }

}
