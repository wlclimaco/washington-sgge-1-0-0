//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.16 at 10:11:41 AM CST 
//


package com.prosperitasglobal.sendsolv.sdn.model.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="callSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vesselType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vesselFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vesselOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tonnage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="grossRegisteredTonnage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "callSign",
    "vesselType",
    "vesselFlag",
    "vesselOwner",
    "tonnage",
    "grossRegisteredTonnage"
})
public class VesselInfo {

    protected String callSign;
    protected String vesselType;
    protected String vesselFlag;
    protected String vesselOwner;
    protected Integer tonnage;
    protected Integer grossRegisteredTonnage;

    /**
     * Gets the value of the callSign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallSign() {
        return callSign;
    }

    /**
     * Sets the value of the callSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallSign(String value) {
        this.callSign = value;
    }

    /**
     * Gets the value of the vesselType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVesselType() {
        return vesselType;
    }

    /**
     * Sets the value of the vesselType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVesselType(String value) {
        this.vesselType = value;
    }

    /**
     * Gets the value of the vesselFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVesselFlag() {
        return vesselFlag;
    }

    /**
     * Sets the value of the vesselFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVesselFlag(String value) {
        this.vesselFlag = value;
    }

    /**
     * Gets the value of the vesselOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVesselOwner() {
        return vesselOwner;
    }

    /**
     * Sets the value of the vesselOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVesselOwner(String value) {
        this.vesselOwner = value;
    }

    /**
     * Gets the value of the tonnage property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTonnage() {
        return tonnage;
    }

    /**
     * Sets the value of the tonnage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTonnage(Integer value) {
        this.tonnage = value;
    }

    /**
     * Gets the value of the grossRegisteredTonnage property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGrossRegisteredTonnage() {
        return grossRegisteredTonnage;
    }

    /**
     * Sets the value of the grossRegisteredTonnage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGrossRegisteredTonnage(Integer value) {
        this.grossRegisteredTonnage = value;
    }

}
