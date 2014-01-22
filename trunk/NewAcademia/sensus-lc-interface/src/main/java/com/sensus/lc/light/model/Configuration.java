package com.sensus.lc.light.model;

import java.util.Date;
import java.util.List;

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
public class Configuration extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Integer parentId;

	/**
	 * See sensus-mlc-bcl-application-context.xml for more information
	 * about the injection mapping of below attributes.
	 */
	private String housing;

	/** The housing color. */
	private String housingColor;

	/** The dimmable. */
	private Boolean dimmable;

	/** The lamp type wattage dimmable. */
	private String lampTypeWattageDimmable;

	/** The wattage rating. */
	private String wattageRating;

	/** The input voltage range. */
	private String inputVoltageRange;

	/** The color temperature. */
	private String colorTemperature;

	/** The manufacturer. */
	private String manufacturer;

	/** The firmware version. */
	private String firmwareVersion;

	/** The model number. */
	private String modelNumber;

	/** The lower assembly serial. */
	private String lowerAssemblySerial;

	/** The upper assembly serial. */
	private String upperAssemblySerial;

	/** The frequency. */
	private String frequency;

	/** The light driver serial number. */
	private String lightDriverSerialNumber;

	/** The date added. */
	private Date dateAdded;

	/** The bulb serial number. */
	private String bulbSerialNumber;

	/** The ballast serial number. */
	private String ballastSerialNumber;

	/** The customer serial number. */
	private String customerSerialNumber;

	/** The part number configurations for THIS light. */
	private List<PartNumberConfiguration> partNumberConfigurations;

	/**
	 * Instantiates a new configuration.
	 */
	public Configuration()
	{
		super();
	}

	/**
	 * Gets the date added.
	 * 
	 * @return the dateAdded
	 */
	public Date getDateAdded()
	{
		return dateAdded;
	}

	/**
	 * Sets the date added.
	 * 
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(Date dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	/**
	 * Gets the light id.
	 * 
	 * @return the parentId
	 */
	public Integer getParentId()
	{
		return parentId;
	}

	/**
	 * Sets the light id.
	 * 
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
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
	 * @param housing the housing to set
	 */
	public void setHousing(String housing)
	{
		this.housing = housing;
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
	 * Sets the housing color.
	 * 
	 * @param housingColor the housingColor to set
	 */
	public void setHousingColor(String housingColor)
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
	 * @param dimmable the dimmable to set
	 */
	public void setDimmable(Boolean dimmable)
	{
		this.dimmable = dimmable;
	}

	/**
	 * Gets the lamp type wattage dimmable.
	 * 
	 * @return the lampTypeWattageDimmable
	 */
	public String getLampTypeWattageDimmable()
	{
		return lampTypeWattageDimmable;
	}

	/**
	 * Sets the lamp type wattage dimmable.
	 * 
	 * @param lampTypeWattageDimmable the lampTypeWattageDimmable to set
	 */
	public void setLampTypeWattageDimmable(String lampTypeWattageDimmable)
	{
		this.lampTypeWattageDimmable = lampTypeWattageDimmable;
	}

	/**
	 * Gets the wattage rating.
	 * 
	 * @return the wattageRating
	 */
	public String getWattageRating()
	{
		return wattageRating;
	}

	/**
	 * Sets the wattage rating.
	 * 
	 * @param wattageRating the wattageRating to set
	 */
	public void setWattageRating(String wattageRating)
	{
		this.wattageRating = wattageRating;
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
	 * Gets the color temperature.
	 * 
	 * @return the colorTemperature
	 */
	public String getColorTemperature()
	{
		return colorTemperature;
	}

	/**
	 * Sets the color temperature.
	 * 
	 * @param colorTemperature the colorTemperature to set
	 */
	public void setColorTemperature(String colorTemperature)
	{
		this.colorTemperature = colorTemperature;
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
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}

	/**
	 * Gets the firmware version.
	 * 
	 * @return the firmwareVersion
	 */
	public String getFirmwareVersion()
	{
		return firmwareVersion;
	}

	/**
	 * Sets the firmware version.
	 * 
	 * @param firmwareVersion the firmwareVersion to set
	 */
	public void setFirmwareVersion(String firmwareVersion)
	{
		this.firmwareVersion = firmwareVersion;
	}

	/**
	 * Gets the model number.
	 * 
	 * @return the modelNumber
	 */
	public String getModelNumber()
	{
		return modelNumber;
	}

	/**
	 * Sets the model number.
	 * 
	 * @param modelNumber the modelNumber to set
	 */
	public void setModelNumber(String modelNumber)
	{
		this.modelNumber = modelNumber;
	}

	/**
	 * Gets the lower assembly serial.
	 * 
	 * @return the lowerAssemblySerial
	 */
	public String getLowerAssemblySerial()
	{
		return lowerAssemblySerial;
	}

	/**
	 * Sets the lower assembly serial.
	 * 
	 * @param lowerAssemblySerial the lowerAssemblySerial to set
	 */
	public void setLowerAssemblySerial(String lowerAssemblySerial)
	{
		this.lowerAssemblySerial = lowerAssemblySerial;
	}

	/**
	 * Gets the upper assembly serial.
	 * 
	 * @return the upperAssemblySerial
	 */
	public String getUpperAssemblySerial()
	{
		return upperAssemblySerial;
	}

	/**
	 * Sets the upper assembly serial.
	 * 
	 * @param upperAssemblySerial the upperAssemblySerial to set
	 */
	public void setUpperAssemblySerial(String upperAssemblySerial)
	{
		this.upperAssemblySerial = upperAssemblySerial;
	}

	/**
	 * Gets the frequency.
	 * 
	 * @return the frequency
	 */
	public String getFrequency()
	{
		return frequency;
	}

	/**
	 * Sets the frequency.
	 * 
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency)
	{
		this.frequency = frequency;
	}

	/**
	 * Gets the light driver serial number.
	 * 
	 * @return the lightDriverSerialNumber
	 */
	public String getLightDriverSerialNumber()
	{
		return lightDriverSerialNumber;
	}

	/**
	 * Sets the light driver serial number.
	 * 
	 * @param lightDriverSerialNumber the lightDriverSerialNumber to set
	 */
	public void setLightDriverSerialNumber(String lightDriverSerialNumber)
	{
		this.lightDriverSerialNumber = lightDriverSerialNumber;
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
	 * Sets the bulb serial number.
	 * 
	 * @param bulbSerialNumber the bulbSerialNumber to set
	 */
	public void setBulbSerialNumber(String bulbSerialNumber)
	{
		this.bulbSerialNumber = bulbSerialNumber;
	}

	/**
	 * Gets the ballast serial number.
	 * 
	 * @return the ballastSerialNumber
	 */
	public String getBallastSerialNumber()
	{
		return ballastSerialNumber;
	}

	/**
	 * Sets the ballast serial number.
	 * 
	 * @param ballastSerialNumber the ballastSerialNumber to set
	 */
	public void setBallastSerialNumber(String ballastSerialNumber)
	{
		this.ballastSerialNumber = ballastSerialNumber;
	}

	/**
	 * Gets the customer serial number.
	 * 
	 * @return the customer serial number
	 */
	public String getCustomerSerialNumber()
	{
		return customerSerialNumber;
	}

	/**
	 * Sets the customer serial number.
	 * 
	 * @param customerSerialNumber the new customer serial number
	 */
	public void setCustomerSerialNumber(String customerSerialNumber)
	{
		this.customerSerialNumber = customerSerialNumber;
	}

	/**
	 * Gets the part number configurations.
	 * 
	 * @return the partNumberConfigurations
	 */
	public List<PartNumberConfiguration> getPartNumberConfigurations()
	{
		return partNumberConfigurations;
	}

	/**
	 * Sets the part number configurations.
	 * 
	 * @param partNumberConfigurations the partNumberConfigurations to set
	 */
	public void setPartNumberConfigurations(List<PartNumberConfiguration> partNumberConfigurations)
	{
		this.partNumberConfigurations = partNumberConfigurations;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Configuration [getDateAdded()=" + getDateAdded() + ", getParentId()=" + getParentId()
				+ ", getHousing()=" + getHousing() + ", getHousingColor()=" + getHousingColor() + ", getDimmable()="
				+ getDimmable() + ", getLampTypeWattageDimmable()=" + getLampTypeWattageDimmable()
				+ ", getWattageRating()=" + getWattageRating() + ", getInputVoltageRange()=" + getInputVoltageRange()
				+ ", getColorTemperature()=" + getColorTemperature() + ", getManufacturer()=" + getManufacturer()
				+ ", getFirmwareVersion()=" + getFirmwareVersion() + ", getModelNumber()=" + getModelNumber()
				+ ", getLowerAssemblySerial()=" + getLowerAssemblySerial() + ", getUpperAssemblySerial()="
				+ getUpperAssemblySerial() + ", getFrequency()=" + getFrequency() + ", getLightDriverSerialNumber()="
				+ getLightDriverSerialNumber() + ", getBulbSerialNumber()=" + getBulbSerialNumber()
				+ ", getBallastSerialNumber()=" + getBallastSerialNumber() + ", getCustomerSerialNumber()="
				+ getCustomerSerialNumber() + ", getPartNumberConfigurations()=" + getPartNumberConfigurations()
				+ ", toString()=" + super.toString() + "]";
	}

}
