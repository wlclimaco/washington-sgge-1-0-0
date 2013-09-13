package com.sensus.cbof.model;

import java.math.BigInteger;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class Device.
 */
@SuppressWarnings("serial")
public class Device extends SensusModel
{

    /** The device id. */
    private String deviceId;

    /** The radio. */
    private Radio radio;

    /** The device type. */
    private DeviceTypeEnum deviceType;

    /** The device state. */
    private IDeviceLifeCycleState deviceLifeCycleState;

    /** The notes. */
    private List<Note> notes;

    // -------------------------------------------------------------------------
    // Constructors:

    /**
     * Instantiates a new device.
     */
    public Device()
    {
    }

    /**
     * Instantiates a new device.
     *
     * @param deviceTypeParam the device type  param
     */
    public Device(DeviceTypeEnum deviceTypeParam)
    {
        setDeviceType(deviceTypeParam);
    }

    /**
     * Instantiates a new device.
     *
     * @param deviceIdParam the device id param
     * @param deviceTypeParam the device type  param
     */
    public Device(String deviceIdParam, DeviceTypeEnum deviceTypeParam)
    {
        setDeviceId(deviceIdParam);
        setDeviceType(deviceTypeParam);
    }

    /**
     * Instantiates a new device.
     *
     * @param electricMeterFlexNetId the electric meter flex net id
     * @param deviceTypeParam the device type  param
     */
    public Device(BigInteger electricMeterFlexNetId, DeviceTypeEnum deviceTypeParam)
    {
        setRadio(new Radio(electricMeterFlexNetId));
        setDeviceType(deviceTypeParam);
    }

    /**
     * Instantiates a new device.
     *
     * @param deviceIdParam the device id
     */
    public Device(String deviceIdParam)
    {
        setDeviceId(deviceIdParam);
    }

    /**
     * Instantiates a new device.
     *
     * @param radioParam the radio
     */
    public Device(Radio radioParam)
    {
        super();
        setRadio(radioParam);
    }

    /**
     * Instantiates a new device.
     *
     * @param radioParam the radio param
     * @param deviceTypeParam the device type param
     */
    public Device(Radio radioParam, DeviceTypeEnum deviceTypeParam)
    {
        super();
        setDeviceType(deviceTypeParam);
        setRadio(radioParam);
    }

    /**
     * Instantiates a new device.
     *
     * @param deviceIdParam the device id param
     * @param radioParam the radio param
     * @param deviceTypeParam the device type param
     */
    public Device(String deviceIdParam, Radio radioParam, DeviceTypeEnum deviceTypeParam)
    {
        super();
        setDeviceId(deviceIdParam);
        setDeviceType(deviceTypeParam);
        setRadio(radioParam);
    }

    // -------------------------------------------------------------------------
    // Getters and setters:

    /**
     * Gets the device id.
     *
     * @return the device id
     */
    public String getDeviceId()
    {
        return deviceId;
    }

    /**
     * Sets the device id.
     *
     * @param deviceId the new device id
     */
    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    /**
     * Gets the radio.
     *
     * @return the radio
     */
    public Radio getRadio()
    {
        return radio;
    }

    /**
     * Sets the radio.
     *
     * @param radio the new radio
     */
    public void setRadio(Radio radio)
    {
        this.radio = radio;
    }

    /**
     * Gets the device type.
     *
     * @return the device type
     */
    public DeviceTypeEnum getDeviceType()
    {
        return deviceType;
    }

    /**
     * Sets the device type.
     *
     * @param deviceType the new device type enum
     */
    public void setDeviceType(DeviceTypeEnum deviceType)
    {
        this.deviceType = deviceType;
    }

    /**
     * Gets the device type value.
     *
     * @return the device type value
     */
    public Integer getDeviceTypeValue()
    {
        if (getDeviceType() != null)
        {
            return getDeviceType().getValue();
        }

        return null;
    }

    /**
     * Sets the device type value.
     *
     * @param deviceTypeParam the new device type value
     */
    public void setDeviceTypeValue(Integer deviceTypeParam)
    {
        setDeviceType(DeviceTypeEnum.enumForValue(deviceTypeParam));
    }

    /**
     * Gets the notes.
     *
     * @return the notes
     */
    public List<Note> getNotes()
    {
        return notes;
    }

    /**
     * Sets the notes.
     *
     * @param notes the new notes
     */
    public void setNotes(List<Note> notes)
    {
        this.notes = notes;
    }

    /**
     * Gets the device life cycle state.
     *
     * @return the device life cycle state
     */
    public IDeviceLifeCycleState getDeviceLifeCycleState()
    {
        return deviceLifeCycleState;
    }

    /**
     * Sets the device state.
     *
     * @param deviceLifeCycleState the new device state
     */
    public void setDeviceLifeCycleState(IDeviceLifeCycleState deviceLifeCycleState)
    {
        this.deviceLifeCycleState = deviceLifeCycleState;
    }

    /*
     * (non-Javadoc)
     * @see com.sensus.common.model.SensusModel#toString()
     */
    @Override
    public String toString()
    {
        return "Device [getDeviceId()=" + getDeviceId() + ", getRadio()=" + getRadio() + ", getDeviceType()="
                + getDeviceType() + ", getNotes()=" + getNotes() + ", getDeviceState()=" + getDeviceLifeCycleState()
                + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
                + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
                + ", getModifyDate()=" + getModifyDate() + "]";
    }

}
