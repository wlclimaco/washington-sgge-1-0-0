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


/**
 * <p>Java class for SmartpointStateLevel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SmartpointStateLevel">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.sensus.com/gateway/mlc/types}Smartpoint">
 *       &lt;sequence>
 *         &lt;element name="lightTopLevelState" type="{http://www.sensus.com/gateway/mlc/types}LightTopLevelState"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmartpointStateLevel", namespace = "http://www.sensus.com/gateway/mlc/types", propOrder = {
    "lightTopLevelState"
})
public class SmartpointStateLevel
    extends Smartpoint
{

    @XmlElement(required = true)
    protected LightTopLevelState lightTopLevelState;

    /**
     * Gets the value of the lightTopLevelState property.
     * 
     * @return
     *     possible object is
     *     {@link LightTopLevelState }
     *     
     */
    public LightTopLevelState getLightTopLevelState() {
        return lightTopLevelState;
    }

    /**
     * Sets the value of the lightTopLevelState property.
     * 
     * @param value
     *     allowed object is
     *     {@link LightTopLevelState }
     *     
     */
    public void setLightTopLevelState(LightTopLevelState value) {
        this.lightTopLevelState = value;
    }

}