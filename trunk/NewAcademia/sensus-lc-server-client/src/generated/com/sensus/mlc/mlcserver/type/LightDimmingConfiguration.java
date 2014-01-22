//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 09:59:10 AM BRT 
//


package com.sensus.mlc.mlcserver.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for LightDimmingConfiguration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LightDimmingConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hardwareSetting" type="{http://www.sensus.com/gateway/mlc/types}Integer"/>
 *         &lt;element name="currentScale" type="{http://www.sensus.com/gateway/mlc/types}Integer"/>
 *         &lt;element name="fullOnRequired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lightLevel" type="{http://www.sensus.com/gateway/mlc/types}LightLevel"/>
 *         &lt;element name="blinkSlotTime" type="{http://www.sensus.com/gateway/mlc/types}Integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LightDimmingConfiguration", namespace = "http://www.sensus.com/gateway/mlc/types", propOrder = {
    "hardwareSetting",
    "currentScale",
    "fullOnRequired",
    "lightLevel",
    "blinkSlotTime"
})
public class LightDimmingConfiguration {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    protected Integer hardwareSetting;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    protected Integer currentScale;
    protected boolean fullOnRequired;
    @XmlElement(required = true)
    protected LightLevel lightLevel;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    protected Integer blinkSlotTime;

    /**
     * Gets the value of the hardwareSetting property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getHardwareSetting() {
        return hardwareSetting;
    }

    /**
     * Sets the value of the hardwareSetting property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHardwareSetting(Integer value) {
        this.hardwareSetting = value;
    }

    /**
     * Gets the value of the currentScale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getCurrentScale() {
        return currentScale;
    }

    /**
     * Sets the value of the currentScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentScale(Integer value) {
        this.currentScale = value;
    }

    /**
     * Gets the value of the fullOnRequired property.
     * 
     */
    public boolean isFullOnRequired() {
        return fullOnRequired;
    }

    /**
     * Sets the value of the fullOnRequired property.
     * 
     */
    public void setFullOnRequired(boolean value) {
        this.fullOnRequired = value;
    }

    /**
     * Gets the value of the lightLevel property.
     * 
     * @return
     *     possible object is
     *     {@link LightLevel }
     *     
     */
    public LightLevel getLightLevel() {
        return lightLevel;
    }

    /**
     * Sets the value of the lightLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link LightLevel }
     *     
     */
    public void setLightLevel(LightLevel value) {
        this.lightLevel = value;
    }

    /**
     * Gets the value of the blinkSlotTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getBlinkSlotTime() {
        return blinkSlotTime;
    }

    /**
     * Sets the value of the blinkSlotTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlinkSlotTime(Integer value) {
        this.blinkSlotTime = value;
    }

}
