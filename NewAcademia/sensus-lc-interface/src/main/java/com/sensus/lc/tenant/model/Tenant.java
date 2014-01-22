package com.sensus.lc.tenant.model;

/**
 * The Class Tenant.
 */
@SuppressWarnings("serial")
public class Tenant extends com.sensus.common.model.Tenant
{
	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The rni code. */
	private String rniCode;

	/** The server name. */
	private String serverName;

	/** The gatewayURL. */
	private String gatewayURL;

	/** The latitude. */
	private Double latitude;

	/** The longitude. */
	private Double longitude;

	/** The minimum smartpoint communication failure time. */
	private Integer minSmartpointCommTime;

	/** The light time zone. */
	private String lightTimeZone;

	/** The ecoMode disable. */
	private Boolean ecoModeDisable;

	/** The batch process hour timetime. */
	private Integer batchProcessTime;

	/** The communication cycle timeout. */
	private Integer communicationCycleTimeout;

	/** The number api access hour. */
	private Integer numberApiAccessHour;

	/**
	 * Instantiates a new tenant.
	 */
	public Tenant()
	{
	}

	/**
	 * Instantiates a new abstract tenant.
	 * 
	 * @param id the id
	 */
	public Tenant(Integer id)
	{
		super(id);
	}

	/**
	 * Instantiates a new tenant.
	 * 
	 * @param rniCodeParam the rni code param
	 */
	public Tenant(String rniCodeParam)
	{
		setRniCode(rniCodeParam);
	}

	/**
	 * Instantiates a new tenant.
	 * 
	 * @param idParam the id param
	 * @param nameParam the name param
	 * @param descriptionParam the description param
	 * @param rniCodeParam the rni code param
	 */
	public Tenant(Integer idParam, String nameParam, String descriptionParam, String rniCodeParam)
	{
		this(idParam);
		setName(nameParam);
		setDescription(descriptionParam);
		setRniCode(rniCodeParam);
	}

	/**
	 * Instantiates a new tenant.
	 * 
	 * @param idParam the id param
	 * @param nameParam the name param
	 * @param descriptionParam the description param
	 * @param rniCodeParam the rni code param
	 * @param gatewayURL the gateway url
	 */
	public Tenant(Integer idParam, String nameParam, String descriptionParam, String rniCodeParam,
			String gatewayURLValue)
	{
		this(idParam, nameParam, descriptionParam, rniCodeParam);
		setGatewayURL(gatewayURLValue);
	}

	/**
	 * @return the communicationCycleTimeout
	 */
	public Integer getCommunicationCycleTimeout()
	{
		return communicationCycleTimeout;
	}

	/**
	 * @param communicationCycleTimeout the communicationCycleTimeout to set
	 */
	public void setCommunicationCycleTimeout(Integer communicationCycleTimeout)
	{
		this.communicationCycleTimeout = communicationCycleTimeout;
	}

	/**
	 * @return the numberApiAccessHour
	 */
	public Integer getNumberApiAccessHour()
	{
		return numberApiAccessHour;
	}

	/**
	 * @param numberApiAccessHour the numberApiAccessHour to set
	 */
	public void setNumberApiAccessHour(Integer numberApiAccessHour)
	{
		this.numberApiAccessHour = numberApiAccessHour;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the RNI code.
	 * 
	 * @return the RNI code
	 */
	public String getRniCode()
	{
		return rniCode;
	}

	/**
	 * Sets the RNI code.
	 * 
	 * @param rniCode the new RNI code
	 */
	public void setRniCode(String rniCode)
	{
		this.rniCode = rniCode;
	}

	/**
	 * Sets the server name.
	 * 
	 * @return the server name
	 */
	public String getServerName()
	{
		return serverName;
	}

	/**
	 * Sets the server name.
	 * 
	 * @param serverName the new server name
	 */
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}

	/**
	 * Sets the gateway url.
	 * 
	 * @param gatewayURL the new gateway url
	 */
	public void setGatewayURL(String gatewayURL)
	{
		this.gatewayURL = gatewayURL;
	}

	/**
	 * Gets the gateway url.
	 * 
	 * @return the gateway url
	 */
	public String getGatewayURL()
	{
		return gatewayURL;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public Double getLatitude()
	{
		return latitude;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude the new latitude
	 */
	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public Double getLongitude()
	{
		return longitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude the new longitude
	 */
	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * Gets the min smartpoint comm time.
	 * 
	 * @return the min smartpoint comm time
	 */
	public Integer getMinSmartpointCommTime()
	{
		return minSmartpointCommTime;
	}

	/**
	 * Sets the min smartpoint comm time.
	 * 
	 * @param minSmartpointCommTime the new min smartpoint comm time
	 */
	public void setMinSmartpointCommTime(Integer minSmartpointCommTime)
	{
		this.minSmartpointCommTime = minSmartpointCommTime;
	}

	/**
	 * Gets the light time zone.
	 * 
	 * @return the light time zone
	 */
	public String getLightTimeZone()
	{
		return lightTimeZone;
	}

	/**
	 * Sets the light time zone.
	 * 
	 * @param lightTimeZone the new light time zone
	 */
	public void setLightTimeZone(String lightTimeZone)
	{
		this.lightTimeZone = lightTimeZone;
	}

	/**
	 * Gets the ecoModeDisable.
	 * 
	 * @return the ecoModeDisable
	 */
	public Boolean getEcoModeDisable()
	{
		return ecoModeDisable;
	}

	/**
	 * Sets the ecoMode Disable.
	 * 
	 * @param ecoModeDisable the new ecoMode Disable
	 */
	public void setEcoModeDisable(Boolean ecoModeDisable)
	{
		this.ecoModeDisable = ecoModeDisable;
	}

	public Integer getBatchProcessTime()
	{
		return batchProcessTime;
	}

	public void setBatchProcessTime(Integer batchProcessTime)
	{
		this.batchProcessTime = batchProcessTime;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Tenant [getCommunicationCycleTimeout()=" + getCommunicationCycleTimeout()
				+ ", getNumberApiAccessHour()=" + getNumberApiAccessHour() + ", getName()=" + getName()
				+ ", getDescription()=" + getDescription() + ", getRniCode()=" + getRniCode() + ", getServerName()="
				+ getServerName() + ", getGatewayURL()=" + getGatewayURL() + ", getLatitude()=" + getLatitude()
				+ ", getLongitude()=" + getLongitude() + ", getMinSmartpointCommTime()=" + getMinSmartpointCommTime()
				+ ", getLightTimeZone()=" + getLightTimeZone() + ", getEcoModeDisable()=" + getEcoModeDisable()
				+ ", getBatchProcessTime()=" + getBatchProcessTime() + "]";
	}

}
