package com.sensus.mlc.smartpoint.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * Model object that contains light configuration and schedule properties.
 * 
 * @see Light for more details about the light object and its relationships.
 * 
 * @author Thiago Silva - QAT
 * 
 */
@SuppressWarnings("serial")
public class LightConfiguration extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Integer lightId;

	/**
	 * See sensus-mlc-bcl-application-context.xml for more information
	 * about the injection mapping of below attributes.
	 */
	private String housing;

	private String housingColor;

	private Boolean dimmable;

	private String lampTypeWattageDimmable;

	private String wattageRating;

	private String inputaWattageRange;

	private String colorTemperature;

	private String manufacturer;

	private String firmwareVersion;

	private String modelNumber;

	private String lowerAssemblySerial;

	private String upperAssemblySerial;

	private String frequency;

	private String lightDriverSerialNumber;

	private Date dateAdded;

	private String bulbSerialNumber;

	private String ballastSerialNumber;

	/**
	 * @return the dateAdded
	 */
	public Date getDateAdded()
	{
		return dateAdded;
	}

	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(Date dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	/**
	 * @return the lightId
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * @param lightId the lightId to set
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * @return the housing
	 */
	public String getHousing()
	{
		return housing;
	}

	/**
	 * @param housing the housing to set
	 */
	public void setHousing(String housing)
	{
		this.housing = housing;
	}

	/**
	 * @return the housingColor
	 */
	public String getHousingColor()
	{
		return housingColor;
	}

	/**
	 * @param housingColor the housingColor to set
	 */
	public void setHousingColor(String housingColor)
	{
		this.housingColor = housingColor;
	}

	/**
	 * @return the dimmable
	 */
	public Boolean getDimmable()
	{
		return dimmable;
	}

	/**
	 * @param dimmable the dimmable to set
	 */
	public void setDimmable(Boolean dimmable)
	{
		this.dimmable = dimmable;
	}

	/**
	 * @return the lampTypeWattageDimmable
	 */
	public String getLampTypeWattageDimmable()
	{
		return lampTypeWattageDimmable;
	}

	/**
	 * @param lampTypeWattageDimmable the lampTypeWattageDimmable to set
	 */
	public void setLampTypeWattageDimmable(String lampTypeWattageDimmable)
	{
		this.lampTypeWattageDimmable = lampTypeWattageDimmable;
	}

	/**
	 * @return the wattageRating
	 */
	public String getWattageRating()
	{
		return wattageRating;
	}

	/**
	 * @param wattageRating the wattageRating to set
	 */
	public void setWattageRating(String wattageRating)
	{
		this.wattageRating = wattageRating;
	}

	/**
	 * @return the inputaWattageRange
	 */
	public String getInputaWattageRange()
	{
		return inputaWattageRange;
	}

	/**
	 * @param inputaWattageRange the inputaWattageRange to set
	 */
	public void setInputaWattageRange(String inputaWattageRange)
	{
		this.inputaWattageRange = inputaWattageRange;
	}

	/**
	 * @return the colorTemperature
	 */
	public String getColorTemperature()
	{
		return colorTemperature;
	}

	/**
	 * @param colorTemperature the colorTemperature to set
	 */
	public void setColorTemperature(String colorTemperature)
	{
		this.colorTemperature = colorTemperature;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer()
	{
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the firmwareVersion
	 */
	public String getFirmwareVersion()
	{
		return firmwareVersion;
	}

	/**
	 * @param firmwareVersion the firmwareVersion to set
	 */
	public void setFirmwareVersion(String firmwareVersion)
	{
		this.firmwareVersion = firmwareVersion;
	}

	/**
	 * @return the modelNumber
	 */
	public String getModelNumber()
	{
		return modelNumber;
	}

	/**
	 * @param modelNumber the modelNumber to set
	 */
	public void setModelNumber(String modelNumber)
	{
		this.modelNumber = modelNumber;
	}

	/**
	 * @return the lowerAssemblySerial
	 */
	public String getLowerAssemblySerial()
	{
		return lowerAssemblySerial;
	}

	/**
	 * @param lowerAssemblySerial the lowerAssemblySerial to set
	 */
	public void setLowerAssemblySerial(String lowerAssemblySerial)
	{
		this.lowerAssemblySerial = lowerAssemblySerial;
	}

	/**
	 * @return the upperAssemblySerial
	 */
	public String getUpperAssemblySerial()
	{
		return upperAssemblySerial;
	}

	/**
	 * @param upperAssemblySerial the upperAssemblySerial to set
	 */
	public void setUpperAssemblySerial(String upperAssemblySerial)
	{
		this.upperAssemblySerial = upperAssemblySerial;
	}

	/**
	 * @return the frequency
	 */
	public String getFrequency()
	{
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency)
	{
		this.frequency = frequency;
	}

	/**
	 * @return the lightDriverSerialNumber
	 */
	public String getLightDriverSerialNumber()
	{
		return lightDriverSerialNumber;
	}

	/**
	 * @param lightDriverSerialNumber the lightDriverSerialNumber to set
	 */
	public void setLightDriverSerialNumber(String lightDriverSerialNumber)
	{
		this.lightDriverSerialNumber = lightDriverSerialNumber;
	}

	/**
	 * @return the bulbSerialNumber
	 */
	public String getBulbSerialNumber()
	{
		return bulbSerialNumber;
	}

	/**
	 * @param bulbSerialNumber the bulbSerialNumber to set
	 */
	public void setBulbSerialNumber(String bulbSerialNumber)
	{
		this.bulbSerialNumber = bulbSerialNumber;
	}

	/**
	 * @return the ballastSerialNumber
	 */
	public String getBallastSerialNumber()
	{
		return ballastSerialNumber;
	}

	/**
	 * @param ballastSerialNumber the ballastSerialNumber to set
	 */
	public void setBallastSerialNumber(String ballastSerialNumber)
	{
		this.ballastSerialNumber = ballastSerialNumber;
	}

}
