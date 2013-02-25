package com.sensus.mlc.wui.smartpoint.model;

import java.io.Serializable;
import java.util.List;

import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.LightStateEnum;

/**
 * Atributes for SmartpointDetailPageAction.
 * 
 * @author Jose Carlos Pereira
 */

@SuppressWarnings("serial")
public class ViewLight implements Serializable
{

	/** The sunset time. */
	private String sunsetTime;

	/** The sunset time tz. */
	private String sunsetTimeTz;

	/** The sunset offset. */
	private String sunsetOffset;

	/** The sunrise time. */
	private String sunriseTime;

	/** The sunrise time tz. */
	private String sunriseTimeTz;

	/** The sunrise offset. */
	private String sunriseOffset;

	/** The serial. */
	private String lightDriverSerial;

	/** The lamp type. */
	private String lampType;

	/** The input voltage range. */
	private String inputVoltageRange;

	/** The temperature color. */
	private String temperatureColor;

	/** The sensus part number. */
	private String sensusPartNumber;

	/** The bulb serial number. */
	private String bulbSerialNumber;

	/** The lower serial number. */
	private String lowerSerialNumber;

	/** The upper serial number. */
	private String upperSerialNumber;

	/** The pole id. */
	private String poleId;

	/** The date added. */
	private String dateAdded;

	/** The date installed. */
	private String dateInstalled;

	/** The firmware. */
	private String firmware;

	/** The latitude. */
	private String latitude;

	/** The longitude. */
	private String longitude;

	/** The Status reason enum value. */
	private String statusReasonEnumValue;

	/** The Status Message Date. */
	private String statusMessageDate;

	/** The status alerts message date. */
	private String statusAlertsMessageDate;

	/** The Status Message Date Hour. */
	private String statusMessageDateHour;

	/** The status message date tz. */
	private String statusMessageDateTz;

	/** Light RNI Id. */
	private String rniId;

	/** The wattage. */
	private String wattage;

	/** The manufacturer. */
	private String manufacturer;

	/*** List Light Alerts. */
	private List<ViewExceptionMessage> statusExceptions;

	/** The smartpoint history. */
	private List<ViewSmartpointHistory> smartpointHistoryViewList;

	/** The current alarm warning message list. */
	private List<CurrentAlarmWarningMessage> currentAlarmWarningMessageList;

	/** The light intensitylevels. */
	private List<String> lightIntensitylevels;

	/** The id. */
	private String id;

	/** The state light. */
	private LightStateEnum stateLight;

	/** The offset Schedule. */
	private ViewOffsetSchedule offsetSchedule;

	/** The event Schedule. */
	private ViewEventSchedule eventSchedule;

	/** The voltage last. */
	private String voltageLast;

	/** The voltage min. */
	private String voltageMin;

	/** The voltage max. */
	private String voltageMax;

	/** The current last. */
	private String currentLast;

	/** The current min. */
	private String currentMin;

	/** The current max. */
	private String currentMax;

	/** The consumption last. */
	private String consumptionLast;

	/** The consumption min. */
	private String consumptionMin;

	/** The consumption max. */
	private String consumptionMax;

	/** The address. */
	private String address;

	/** The alert today. */
	private Boolean messageToday = false;

	/** The is protected. */
	private Boolean isProtected;

	/** The light intensity. */
	private String lightIntensity;

	/** The housing. */
	private String housing;

	/** The dimmable. */
	private String dimmable;

	/** The housing color. */
	private String housingColor;

	/** The light status. */
	private Integer lightStatus;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public String getLatitude()
	{
		return latitude;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude the new latitude
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * Gets the sunset time.
	 * 
	 * @return the sunset time
	 */
	public String getSunsetTime()
	{
		return sunsetTime;
	}

	/**
	 * Sets the sunset time.
	 * 
	 * @param sunsetTime the new sunset time
	 */
	public void setSunsetTime(String sunsetTime)
	{
		this.sunsetTime = sunsetTime;
	}

	/**
	 * Gets the sunset time tz.
	 * 
	 * @return the sunset time tz
	 */
	public String getSunsetTimeTz()
	{
		return sunsetTimeTz;
	}

	/**
	 * Sets the sunset time tz.
	 * 
	 * @param sunsetTimeTz the new sunset time tz
	 */
	public void setSunsetTimeTz(String sunsetTimeTz)
	{
		this.sunsetTimeTz = sunsetTimeTz;
	}

	/**
	 * Gets the sunset offset.
	 * 
	 * @return the sunset offset
	 */
	public String getSunsetOffset()
	{
		return sunsetOffset;
	}

	/**
	 * Sets the sunset offset.
	 * 
	 * @param sunsetOffset the new sunset offset
	 */
	public void setSunsetOffset(String sunsetOffset)
	{
		this.sunsetOffset = sunsetOffset;
	}

	/**
	 * Gets the sunrise time.
	 * 
	 * @return the sunrise time
	 */
	public String getSunriseTime()
	{
		return sunriseTime;
	}

	/**
	 * Sets the sunrise time.
	 * 
	 * @param sunriseTime the new sunrise time
	 */
	public void setSunriseTime(String sunriseTime)
	{
		this.sunriseTime = sunriseTime;
	}

	/**
	 * Gets the sunrise time tz.
	 * 
	 * @return the sunrise time tz
	 */
	public String getSunriseTimeTz()
	{
		return sunriseTimeTz;
	}

	/**
	 * Sets the sunrise time tz.
	 * 
	 * @param sunriseTimeTz the new sunrise time tz
	 */
	public void setSunriseTimeTz(String sunriseTimeTz)
	{
		this.sunriseTimeTz = sunriseTimeTz;
	}

	/**
	 * Gets the sunrise offset.
	 * 
	 * @return the sunrise offset
	 */
	public String getSunriseOffset()
	{
		return sunriseOffset;
	}

	/**
	 * Sets the sunrise offset.
	 * 
	 * @param sunriseOffset the new sunrise offset
	 */
	public void setSunriseOffset(String sunriseOffset)
	{
		this.sunriseOffset = sunriseOffset;
	}

	/**
	 * Gets the lamp type.
	 * 
	 * @return the lamp type
	 */
	public String getLampType()
	{
		return lampType;
	}

	/**
	 * Sets the lamp type.
	 * 
	 * @param lampType the new lamp type
	 */
	public void setLampType(String lampType)
	{
		this.lampType = lampType;
	}

	/**
	 * Gets the input voltage range.
	 * 
	 * @return the input voltage range
	 */
	public String getInputVoltageRange()
	{
		return inputVoltageRange;
	}

	/**
	 * Sets the input voltage range.
	 * 
	 * @param inputVoltageRange the new input voltage range
	 */
	public void setInputVoltageRange(String inputVoltageRange)
	{
		this.inputVoltageRange = inputVoltageRange;
	}

	/**
	 * Gets the temperature color.
	 * 
	 * @return the temperature color
	 */
	public String getTemperatureColor()
	{
		return temperatureColor;
	}

	/**
	 * Sets the temperature color.
	 * 
	 * @param temperatureColor the new temperature color
	 */
	public void setTemperatureColor(String temperatureColor)
	{
		this.temperatureColor = temperatureColor;
	}

	/**
	 * Gets the sensus part number.
	 * 
	 * @return the sensus part number
	 */
	public String getSensusPartNumber()
	{
		return sensusPartNumber;
	}

	/**
	 * Sets the sensus part number.
	 * 
	 * @param sensusPartNumber the new sensus part number
	 */
	public void setSensusPartNumber(String sensusPartNumber)
	{
		this.sensusPartNumber = sensusPartNumber;
	}

	/**
	 * Gets the pole id.
	 * 
	 * @return the pole id
	 */
	public String getPoleId()
	{
		return poleId;
	}

	/**
	 * Sets the pole id.
	 * 
	 * @param poleId the new pole id
	 */
	public void setPoleId(String poleId)
	{
		this.poleId = poleId;
	}

	/**
	 * Gets the date added.
	 * 
	 * @return the date added
	 */
	public String getDateAdded()
	{
		return dateAdded;
	}

	/**
	 * Sets the date added.
	 * 
	 * @param dateAdded the new date added
	 */
	public void setDateAdded(String dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	/**
	 * Gets the date installed.
	 * 
	 * @return the date installed
	 */
	public String getDateInstalled()
	{
		return dateInstalled;
	}

	/**
	 * Sets the date installed.
	 * 
	 * @param dateInstalled the new date installed
	 */
	public void setDateInstalled(String dateInstalled)
	{
		this.dateInstalled = dateInstalled;
	}

	/**
	 * Gets the firmware.
	 * 
	 * @return the firmware
	 */
	public String getFirmware()
	{
		return firmware;
	}

	/**
	 * Sets the firmware.
	 * 
	 * @param firmware the new firmware
	 */
	public void setFirmware(String firmware)
	{
		this.firmware = firmware;
	}

	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public String getLongitude()
	{
		return longitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude the new longitude
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * Gets the status reason enum value.
	 * 
	 * @return the statusReasonEnumValue
	 */
	public String getStatusReasonEnumValue()
	{
		return statusReasonEnumValue;
	}

	/**
	 * Sets the status reason enum value.
	 * 
	 * @param statusReasonEnumValue the statusReasonEnumValue to set
	 */
	public void setStatusReasonEnumValue(String statusReasonEnumValue)
	{
		this.statusReasonEnumValue = statusReasonEnumValue;
	}

	/**
	 * Sets the status message date.
	 * 
	 * @param statusMessageDate the new status message date
	 */
	public void setStatusMessageDate(String statusMessageDate)
	{
		this.statusMessageDate = statusMessageDate;
	}

	/**
	 * Gets the status message date.
	 * 
	 * @return the status message date
	 */
	public String getStatusMessageDate()
	{
		return statusMessageDate;
	}

	/**
	 * Gets the status alerts message date.
	 * 
	 * @return the statusAlertsMessageDate
	 */
	public String getStatusAlertsMessageDate()
	{
		return statusAlertsMessageDate;
	}

	/**
	 * Sets the status alerts message date.
	 * 
	 * @param statusAlertsMessageDate the statusAlertsMessageDate to set
	 */
	public void setStatusAlertsMessageDate(String statusAlertsMessageDate)
	{
		this.statusAlertsMessageDate = statusAlertsMessageDate;
	}

	/**
	 * Gets the status message date hour.
	 * 
	 * @return the status message date hour
	 */
	public String getStatusMessageDateHour()
	{
		return statusMessageDateHour;
	}

	/**
	 * Sets the status message date hour.
	 * 
	 * @param statusMessageDateHour the new status message date hour
	 */
	public void setStatusMessageDateHour(String statusMessageDateHour)
	{
		this.statusMessageDateHour = statusMessageDateHour;
	}

	/**
	 * Gets the status message date tz.
	 * 
	 * @return the status message date tz
	 */
	public String getStatusMessageDateTz()
	{
		return statusMessageDateTz;
	}

	/**
	 * Sets the status message date tz.
	 * 
	 * @param statusMessageDateTz the new status message date tz
	 */
	public void setStatusMessageDateTz(String statusMessageDateTz)
	{
		this.statusMessageDateTz = statusMessageDateTz;
	}

	/**
	 * Sets the rni id.
	 * 
	 * @param rniId the new rni id
	 */
	public void setRniId(String rniId)
	{
		this.rniId = rniId;
	}

	/**
	 * Gets the rni id.
	 * 
	 * @return the rni id
	 */
	public String getRniId()
	{
		return rniId;
	}

	/**
	 * Gets the wattage.
	 * 
	 * @return the wattage
	 */
	public String getWattage()
	{
		return wattage;
	}

	/**
	 * Sets the wattage.
	 * 
	 * @param wattage the new wattage
	 */
	public void setWattage(String wattage)
	{
		this.wattage = wattage;
	}

	/**
	 * Gets the manufacturer.
	 * 
	 * @return the manufacturer
	 */
	public String getManufacturer()
	{
		return manufacturer;
	}

	/**
	 * Sets the manufacturer.
	 * 
	 * @param manufacturer the new manufacturer
	 */
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}

	/**
	 * Sets the status exceptions.
	 * 
	 * @param statusExceptions the status exceptions
	 */
	public void setStatusExceptions(List<ViewExceptionMessage> statusExceptions)
	{
		this.statusExceptions = statusExceptions;
	}

	/**
	 * Gets the status exceptions.
	 * 
	 * @return the status exceptions
	 */
	public List<ViewExceptionMessage> getStatusExceptions()
	{
		return statusExceptions;
	}

	/**
	 * Sets the offset schedule.
	 * 
	 * @param offsetSchedule the offsetSchedule to set
	 */
	public void setOffsetSchedule(ViewOffsetSchedule offsetSchedule)
	{
		this.offsetSchedule = offsetSchedule;
	}

	/**
	 * Gets the offset schedule.
	 * 
	 * @return the offsetSchedule
	 */
	public ViewOffsetSchedule getOffsetSchedule()
	{
		return offsetSchedule;
	}

	/**
	 * Sets the event schedule.
	 * 
	 * @param eventSchedule the eventSchedule to set
	 */
	public void setEventSchedule(ViewEventSchedule eventSchedule)
	{
		this.eventSchedule = eventSchedule;
	}

	/**
	 * Gets the event schedule.
	 * 
	 * @return the eventSchedule
	 */
	public ViewEventSchedule getEventSchedule()
	{
		return eventSchedule;
	}

	/**
	 * Sets the alert today.
	 * 
	 * @param messageToday the new message today
	 */
	public void setMessageToday(Boolean messageToday)
	{
		this.messageToday = messageToday;
	}

	/**
	 * Gets the alert today.
	 * 
	 * @return the alertToday
	 */
	public Boolean getMessageToday()
	{
		return messageToday;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Sets the checks if is protected.
	 * 
	 * @param isProtected the isProtected to set
	 */
	public void setIsProtected(Boolean isProtected)
	{
		this.isProtected = isProtected;
	}

	/**
	 * Gets the checks if is protected.
	 * 
	 * @return the isProtected
	 */
	public Boolean getIsProtected()
	{
		return isProtected;
	}

	/**
	 * Sets the smartpoint history view list.
	 * 
	 * @param smartpointHistoryViewList the smartpointHistoryViewList to set
	 */
	public void setSmartpointHistoryViewList(List<ViewSmartpointHistory> smartpointHistoryViewList)
	{
		this.smartpointHistoryViewList = smartpointHistoryViewList;
	}

	/**
	 * Gets the smartpoint history view list.
	 * 
	 * @return the smartpointHistoryViewList
	 */
	public List<ViewSmartpointHistory> getSmartpointHistoryViewList()
	{
		return smartpointHistoryViewList;
	}

	/**
	 * Sets the state light.
	 * 
	 * @param stateLight the stateLight to set
	 */
	public void setStateLight(LightStateEnum stateLight)
	{
		this.stateLight = stateLight;
	}

	/**
	 * Gets the state light.
	 * 
	 * @return the stateLight
	 */
	public LightStateEnum getStateLight()
	{
		return stateLight;
	}

	/**
	 * Sets the light driver serial.
	 * 
	 * @param lightDriverSerial the lightDriverSerial to set
	 */
	public void setLightDriverSerial(String lightDriverSerial)
	{
		this.lightDriverSerial = lightDriverSerial;
	}

	/**
	 * Gets the light driver serial.
	 * 
	 * @return the lightDriverSerial
	 */
	public String getLightDriverSerial()
	{
		return lightDriverSerial;
	}

	/**
	 * Sets the upper serial number.
	 * 
	 * @param upperSerialNumber the upperSerialNumber to set
	 */
	public void setUpperSerialNumber(String upperSerialNumber)
	{
		this.upperSerialNumber = upperSerialNumber;
	}

	/**
	 * Gets the upper serial number.
	 * 
	 * @return the upperSerialNumber
	 */
	public String getUpperSerialNumber()
	{
		return upperSerialNumber;
	}

	/**
	 * Sets the lower serial number.
	 * 
	 * @param lowerSerialNumber the lowerSerialNumber to set
	 */
	public void setLowerSerialNumber(String lowerSerialNumber)
	{
		this.lowerSerialNumber = lowerSerialNumber;
	}

	/**
	 * Gets the lower serial number.
	 * 
	 * @return the lowerSerialNumber
	 */
	public String getLowerSerialNumber()
	{
		return lowerSerialNumber;
	}

	/**
	 * Sets the bulb serial number.
	 * 
	 * @param bulbSerialNumber the bulbSerialNumber to set
	 */
	public void setBulbSerialNumber(String bulbSerialNumber)
	{
		this.bulbSerialNumber = bulbSerialNumber;
	}

	/**
	 * Gets the bulb serial number.
	 * 
	 * @return the bulbSerialNumber
	 */
	public String getBulbSerialNumber()
	{
		return bulbSerialNumber;
	}

	/**
	 * Gets the light intensity.
	 * 
	 * @return the light intensity
	 */
	public String getLightIntensity()
	{
		return lightIntensity;
	}

	/**
	 * Sets the light intensity.
	 * 
	 * @param lightIntensity the new light intensity
	 */
	public void setLightIntensity(String lightIntensity)
	{
		this.lightIntensity = lightIntensity;
	}

	/**
	 * Sets the housing.
	 * 
	 * @param housing the housing to set
	 */
	public void setHousing(String housing)
	{
		this.housing = housing;
	}

	/**
	 * Gets the housing.
	 * 
	 * @return the housing
	 */
	public String getHousing()
	{
		return housing;
	}

	/**
	 * Sets the dimmable.
	 * 
	 * @param dimmable the dimmable to set
	 */
	public void setDimmable(String dimmable)
	{
		this.dimmable = dimmable;
	}

	/**
	 * Gets the dimmable.
	 * 
	 * @return the dimmable
	 */
	public String getDimmable()
	{
		return dimmable;
	}

	/**
	 * Sets the housing color.
	 * 
	 * @param housingColor the housingColor to set
	 */
	public void setHousingColor(String housingColor)
	{
		this.housingColor = housingColor;
	}

	/**
	 * Gets the housing color.
	 * 
	 * @return the housingColor
	 */
	public String getHousingColor()
	{
		return housingColor;
	}

	/**
	 * Gets the light status.
	 * 
	 * @return the light status
	 */
	public Integer getLightStatus()
	{
		return lightStatus;
	}

	/**
	 * Sets the light status.
	 * 
	 * @param lightStatus the new light status
	 */
	public void setLightStatus(Integer lightStatus)
	{
		this.lightStatus = lightStatus;
	}

	/**
	 * Gets the voltage last.
	 * 
	 * @return the voltageLast
	 */
	public String getVoltageLast()
	{
		return voltageLast;
	}

	/**
	 * Sets the voltage last.
	 * 
	 * @param voltageLast the voltageLast to set
	 */
	public void setVoltageLast(String voltageLast)
	{
		this.voltageLast = voltageLast;
	}

	/**
	 * Gets the voltage min.
	 * 
	 * @return the voltageMin
	 */
	public String getVoltageMin()
	{
		return voltageMin;
	}

	/**
	 * Sets the voltage min.
	 * 
	 * @param voltageMin the voltageMin to set
	 */
	public void setVoltageMin(String voltageMin)
	{
		this.voltageMin = voltageMin;
	}

	/**
	 * Gets the voltage max.
	 * 
	 * @return the voltageMax
	 */
	public String getVoltageMax()
	{
		return voltageMax;
	}

	/**
	 * Sets the voltage max.
	 * 
	 * @param voltageMax the voltageMax to set
	 */
	public void setVoltageMax(String voltageMax)
	{
		this.voltageMax = voltageMax;
	}

	/**
	 * Gets the current last.
	 * 
	 * @return the currentLast
	 */
	public String getCurrentLast()
	{
		return currentLast;
	}

	/**
	 * Sets the current last.
	 * 
	 * @param currentLast the currentLast to set
	 */
	public void setCurrentLast(String currentLast)
	{
		this.currentLast = currentLast;
	}

	/**
	 * Gets the current min.
	 * 
	 * @return the currentMin
	 */
	public String getCurrentMin()
	{
		return currentMin;
	}

	/**
	 * Sets the current min.
	 * 
	 * @param currentMin the currentMin to set
	 */
	public void setCurrentMin(String currentMin)
	{
		this.currentMin = currentMin;
	}

	/**
	 * Gets the current max.
	 * 
	 * @return the currentMax
	 */
	public String getCurrentMax()
	{
		return currentMax;
	}

	/**
	 * Sets the current max.
	 * 
	 * @param currentMax the currentMax to set
	 */
	public void setCurrentMax(String currentMax)
	{
		this.currentMax = currentMax;
	}

	/**
	 * Gets the consumption last.
	 * 
	 * @return the consumptionLast
	 */
	public String getConsumptionLast()
	{
		return consumptionLast;
	}

	/**
	 * Sets the consumption last.
	 * 
	 * @param consumptionLast the consumptionLast to set
	 */
	public void setConsumptionLast(String consumptionLast)
	{
		this.consumptionLast = consumptionLast;
	}

	/**
	 * Gets the consumption min.
	 * 
	 * @return the consumptionMin
	 */
	public String getConsumptionMin()
	{
		return consumptionMin;
	}

	/**
	 * Sets the consumption min.
	 * 
	 * @param consumptionMin the consumptionMin to set
	 */
	public void setConsumptionMin(String consumptionMin)
	{
		this.consumptionMin = consumptionMin;
	}

	/**
	 * Gets the consumption max.
	 * 
	 * @return the consumptionMax
	 */
	public String getConsumptionMax()
	{
		return consumptionMax;
	}

	/**
	 * Sets the consumption max.
	 * 
	 * @param consumptionMax the consumptionMax to set
	 */
	public void setConsumptionMax(String consumptionMax)
	{
		this.consumptionMax = consumptionMax;
	}

	/**
	 * Gets the light intensitylevels.
	 * 
	 * @return the lightIntensitylevels
	 */
	public List<String> getLightIntensitylevels()
	{
		return lightIntensitylevels;
	}

	/**
	 * Sets the light intensitylevels.
	 * 
	 * @param lightIntensitylevels the lightIntensitylevels to set
	 */
	public void setLightIntensitylevels(List<String> lightIntensitylevels)
	{
		this.lightIntensitylevels = lightIntensitylevels;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ViewLight [getId()=" + getId() + ", getLatitude()=" + getLatitude() + ", getSunsetTime()="
				+ getSunsetTime() + ", getSunsetTimeTz()=" + getSunsetTimeTz() + ", getSunsetOffset()="
				+ getSunsetOffset() + ", getSunriseTime()=" + getSunriseTime() + ", getSunriseTimeTz()="
				+ getSunriseTimeTz() + ", getSunriseOffset()=" + getSunriseOffset() + ", getLampType()="
				+ getLampType() + ", getInputVoltageRange()=" + getInputVoltageRange() + ", getTemperatureColor()="
				+ getTemperatureColor() + ", getSensusPartNumber()=" + getSensusPartNumber() + ", getPoleId()="
				+ getPoleId() + ", getDateAdded()=" + getDateAdded() + ", getDateInstalled()=" + getDateInstalled()
				+ ", getFirmware()=" + getFirmware() + ", getLongitude()=" + getLongitude()
				+ ", getStatusReasonEnumValue()=" + getStatusReasonEnumValue() + ", getStatusMessageDate()="
				+ getStatusMessageDate() + ", getStatusAlertsMessageDate()=" + getStatusAlertsMessageDate()
				+ ", getStatusMessageDateHour()=" + getStatusMessageDateHour() + ", getStatusMessageDateTz()="
				+ getStatusMessageDateTz() + ", getRniId()=" + getRniId() + ", getWattage()=" + getWattage()
				+ ", getManufacturer()=" + getManufacturer() + ", getStatusExceptions()=" + getStatusExceptions()
				+ ", getOffsetSchedule()=" + getOffsetSchedule() + ", getEventSchedule()=" + getEventSchedule()
				+ ", getMessageToday()=" + getMessageToday() + ", getAddress()=" + getAddress() + ", getIsProtected()="
				+ getIsProtected() + ", getSmartpointHistoryViewList()=" + getSmartpointHistoryViewList()
				+ ", getStateLight()=" + getStateLight() + ", getLightDriverSerial()=" + getLightDriverSerial()
				+ ", getUpperSerialNumber()=" + getUpperSerialNumber() + ", getLowerSerialNumber()="
				+ getLowerSerialNumber() + ", getBulbSerialNumber()=" + getBulbSerialNumber()
				+ ", getLightIntensity()=" + getLightIntensity() + ", getHousing()=" + getHousing()
				+ ", getDimmable()=" + getDimmable() + ", getHousingColor()=" + getHousingColor()
				+ ", getLightStatus()=" + getLightStatus() + ", getVoltageLast()=" + getVoltageLast()
				+ ", getVoltageMin()=" + getVoltageMin() + ", getVoltageMax()=" + getVoltageMax()
				+ ", getCurrentLast()=" + getCurrentLast() + ", getCurrentMin()=" + getCurrentMin()
				+ ", getCurrentMax()=" + getCurrentMax() + ", getConsumptionLast()=" + getConsumptionLast()
				+ ", getConsumptionMin()=" + getConsumptionMin() + ", getConsumptionMax()=" + getConsumptionMax()
				+ ", getLightIntensitylevels()=" + getLightIntensitylevels() + "]";
	}

	/**
	 * @return the currentAlarmWarningMessageList
	 */
	public List<CurrentAlarmWarningMessage> getCurrentAlarmWarningMessageList()
	{
		return currentAlarmWarningMessageList;
	}

	/**
	 * @param currentAlarmWarningMessageList the currentAlarmWarningMessageList to set
	 */
	public void setCurrentAlarmWarningMessageList(List<CurrentAlarmWarningMessage> currentAlarmWarningMessageList)
	{
		this.currentAlarmWarningMessageList = currentAlarmWarningMessageList;
	}

}
