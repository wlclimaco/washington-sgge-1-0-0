package com.sensus.mlc.smartpoint.model;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.LightConsumption;

/**
 * * The Class Light.
 * 
 * @author - QAT Brazil
 */
@SuppressWarnings("serial")
public class Light extends SmartPoint
{
	/** The id. */
	private Integer id;

	/** The protect. */
	private Boolean protect;

	/** The pole id. */
	private String poleId;

	// State informations
	/** The light status enum. */
	private LightStateEnum lightStateEnum;

	/** The intensity. */
	private LightIntensityEnum lightIntensityEnum;

	/** The blink status. */
	private LightBlinkEnum lightBlinkEnum;

	/** The Override ***. */
	private OverrideEnum overrideEnum;

	/** The override per date. */
	private Date overridePerDate;

	/** The override create date. */
	private Date overrideCreateDate;

	/** The light intensitylevels. */
	private List<SensusPartNumberConfiguration> lightIntensitylevels;

	// EcoMode informations

	/** The eco mode. */
	private Double ecoMode;

	/** The eco mode baseline. */
	private EcoModeBaseline ecoModeBaseline;

	/** The last consumption. */
	private LightConsumption lastConsumption;

	/** The consumptions. */
	private List<LightConsumption> consumptions;

	// Status informations

	/** The current status message. */
	private StatusMessage currentStatusMessage;

	/** The current light status. */
	private LightStatusEnum currentLightStatusEnum;

	/** The CURREN t_ alar m_ warnin g_ statu s_ subtype. */
	private String currentAlarmWarningStatusSubtype;

	/** All Status Messages. */
	private List<StatusMessage> statusMessages;

	/** The current alarm warning message list. */
	private List<CurrentAlarmWarningMessage> currentAlarmWarningMessageList;

	/** The last message received. */
	private StatusMessage lastMessageReceived;

	/** The light location. */
	private LightLocation lightLocation = new LightLocation();

	/** The light configuration. */
	private LightConfiguration lightConfiguration = new LightConfiguration();

	/** The light schedule. */
	private LightSchedule lightSchedule = new LightSchedule();

	/** The light last operational data. */
	private LightLastOperationalData lightLastOperationalData = new LightLastOperationalData();

	/** The light type LED, INDUCTIVE, OTHER. */
	private LightTypeEnum lightTypeEnum;

	/**
	 * Instantiates a new light.
	 */
	public Light()
	{
		setSmartPointTypeEnum(SmartPointTypeEnum.LIGHT);
		setLightStateEnum(LightStateEnum.OFF);
	}

	/**
	 * Instantiates a new light.
	 * 
	 * @param rniId the rni id
	 */
	public Light(Integer rniId)
	{
		this();
		setRniId(rniId);
	}

	/**
	 * Instantiates a new light.
	 * 
	 * @param rniId the rni id
	 * @param lightState the light state
	 */
	public Light(Integer rniId, LightStateEnum lightState)
	{
		this(rniId);
		setLightStateEnum(lightState);
	}

	/**
	 * Instantiates a new light.
	 * 
	 * @param idParamValue the id param value
	 * @param parametersValue the parameters value
	 * @param currentStatusMessageParamValue the current status message param value
	 * @param protectParamValue the protect param value
	 */
	public Light(Integer idParamValue, List<LightParameter> parametersValue,
			StatusMessage currentStatusMessageParamValue, Boolean protectParamValue)
	{
		this();
		setId(idParamValue);
		setCurrentStatusMessage(currentStatusMessageParamValue);
		setProtect(protectParamValue);
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the current status message.
	 * 
	 * @return the current status message
	 */
	public StatusMessage getCurrentStatusMessage()
	{
		return currentStatusMessage;
	}

	/**
	 * Sets the current status message.
	 * 
	 * @param currentStatusMessage the new current status message
	 */
	public void setCurrentStatusMessage(StatusMessage currentStatusMessage)
	{
		this.currentStatusMessage = currentStatusMessage;
	}

	/**
	 * Gets the current alarm warning message list.
	 * 
	 * @return the current alarm warning message list
	 */
	public List<CurrentAlarmWarningMessage> getCurrentAlarmWarningMessageList()
	{
		return currentAlarmWarningMessageList;
	}

	/**
	 * Sets the current alarm warning message list.
	 * 
	 * @param currentAlarmWarningMessageList the new current alarm warning message list
	 */
	public void setCurrentAlarmWarningMessageList(List<CurrentAlarmWarningMessage> currentAlarmWarningMessageList)
	{
		this.currentAlarmWarningMessageList = currentAlarmWarningMessageList;
	}

	/**
	 * Gets the light status enum.
	 * 
	 * @return the light status enum
	 */
	public LightStateEnum getLightStateEnum()
	{
		return lightStateEnum;
	}

	/**
	 * Sets the light status enum value.
	 * 
	 * @param lightState the new light state enum value
	 */
	public void setLightStateEnumValue(Integer lightState)
	{
		lightStateEnum = LightStateEnum.enumForValue(lightState);
	}

	/**
	 * Gets the light status enum value.
	 * 
	 * @return the light status enum value
	 */
	public Integer getLightStateEnumValue()
	{
		if (lightStateEnum != null)
		{
			return lightStateEnum.getValue();
		}
		return null;
	}

	/**
	 * Sets the light status enum.
	 * 
	 * @param lightStateEnum the new light state enum
	 */
	public void setLightStateEnum(LightStateEnum lightStateEnum)
	{
		this.lightStateEnum = lightStateEnum;
	}

	/**
	 * Sets the status messages.
	 * 
	 * @param statusMessage the new status messages
	 */
	public void setStatusMessages(List<StatusMessage> statusMessage)
	{
		statusMessages = statusMessage;
	}

	/**
	 * Gets the status messages.
	 * 
	 * @return the status messages
	 */
	public List<StatusMessage> getStatusMessages()
	{
		return statusMessages;
	}

	/**
	 * Gets the protect.
	 * 
	 * @return the protect
	 */
	public Boolean getProtect()
	{
		return protect;
	}

	/**
	 * Setter for property protect.
	 * 
	 * @param protect the new protect
	 */
	public void setProtect(Boolean protect)
	{
		this.protect = protect;
	}

	/**
	 * Gets the light intensity.
	 * 
	 * @return the intensity enum
	 */
	public LightIntensityEnum getLightIntensityEnum()
	{
		return lightIntensityEnum;
	}

	/**
	 * Sets the light intensity.
	 * 
	 * @param intensity the new intensity
	 */
	public void setLightIntensityEnum(LightIntensityEnum intensity)
	{
		lightIntensityEnum = intensity;
	}

	/**
	 * Gets the light intensity enum value.
	 * 
	 * @return the light intensity enum value
	 */
	public Integer getLightIntensityEnumValue()
	{
		if (getLightIntensityEnum() == null)
		{
			return null;
		}
		return getLightIntensityEnum().getValue();
	}

	/**
	 * Sets the light intensity enum value.
	 * 
	 * @param intensity the new light intensity enum value
	 */
	public void setLightIntensityEnumValue(Integer intensity)
	{
		lightIntensityEnum = LightIntensityEnum.enumForValue(intensity);
	}

	/**
	 * Gets the light intensity percentage value.
	 * 
	 * @return the light intensity percentage value
	 */
	public Integer getLightIntensityPercentageValue()
	{
		if (getLightIntensityEnum() == null)
		{
			return null;
		}

		return getLightIntensityEnum().getPercentage();
	}

	/**
	 * Sets the light intensity percentage value.
	 * 
	 * @param intensityPercentage the new light intensity percentage value
	 */
	public void setLightIntensityPercentageValue(Integer intensityPercentage)
	{
		getLightIntensityEnum().setPercentage(intensityPercentage);
	}

	/**
	 * Gets the light blink enum.
	 * 
	 * @return the light blink enum
	 */
	public LightBlinkEnum getLightBlinkEnum()
	{
		return lightBlinkEnum;
	}

	/**
	 * Sets the light blink enum.
	 * 
	 * @param lightBlinkEnum the new light blink enum
	 */
	public void setLightBlinkEnum(LightBlinkEnum lightBlinkEnum)
	{
		this.lightBlinkEnum = lightBlinkEnum;
	}

	/**
	 * Sets the light blink enum value.
	 * 
	 * @param blink the new light blink enum value
	 */
	public void setLightBlinkEnumValue(Integer blink)
	{
		lightBlinkEnum = LightBlinkEnum.enumForValue(blink);
	}

	/**
	 * Gets the light blink enum value.
	 * 
	 * @return the light blink enum value
	 */
	public Integer getLightBlinkEnumValue()
	{
		if (getLightBlinkEnum() == null)
		{
			return null;
		}
		return getLightBlinkEnum().getValue();
	}

	/**
	 * Gets the override enum.
	 * 
	 * @return the override enum
	 */
	public OverrideEnum getOverrideEnum()
	{
		return overrideEnum;
	}

	/**
	 * Sets the override enum.
	 * 
	 * @param overrideEnum the new override enum
	 */
	public void setOverrideEnum(OverrideEnum overrideEnum)
	{
		this.overrideEnum = overrideEnum;
	}

	/**
	 * Sets the override enum value.
	 * 
	 * @param override the new override enum value
	 */
	public void setOverrideEnumValue(Integer override)
	{
		overrideEnum = OverrideEnum.enumForValue(override);
	}

	/**
	 * Gets the override enum value.
	 * 
	 * @return the override enum value
	 */
	public Integer getOverrideEnumValue()
	{
		if (getOverrideEnum() == null)
		{
			return null;
		}
		return getOverrideEnum().getValue();
	}

	/**
	 * Gets the light intensitylevels.
	 * 
	 * @return the light intensitylevels
	 */
	public List<SensusPartNumberConfiguration> getLightIntensitylevels()
	{
		return lightIntensitylevels;
	}

	/**
	 * Sets the light intensitylevels.
	 * 
	 * @param lightIntensitylevels the new light intensitylevels
	 */
	public void setLightIntensitylevels(List<SensusPartNumberConfiguration> lightIntensitylevels)
	{
		this.lightIntensitylevels = lightIntensitylevels;
	}

	/**
	 * Gets the override per date.
	 * 
	 * @return the override per date
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getOverridePerDate()
	{
		return overridePerDate;
	}

	/**
	 * Sets the override per date.
	 * 
	 * @param overridePerDate the new override per date
	 */
	public void setOverridePerDate(Date overridePerDate)
	{
		this.overridePerDate = overridePerDate;
	}

	/**
	 * Gets the override create date.
	 * 
	 * @return the override create date
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getOverrideCreateDate()
	{
		return overrideCreateDate;
	}

	/**
	 * Sets the override create date.
	 * 
	 * @param overrideCreateDate the new override create date
	 */
	public void setOverrideCreateDate(Date overrideCreateDate)
	{
		this.overrideCreateDate = overrideCreateDate;
	}

	/**
	 * Gets the eco mode.
	 * 
	 * @return the eco mode
	 */
	public Double getEcoMode()
	{
		return ecoMode;
	}

	/**
	 * Sets the eco mode.
	 * 
	 * @param ecoModeCalculated the new eco mode
	 */
	public void setEcoMode(Double ecoModeCalculated)
	{
		ecoMode = ecoModeCalculated;
	}

	/**
	 * Gets the eco mode baseline.
	 * 
	 * @return the eco mode baseline
	 */
	public EcoModeBaseline getEcoModeBaseline()
	{
		return ecoModeBaseline;
	}

	/**
	 * Sets the eco mode baseline.
	 * 
	 * @param ecoModeBaseline the new eco mode baseline
	 */
	public void setEcoModeBaseline(EcoModeBaseline ecoModeBaseline)
	{
		this.ecoModeBaseline = ecoModeBaseline;
	}

	/**
	 * Gets the last consumption.
	 * 
	 * @return the last consumption
	 */
	public LightConsumption getLastConsumption()
	{
		return lastConsumption;
	}

	/**
	 * Sets the last consumption.
	 * 
	 * @param lastConsumption the new last consumption
	 */
	public void setLastConsumption(LightConsumption lastConsumption)
	{
		this.lastConsumption = lastConsumption;
	}

	/**
	 * Gets the consumptions.
	 * 
	 * @return the consumptions
	 */
	public List<LightConsumption> getConsumptions()
	{
		return consumptions;
	}

	/**
	 * Sets the consumptions.
	 * 
	 * @param consumptions the new consumptions
	 */
	public void setConsumptions(List<LightConsumption> consumptions)
	{
		this.consumptions = consumptions;
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
	 * Gets the current light status.
	 * 
	 * @return the current light status
	 */
	public LightStatusEnum getCurrentLightStatusEnum()
	{
		return currentLightStatusEnum;
	}

	/**
	 * Sets the current light status.
	 * 
	 * @param currentLightStatusEnumValue the new current light status enum
	 */
	public void setCurrentLightStatusEnum(LightStatusEnum currentLightStatusEnumValue)
	{
		currentLightStatusEnum = currentLightStatusEnumValue;
	}

	/**
	 * Gets the current light status enum value.
	 * 
	 * @return the current light status enum value
	 */
	public Integer getCurrentLightStatusEnumValue()
	{
		if (getCurrentLightStatusEnum() == null)
		{
			return null;
		}
		return getCurrentLightStatusEnum().getValue();
	}

	/**
	 * Sets the current light status enum value.
	 * 
	 * @param currentLightStatusEnumValue the new current light status enum value
	 */
	public void setCurrentLightStatusEnumValue(Integer currentLightStatusEnumValue)
	{
		currentLightStatusEnum = LightStatusEnum.enumForValue(currentLightStatusEnumValue);
	}

	/**
	 * Gets the current alarm warning status subtype.
	 * 
	 * @return the current alarm warning status subtype
	 */
	public String getCurrentAlarmWarningStatusSubtype()
	{
		return currentAlarmWarningStatusSubtype;
	}

	/**
	 * Sets the current alarm warning status subtype.
	 * 
	 * @param currentAlarmWarningStatusSubtype the new current alarm warning status subtype
	 */
	public void setCurrentAlarmWarningStatusSubtype(String currentAlarmWarningStatusSubtype)
	{
		this.currentAlarmWarningStatusSubtype = currentAlarmWarningStatusSubtype;
	}

	/**
	 * Gets the light location.
	 * 
	 * @return the light location
	 */
	public LightLocation getLightLocation()
	{
		return lightLocation;
	}

	/**
	 * Sets the light location.
	 * 
	 * @param lightLocation the new light location
	 */
	public void setLightLocation(LightLocation lightLocation)
	{
		this.lightLocation = lightLocation;
	}

	/**
	 * Gets the light configuration.
	 * 
	 * @return the light configuration
	 */
	public LightConfiguration getLightConfiguration()
	{
		return lightConfiguration;
	}

	/**
	 * Sets the light configuration.
	 * 
	 * @param lightConfiguration the new light configuration
	 */
	public void setLightConfiguration(LightConfiguration lightConfiguration)
	{
		this.lightConfiguration = lightConfiguration;
	}

	/**
	 * Gets the light schedule.
	 * 
	 * @return the light schedule
	 */
	public LightSchedule getLightSchedule()
	{
		return lightSchedule;
	}

	/**
	 * Sets the light schedule.
	 * 
	 * @param lightSchedule the new light schedule
	 */
	public void setLightSchedule(LightSchedule lightSchedule)
	{
		this.lightSchedule = lightSchedule;
	}

	/**
	 * Gets the light last operational data.
	 * 
	 * @return the light last operational data
	 */
	public LightLastOperationalData getLightLastOperationalData()
	{
		return lightLastOperationalData;
	}

	/**
	 * Sets the light last operational data.
	 * 
	 * @param lightLastOperationalData the new light last operational data
	 */
	public void setLightLastOperationalData(LightLastOperationalData lightLastOperationalData)
	{
		this.lightLastOperationalData = lightLastOperationalData;
	}

	/**
	 * Gets the light type enum.
	 * 
	 * @return the light type enum
	 */
	public LightTypeEnum getLightTypeEnum()
	{
		return lightTypeEnum;
	}

	/**
	 * Sets the light type enum.
	 * 
	 * @param lightTypeEnumValue the new light type enum
	 */
	public void setLightTypeEnum(LightTypeEnum lightTypeEnumValue)
	{
		lightTypeEnum = lightTypeEnumValue;
	}

	/**
	 * Gets the light type enum value.
	 * 
	 * @return the light type enum value
	 */
	public Integer getLightTypeEnumValue()
	{
		if (getLightTypeEnum() == null)
		{
			return null;
		}
		return getLightTypeEnum().getValue();
	}

	/**
	 * Sets the light type enum value.
	 * 
	 * @param lightType the new light type enum value
	 */
	public void setLightTypeEnumValue(Integer lightType)
	{
		lightTypeEnum = LightTypeEnum.enumForValue(lightType);
	}

	public StatusMessage getLastMessageReceived()
	{
		return lastMessageReceived;
	}

	public void setLastMessageReceived(StatusMessage lastMessageReceived)
	{
		this.lastMessageReceived = lastMessageReceived;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.model.SmartPoint#toString()
	 */
	@Override
	public String toString()
	{
		return "Light [id=" + id + ", protect=" + protect + ", poleId=" + poleId + ", lightStateEnum=" + lightStateEnum
				+ ", lightIntensityEnum=" + lightIntensityEnum + ", lightBlinkEnum=" + lightBlinkEnum
				+ ", overrideEnum=" + overrideEnum + ", overridePerDate=" + overridePerDate + ", overrideCreateDate="
				+ overrideCreateDate + ", lightIntensitylevels=" + lightIntensitylevels + ", ecoMode=" + ecoMode
				+ ", ecoModeBaseline=" + ecoModeBaseline + ", lastConsumption=" + lastConsumption + ", consumptions="
				+ consumptions + ", currentStatusMessage=" + currentStatusMessage + ", currentLightStatusEnum="
				+ currentLightStatusEnum + ", currentAlarmWarningStatusSubtype=" + currentAlarmWarningStatusSubtype
				+ ", statusMessages=" + statusMessages + ", currentAlarmWarningMessageList="
				+ currentAlarmWarningMessageList + ", lightLocation=" + lightLocation + ", lightConfiguration="
				+ lightConfiguration + ", lightSchedule=" + lightSchedule + ", lightLastOperationalData="
				+ lightLastOperationalData + ", lightTypeEnum=" + lightTypeEnum + "]";
	}
}
