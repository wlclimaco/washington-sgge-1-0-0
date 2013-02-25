package com.sensus.mlc.analytics.model;

/**
 * * The Model Object Analytics Group.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
@SuppressWarnings("serial")
public class AnalyticsGroupAlarm extends AnalyticsGroup
{
	/** The lamp failure. */
	private Integer lampFailure;

	/** The power failure. */
	private Integer powerFailure;

	/** The board failure. */
	private Integer boardFailure;

	/** The metrology error. */
	private Integer metrologyError;

	/** The metrology com failure. */
	private Integer metrologyComFailure;

	/** The total. */
	private Integer total;

	/**
	 * Instantiates a new analytics group alarm.
	 */
	public AnalyticsGroupAlarm()
	{
	}

	/**
	 * Instantiates a new analytics group alarm.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param lampFailureValue the lamp failure value
	 * @param powerFailureValue the power failure value
	 * @param totalValue the total value
	 */
	public AnalyticsGroupAlarm(Integer id, String name, Integer lampFailureValue, Integer powerFailureValue,
			Integer totalValue)
	{
		super(id, name);
		setLampFailure(lampFailureValue);
		setPowerFailure(powerFailureValue);
		setTotal(totalValue);
	}

	/**
	 * Gets the lamp failure.
	 * 
	 * @return the lamp failure
	 */
	public Integer getLampFailure()
	{
		return lampFailure;
	}

	/**
	 * Sets the lamp failure.
	 * 
	 * @param lampFailure the new lamp failure
	 */
	public void setLampFailure(Integer lampFailure)
	{
		this.lampFailure = lampFailure;
	}

	/**
	 * Gets the power failure.
	 * 
	 * @return the power failure
	 */
	public Integer getPowerFailure()
	{
		return powerFailure;
	}

	/**
	 * Sets the power failure.
	 * 
	 * @param powerFailure the new power failure
	 */
	public void setPowerFailure(Integer powerFailure)
	{
		this.powerFailure = powerFailure;
	}

	/**
	 * Gets the board failure.
	 * 
	 * @return the board failure
	 */
	public Integer getBoardFailure()
	{
		return boardFailure;
	}

	/**
	 * Sets the board failure.
	 * 
	 * @param boardFailure the new board failure
	 */
	public void setBoardFailure(Integer boardFailure)
	{
		this.boardFailure = boardFailure;
	}

	/**
	 * Gets the metrology error.
	 * 
	 * @return the metrology error
	 */
	public Integer getMetrologyError()
	{
		return metrologyError;
	}

	/**
	 * Sets the metrology error.
	 * 
	 * @param metrologyError the new metrology error
	 */
	public void setMetrologyError(Integer metrologyError)
	{
		this.metrologyError = metrologyError;
	}

	/**
	 * Gets the metrology com failure.
	 * 
	 * @return the metrology com failure
	 */
	public Integer getMetrologyComFailure()
	{
		return metrologyComFailure;
	}

	/**
	 * Sets the metrology com failure.
	 * 
	 * @param metrologyComFailure the new metrology com failure
	 */
	public void setMetrologyComFailure(Integer metrologyComFailure)
	{
		this.metrologyComFailure = metrologyComFailure;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.model.AnalyticsGroup#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyticsGroupAlarm [getLampFailure()=" + getLampFailure() + ", getPowerFailure()=" + getPowerFailure()
				+ ", getBoardFailure()=" + getBoardFailure() + ", getMetrologyError()=" + getMetrologyError()
				+ ", getMetrologyComFailure()=" + getMetrologyComFailure() + ", getTotal()=" + getTotal()
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getColumns()=" + getColumns()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
