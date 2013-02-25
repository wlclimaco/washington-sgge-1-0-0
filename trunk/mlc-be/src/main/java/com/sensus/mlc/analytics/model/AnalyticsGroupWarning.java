package com.sensus.mlc.analytics.model;

/**
 * * The Model Object Analytics Group.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
@SuppressWarnings("serial")
public class AnalyticsGroupWarning extends AnalyticsGroup
{
	/** The lamp failure. */
	private Integer powerSurge;

	/** The power failure. */
	private Integer brownoutDetected;

	/** The communication failure. */
	private Integer communicationFail;

	/** The high current. */
	private Integer highCurrent;

	/** The low current. */
	private Integer lowCurrent;

	/** The reverse energy. */
	private Integer reverseEnergy;

	/** The metrology reset. */
	private Integer metrologyReset;

	/** The total. */
	private Integer total;

	/**
	 * Instantiates a new analytics group warning.
	 */
	public AnalyticsGroupWarning()
	{
	}

	/**
	 * Instantiates a new analytics group warning.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param powerSurgeValue the power surge value
	 * @param brownoutDetectedValue the brownout detected value
	 * @param totalValue the total value
	 */
	public AnalyticsGroupWarning(Integer id, String name, Integer powerSurgeValue, Integer brownoutDetectedValue,
			Integer totalValue)
	{
		super(id, name);
		setPowerSurge(powerSurgeValue);
		setBrownoutDetected(brownoutDetectedValue);
		setTotal(totalValue);
	}

	/**
	 * Gets the power surge.
	 * 
	 * @return the power surge
	 */
	public Integer getPowerSurge()
	{
		return powerSurge;
	}

	/**
	 * Sets the power surge.
	 * 
	 * @param powerSurge the new power surge
	 */
	public void setPowerSurge(Integer powerSurge)
	{
		this.powerSurge = powerSurge;
	}

	/**
	 * Gets the brownout detected.
	 * 
	 * @return the brownout detected
	 */
	public Integer getBrownoutDetected()
	{
		return brownoutDetected;
	}

	/**
	 * Sets the brownout detected.
	 * 
	 * @param brownoutDetected the new brownout detected
	 */
	public void setBrownoutDetected(Integer brownoutDetected)
	{
		this.brownoutDetected = brownoutDetected;
	}

	/**
	 * Gets the total.
	 * 
	 * @return the total
	 */
	public Integer getTotal()
	{
		return total;
	}

	/**
	 * Sets the total.
	 * 
	 * @param total the new total
	 */
	public void setTotal(Integer total)
	{
		this.total = total;
	}

	/**
	 * Gets the communication fail.
	 * 
	 * @return the communicationFail
	 */
	public Integer getCommunicationFail()
	{
		return communicationFail;
	}

	/**
	 * Sets the communication fail.
	 * 
	 * @param communicationFail the communicationFail to set
	 */
	public void setCommunicationFail(Integer communicationFail)
	{
		this.communicationFail = communicationFail;
	}

	/**
	 * Gets the high current.
	 * 
	 * @return the high current
	 */
	public Integer getHighCurrent()
	{
		return highCurrent;
	}

	/**
	 * Sets the high current.
	 * 
	 * @param highCurrent the new high current
	 */
	public void setHighCurrent(Integer highCurrent)
	{
		this.highCurrent = highCurrent;
	}

	/**
	 * Gets the low current.
	 * 
	 * @return the low current
	 */
	public Integer getLowCurrent()
	{
		return lowCurrent;
	}

	/**
	 * Sets the low current.
	 * 
	 * @param lowCurrent the new low current
	 */
	public void setLowCurrent(Integer lowCurrent)
	{
		this.lowCurrent = lowCurrent;
	}

	/**
	 * Gets the reverse energy.
	 * 
	 * @return the reverse energy
	 */
	public Integer getReverseEnergy()
	{
		return reverseEnergy;
	}

	/**
	 * Sets the revers eenergy.
	 * 
	 * @param reverseEnergy the new reverse energy
	 */
	public void setReverseEnergy(Integer reverseEnergy)
	{
		this.reverseEnergy = reverseEnergy;
	}

	/**
	 * Gets the metrology reset.
	 * 
	 * @return the metrology reset
	 */
	public Integer getMetrologyReset()
	{
		return metrologyReset;
	}

	/**
	 * Sets the metrology reset.
	 * 
	 * @param metrologyReset the new metrology reset
	 */
	public void setMetrologyReset(Integer metrologyReset)
	{
		this.metrologyReset = metrologyReset;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.model.AnalyticsGroup#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyticsGroupWarning [getPowerSurge()=" + getPowerSurge() + ", getBrownoutDetected()="
				+ getBrownoutDetected() + ", getTotal()=" + getTotal() + ", getCommunicationFail()="
				+ getCommunicationFail() + ", getHighCurrent()=" + getHighCurrent() + ", getLowCurrent()="
				+ getLowCurrent() + ", getReverseEnergy()=" + getReverseEnergy() + ", getMetrologyReset()="
				+ getMetrologyReset() + ", getId()=" + getId() + ", getName()=" + getName() + ", getColumns()="
				+ getColumns() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
