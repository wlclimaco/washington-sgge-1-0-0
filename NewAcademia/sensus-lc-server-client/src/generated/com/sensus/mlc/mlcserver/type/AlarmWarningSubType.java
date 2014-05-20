//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 09:59:10 AM BRT 
//


package com.sensus.mlc.mlcserver.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AlarmWarningSubType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AlarmWarningSubType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="0"/>
 *     &lt;enumeration value="1"/>
 *     &lt;enumeration value="2"/>
 *     &lt;enumeration value="3"/>
 *     &lt;enumeration value="4"/>
 *     &lt;enumeration value="5"/>
 *     &lt;enumeration value="6"/>
 *     &lt;enumeration value="7"/>
 *     &lt;enumeration value="8"/>
 *     &lt;enumeration value="9"/>
 *     &lt;enumeration value="10"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlarmWarningSubType", namespace = "http://www.sensus.com/gateway/mlc/types")
@XmlEnum
public enum AlarmWarningSubType {

    @XmlEnumValue("0")
    LAMP_FAILURE("0"),
    @XmlEnumValue("1")
    POWER_FAILURE("1"),
    @XmlEnumValue("2")
    POWER_SURGE_DETECTED("2"),
    @XmlEnumValue("3")
    BROWN_OUT_DETECTED("3"),
    @XmlEnumValue("4")
    METROLOGY_ERROR("4"),
    @XmlEnumValue("5")
    METROLOGY_COM_FAILURE("5"),
    @XmlEnumValue("6")
    HIGH_CURRENT("6"),
    @XmlEnumValue("7")
    LOW_CURRENT("7"),
    @XmlEnumValue("8")
    REVERSE_ENERGY("8"),
    @XmlEnumValue("9")
    METROLOGY_RESET("9"),
    @XmlEnumValue("10")
    BOARD_FAILURE("10");
    private final String value;

    AlarmWarningSubType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlarmWarningSubType fromValue(String v) {
        for (AlarmWarningSubType c: AlarmWarningSubType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}