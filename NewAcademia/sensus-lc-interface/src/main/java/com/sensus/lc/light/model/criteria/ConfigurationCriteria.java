package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.Date;
import java.util.List;

/**
 * Criteria for light configurations.
 * 
 * @author Thiago - QAT
 */
public class ConfigurationCriteria
{

	/**
	 * Attributes.
	 */
	private String housing;

	/** The housing color. */
	private List<String> housingColor;

	/** The dimmable. */
	private Boolean dimmable;

	/** The wattage rating. */
	private String wattageRating;

	/** The input voltage range. */
	private List<String> inputVoltageRange;

	/** The color temperature. */
	private String colorTemperature;

	/** The lower assembly serial. */
	private String lowerAssemblySerial;

	/** The upper assembly serial. */
	private String upperAssemblySerial;

	/** The date added. */
	private Date dateAdded;

	/** The date added before. */
	private Date dateAddedBefore;

	/** The date added after. */
	private Date dateAddedAfter;

	/** The bulb serial number. */
	private String bulbSerialNumber;

	/** The ballast serial number. */
	private String ballastSerialNumber;

	/** The lamp type wattage dimmable. */
	private String lampTypeWattageDimmable;

	/** The firmware version. */
	private String firmwareVersion;

	/** The model number. */
	private String modelNumber;

	/**
	 * Instantiates a new configuration criteria.
	 */
	public ConfigurationCriteria()
	{

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
	 * Sets the housing.
	 * 
	 * @param housing the new housing
	 */
	public void setHousing(String housing)
	{
		this.housing = housing;
	}

	/**
	 * @return the housingColor
	 */
	public List<String> getHousingColor()
	{
		return housingColor;
	}

	/**
	 * @param housingColor the housingColor to set
	 */
	public void setHousingColor(List<String> housingColor)
	{
		this.housingColor = housingColor;
	}

	/**
	 * Gets the dimmable.
	 * 
	 * @return the dimmable
	 */
	public Boolean getDimmable()
	{
		return dimmable;
	}

	/**
	 * Sets the dimmable.
	 * 
	 * @param dimmable the new dimmable
	 */
	public void setDimmable(Boolean dimmable)
	{
		this.dimmable = dimmable;
	}

	/**
	 * Gets the wattage rating.
	 * 
	 * @return the wattage rating
	 */
	public String getWattageRating()
	{
		return wattageRating;
	}

	/**
	 * Sets the wattage rating.
	 * 
	 * @param wattageRating the new wattage rating
	 */
	public void setWattageRating(String wattageRating)
	{
		this.wattageRating = wattageRating;
	}

	/**
	 * @return the inputVoltageRange
	 */
	public List<String> getInputVoltageRange()
	{
		return inputVoltageRange;
	}

	/**
	 * @param inputVoltageRange the inputVoltageRange to set
	 */
	public void setInputVoltageRange(List<String> inputVoltageRange)
	{
		this.inputVoltageRange = inputVoltageRange;
	}

	/**
	 * Gets the color temperature.
	 * 
	 * @return the color temperature
	 */
	public String getColorTemperature()
	{
		return colorTemperature;
	}

	/**
	 * Sets the color temperature.
	 * 
	 * @param colorTemperature the new color temperature
	 */
	public void setColorTemperature(String colorTemperature)
	{
		this.colorTemperature = colorTemperature;
	}

	/**
	 * Gets the lower assembly serial.
	 * 
	 * @return the lower assembly serial
	 */
	public String getLowerAssemblySerial()
	{
		return lowerAssemblySerial;
	}

	/**
	 * Sets the lower assembly serial.
	 * 
	 * @param lowerAssemblySerial the new lower assembly serial
	 */
	public void setLowerAssemblySerial(String lowerAssemblySerial)
	{
		this.lowerAssemblySerial = lowerAssemblySerial;
	}

	/**
	 * Gets the upper assembly serial.
	 * 
	 * @return the upper assembly serial
	 */
	public String getUpperAssemblySerial()
	{
		return upperAssemblySerial;
	}

	/**
	 * Sets the upper assembly serial.
	 * 
	 * @param upperAssemblySerial the new upper assembly serial
	 */
	public void setUpperAssemblySerial(String upperAssemblySerial)
	{
		this.upperAssemblySerial = upperAssemblySerial;
	}

	/**
	 * Gets the date added.
	 * 
	 * @return the date added
	 */
	public Date getDateAdded()
	{
		return dateAdded;
	}

	/**
	 * Sets the date added.
	 * 
	 * @param dateAdded the new date added
	 */
	public void setDateAdded(Date dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	/**
	 * Gets the bulb serial number.
	 * 
	 * @return the bulb serial number
	 */
	public String getBulbSerialNumber()
	{
		return bulbSerialNumber;
	}

	/**
	 * Sets the bulb serial number.
	 * 
	 * @param bulbSerialNumber the new bulb serial number
	 */
	public void setBulbSerialNumber(String bulbSerialNumber)
	{
		this.bulbSerialNumber = bulbSerialNumber;
	}

	/**
	 * Gets the ballast serial number.
	 * 
	 * @return the ballast serial number
	 */
	public String getBallastSerialNumber()
	{
		return ballastSerialNumber;
	}

	/**
	 * Sets the ballast serial number.
	 * 
	 * @param ballastSerialNumber the new ballast serial number
	 */
	public void setBallastSerialNumber(String ballastSerialNumber)
	{
		this.ballastSerialNumber = ballastSerialNumber;
	}

	/**
	 * Gets the lamp type wattage dimmable.
	 * 
	 * @return the lamp type wattage dimmable
	 */
	public String getLampTypeWattageDimmable()
	{
		return lampTypeWattageDimmable;
	}

	/**
	 * Sets the lamp type wattage dimmable.
	 * 
	 * @param lampTypeWattageDimmable the new lamp type wattage dimmable
	 */
	public void setLampTypeWattageDimmable(String lampTypeWattageDimmable)
	{
		this.lampTypeWattageDimmable = lampTypeWattageDimmable;
	}

	/**
	 * Gets the firmware version.
	 * 
	 * @return the firmware version
	 */
	public String getFirmwareVersion()
	{
		return firmwareVersion;
	}

	/**
	 * Sets the firmware version.
	 * 
	 * @param firmwareVersion the new firmware version
	 */
	public void setFirmwareVersion(String firmwareVersion)
	{
		this.firmwareVersion = firmwareVersion;
	}

	/**
	 * Gets the model number.
	 * 
	 * @return the model number
	 */
	public String getModelNumber()
	{
		return modelNumber;
	}

	/**
	 * Sets the model number.
	 * 
	 * @param modelNumber the new model number
	 */
	public void setModelNumber(String modelNumber)
	{
		this.modelNumber = modelNumber;
	}

	/**
	 * Gets the date added before.
	 * 
	 * @return the date added before
	 */
	public Date getDateAddedBefore()
	{
		return dateAddedBefore;
	}

	/**
	 * Sets the date added before.
	 * 
	 * @param dateAddedBefore the new date added before
	 */
	public void setDateAddedBefore(Date dateAddedBefore)
	{
		this.dateAddedBefore = dateAddedBefore;
	}

	/**
	 * Gets the date added after.
	 * 
	 * @return the date added after
	 */
	public Date getDateAddedAfter()
	{
		return dateAddedAfter;
	}

	/**
	 * Sets the date added after.
	 * 
	 * @param dateAddedAfter the new date added after
	 */
	public void setDateAddedAfter(Date dateAddedAfter)
	{
		this.dateAddedAfter = dateAddedAfter;
	}

	/**
	 * Checks for filter.
	 * 
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return !isNull(getHousing()) ||
				!isNull(getHousingColor()) ||
				!isNull(getDimmable()) ||
				!isNull(getWattageRating()) ||
				!isNull(getInputVoltageRange()) ||
				!isNull(getColorTemperature()) ||
				!isNull(getLowerAssemblySerial()) ||
				!isNull(getUpperAssemblySerial()) ||
				!isNull(getDateAdded()) ||
				!isNull(getBulbSerialNumber()) ||
				!isNull(getBallastSerialNumber());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ConfigurationCriteria [getHousing()=" + getHousing() + ", getHousingColor()=" + getHousingColor()
				+ ", getDimmable()=" + getDimmable() + ", getWattageRating()=" + getWattageRating()
				+ ", getInputVoltageRange()=" + getInputVoltageRange() + ", getColorTemperature()="
				+ getColorTemperature() + ", getLowerAssemblySerial()=" + getLowerAssemblySerial()
				+ ", getUpperAssemblySerial()=" + getUpperAssemblySerial() + ", getDateAdded()=" + getDateAdded()
				+ ", getBulbSerialNumber()=" + getBulbSerialNumber() + ", getBallastSerialNumber()="
				+ getBallastSerialNumber() + ", getLampTypeWattageDimmable()=" + getLampTypeWattageDimmable()
				+ ", getFirmwareVersion()=" + getFirmwareVersion() + ", getModelNumber()=" + getModelNumber()
				+ ", getDateAddedBefore()=" + getDateAddedBefore() + ", getDateAddedAfter()=" + getDateAddedAfter()
				+ ", hasFilter()=" + hasFilter() + ", toString()=" + super.toString() + "]";
	}
}
