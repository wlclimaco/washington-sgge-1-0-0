package com.sensus.lc.light.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class Light.
 */
@SuppressWarnings("serial")
public class Light extends Device
{
	// General Light fields.

	/** The id. */
	private Integer id; // Not to be confused with DeviceId which is not used for SLC.

	/** The protect. */
	private Boolean protect;

	/** The pole id. */
	private String poleId;

	/** The life cycle state. */
	private LifeCycleStateEnum lifeCycleState;

	/** The intensity. */
	private IntensityEnum intensity;

	/** The blink status. */
	private BlinkStatusEnum blinkStatus;

	/** The Override ***. */
	private OverrideEnum overrideType;

	/** The override per date. */
	private Date overridePerDate;

	/** The override create date. */
	private Date overrideCreateDate;

	/** The light type LED, INDUCTIVE, OTHER. */
	private LightTypeEnum lightType;

	/** The light last operational data. */
	private LastOperationalData lastOperationalData = new LastOperationalData();

	/** The light schedule. */
	private LightSchedule lightSchedule = new LightSchedule();

	/** The light configuration. */
	private Configuration configuration = new Configuration();

	/** The tenant. */
	private Tenant tenant;

	// EcoMode fields

	/** The eco mode. */
	private Double ecoMode;

	/** The eco mode baseline. */
	private EcoModeBaseline ecoModeBaseline;

	/** The last consumption. */
	private Consumption lastConsumption;

	/** The consumptions. */
	private List<Consumption> consumptions = new ArrayList<>();

	// Schedule fields
	/** The event schedule. */
	private EventSchedule eventSchedule;

	/** The offset schedule. */
	private OffsetSchedule offsetSchedule;

	/** The last notification history. */
	private NotificationHistory lastNotificationHistory;

	/**
	 * Instantiates a new light.
	 */
	public Light()
	{
		super();
		setRadio(new Radio(new Location()));
		setDeviceType(DeviceTypeEnum.LIGHT);
	}

	/**
	 * Instantiates a new light.
	 * 
	 * @param lightId the light id
	 */
	public Light(Integer lightId)
	{
		this();
		setId(lightId);
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
	 * Gets the protect.
	 * 
	 * @return the protect
	 */
	public Boolean getProtect()
	{
		return protect;
	}

	/**
	 * Sets the protect.
	 * 
	 * @param protect the new protect
	 */
	public void setProtect(Boolean protect)
	{
		this.protect = protect;
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
	 * Gets the life cycle state.
	 * 
	 * @return the life cycle state
	 */
	public LifeCycleStateEnum getLifeCycleState()
	{
		return lifeCycleState;
	}

	/**
	 * Gets the life cycle state value.
	 * 
	 * @return the life cycle state value
	 */
	public Integer getLifeCycleStateValue()
	{
		if (lifeCycleState != null)
		{
			return lifeCycleState.getValue();
		}
		return null;
	}

	/**
	 * Sets the life cycle state value.
	 * 
	 * @param lifeCycleStateValue the new life cycle state value
	 */
	public void setLifeCycleStateValue(Integer lifeCycleStateValue)
	{
		lifeCycleState = LifeCycleStateEnum.enumForValue(lifeCycleStateValue);
	}

	/**
	 * Sets the life cycle state.
	 * 
	 * @param lifeCycleState the new life cycle state
	 */
	public void setLifeCycleState(LifeCycleStateEnum lifeCycleState)
	{
		this.lifeCycleState = lifeCycleState;
	}

	/**
	 * Gets the intensity.
	 * 
	 * @return the intensity
	 */
	public IntensityEnum getIntensity()
	{
		return intensity;
	}

	/**
	 * Sets the intensity.
	 * 
	 * @param intensity the new intensity
	 */
	public void setIntensity(IntensityEnum intensity)
	{
		this.intensity = intensity;
	}

	/**
	 * Gets the intensity percentage.
	 * 
	 * @return the intensity percentage
	 */
	public Integer getIntensityPercentage()
	{
		if (getIntensity() == null)
		{
			return null;
		}

		return getIntensity().getPercentage();
	}

	/**
	 * Gets the intensity value.
	 * 
	 * @return the intensity value
	 */
	public Integer getIntensityValue()
	{
		if (intensity != null)
		{
			return intensity.getValue();
		}
		return null;
	}

	/**
	 * Sets the intensity value.
	 * 
	 * @param intensityValue the new intensity value
	 */
	public void setIntensityValue(Integer intensityValue)
	{
		intensity = IntensityEnum.enumForValue(intensityValue);
	}

	/**
	 * Gets the blink status.
	 * 
	 * @return the blink status
	 */
	public BlinkStatusEnum getBlinkStatus()
	{
		return blinkStatus;
	}

	/**
	 * Sets the blink status.
	 * 
	 * @param blinkStatus the new blink status
	 */
	public void setBlinkStatus(BlinkStatusEnum blinkStatus)
	{
		this.blinkStatus = blinkStatus;
	}

	/**
	 * Gets the blink status value.
	 * 
	 * @return the blink status value
	 */
	public Integer getBlinkStatusValue()
	{
		if (blinkStatus != null)
		{
			return blinkStatus.getValue();
		}
		return null;
	}

	/**
	 * Sets the blink status value.
	 * 
	 * @param blinkStatusValue the new blink status value
	 */
	public void setBlinkStatusValue(Integer blinkStatusValue)
	{
		blinkStatus = BlinkStatusEnum.enumForValue(blinkStatusValue);
	}

	/**
	 * Gets the override type.
	 * 
	 * @return the override type
	 */
	public OverrideEnum getOverrideType()
	{
		return overrideType;
	}

	/**
	 * Sets the override type.
	 * 
	 * @param overrideType the new override type
	 */
	public void setOverrideType(OverrideEnum overrideType)
	{
		this.overrideType = overrideType;
	}

	/**
	 * Gets the override type value.
	 * 
	 * @return the override type value
	 */
	public Integer getOverrideTypeValue()
	{
		if (overrideType != null)
		{
			return overrideType.getValue();
		}
		return null;
	}

	/**
	 * Sets the override type value.
	 * 
	 * @param overrideTypeValue the new override type value
	 */
	public void setOverrideTypeValue(Integer overrideTypeValue)
	{
		overrideType = OverrideEnum.enumForValue(overrideTypeValue);
	}

	/**
	 * Gets the override per date.
	 * 
	 * @return the override per date
	 */
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
	 * Gets the light type.
	 * 
	 * @return the light type
	 */
	public LightTypeEnum getLightType()
	{
		return lightType;
	}

	/**
	 * Gets the light type value.
	 * 
	 * @return the light type value
	 */
	public Integer getLightTypeValue()
	{
		if (lightType != null)
		{
			return lightType.getValue();
		}
		return null;
	}

	/**
	 * Sets the light type.
	 * 
	 * @param lightType the new light type
	 */
	public void setLightType(LightTypeEnum lightType)
	{
		this.lightType = lightType;
	}

	/**
	 * Sets the light type.
	 * 
	 * @param lightTypeValue the new light type
	 */
	public void setLightTypeValue(Integer lightTypeValue)
	{
		lightType = LightTypeEnum.enumForValue(lightTypeValue);
	}

	/**
	 * Gets the last operational data.
	 * 
	 * @return the last operational data
	 */
	public LastOperationalData getLastOperationalData()
	{
		return lastOperationalData;
	}

	/**
	 * Sets the last operational data.
	 * 
	 * @param lastOperationalData the new last operational data
	 */
	public void setLastOperationalData(LastOperationalData lastOperationalData)
	{
		this.lastOperationalData = lastOperationalData;
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
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 */
	public Configuration getConfiguration()
	{
		return configuration;
	}

	/**
	 * Sets the configuration.
	 * 
	 * @param configuration the new configuration
	 */
	public void setConfiguration(Configuration configuration)
	{
		this.configuration = configuration;
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
	 * @param ecoMode the new eco mode
	 */
	public void setEcoMode(Double ecoMode)
	{
		this.ecoMode = ecoMode;
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

	public Integer getDeviceLifeCycleStateValue()
	{
		if (getDeviceLifeCycleState() == null)
		{
			return null;
		}
		return getDeviceLifeCycleState().getValue();
	}

	/**
	 * Sets the light state.
	 * 
	 * @param lightStateEnum the lightStateEnum to set
	 */
	public void setDeviceLifeCycleStateValue(Integer deviceLifeCycleStateValue)
	{
		setDeviceLifeCycleState(LightStateEnum.enumForValue(deviceLifeCycleStateValue));
	}

	/**
	 * Gets the consumptions.
	 * 
	 * @return the consumptions
	 */
	public List<Consumption> getConsumptions()
	{
		return consumptions;
	}

	/**
	 * Sets the consumptions.
	 * 
	 * @param consumptions the consumptions to set
	 */
	public void setConsumptions(List<Consumption> consumptions)
	{
		this.consumptions = consumptions;
	}

	/**
	 * Adds the consumption.
	 * 
	 * @param consumption the consumption
	 */
	public void addConsumption(Consumption consumption)
	{
		getConsumptions().add(consumption);
	}

	/**
	 * Gets the last consumption.
	 * 
	 * @return the lastConsumption
	 */
	public Consumption getLastConsumption()
	{
		return lastConsumption;
	}

	/**
	 * Sets the last consumption.
	 * 
	 * @param lastConsumption the lastConsumption to set
	 */
	public void setLastConsumption(Consumption lastConsumption)
	{
		this.lastConsumption = lastConsumption;
	}

	/**
	 * Gets the tenant.
	 * 
	 * @return the tenant
	 */
	public Tenant getTenant()
	{
		return tenant;
	}

	/**
	 * Sets the tenant.
	 * 
	 * @param tenant the tenant to set
	 */
	public void setTenant(Tenant tenant)
	{
		this.tenant = tenant;
	}

	/**
	 * Gets the event schedule.
	 * 
	 * @return the event schedule
	 */
	public EventSchedule getEventSchedule()
	{
		return eventSchedule;
	}

	/**
	 * Sets the event schedule.
	 * 
	 * @param eventSchedule the new event schedule
	 */
	public void setEventSchedule(EventSchedule eventSchedule)
	{
		this.eventSchedule = eventSchedule;
	}

	/**
	 * Gets the offset schedule.
	 * 
	 * @return the offset schedule
	 */
	public OffsetSchedule getOffsetSchedule()
	{
		return offsetSchedule;
	}

	/**
	 * Sets the offset schedule.
	 * 
	 * @param offsetSchedule the new offset schedule
	 */
	public void setOffsetSchedule(OffsetSchedule offsetSchedule)
	{
		this.offsetSchedule = offsetSchedule;
	}

	/**
	 * @return the lastNotificationHistory
	 */
	public NotificationHistory getLastNotificationHistory()
	{
		return lastNotificationHistory;
	}

	/**
	 * @param lastNotificationHistory the lastNotificationHistory to set
	 */
	public void setLastNotificationHistory(NotificationHistory lastNotificationHistory)
	{
		this.lastNotificationHistory = lastNotificationHistory;
	}

	@Override
	public String toString()
	{
		return "Light [getId()=" + getId() + ", getProtect()=" + getProtect() + ", getPoleId()=" + getPoleId()
				+ ", getLifeCycleState()=" + getLifeCycleState() + ", getLifeCycleStateValue()="
				+ getLifeCycleStateValue() + ", getIntensity()=" + getIntensity() + ", getIntensityPercentage()="
				+ getIntensityPercentage() + ", getIntensityValue()=" + getIntensityValue() + ", getBlinkStatus()="
				+ getBlinkStatus() + ", getBlinkStatusValue()=" + getBlinkStatusValue() + ", getOverrideType()="
				+ getOverrideType() + ", getOverrideTypeValue()=" + getOverrideTypeValue() + ", getOverridePerDate()="
				+ getOverridePerDate() + ", getOverrideCreateDate()=" + getOverrideCreateDate() + ", getLightType()="
				+ getLightType() + ", getLightTypeValue()=" + getLightTypeValue() + ", getLastOperationalData()="
				+ getLastOperationalData() + ", getLightSchedule()=" + getLightSchedule() + ", getConfiguration()="
				+ getConfiguration() + ", getEcoMode()=" + getEcoMode() + ", getEcoModeBaseline()="
				+ getEcoModeBaseline() + ", getDeviceLifeCycleStateValue()=" + getDeviceLifeCycleStateValue()
				+ ", getConsumptions()=" + getConsumptions() + ", getLastConsumption()=" + getLastConsumption()
				+ ", getTenant()=" + getTenant() + ", getEventSchedule()=" + getEventSchedule()
				+ ", getOffsetSchedule()=" + getOffsetSchedule() + ", getLastNotificationHistory()="
				+ getLastNotificationHistory() + ", toString()=" + super.toString() + "]";
	}
}
