package com.sensus.mlc.analytics.model;

/**
 * * The Model Object Analytics Group.
 * 
 * @author - Igor Mendes - QAT Brazil
 */
@SuppressWarnings("serial")
public class AnalyticsGroupTypeLight extends AnalyticsGroup
{
	/** The type induction. */
	private Double induction;

	/** The type led. */
	private Double led;

	/** The type other. */
	private Double other;

	/** The total. */
	private Double total;

	/**
	 * Instantiates a new analytics group alarm.
	 */
	public AnalyticsGroupTypeLight()
	{
	}

	/**
	 * Instantiates a new analytics group type light.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param inductionValue the induction value
	 * @param ledValue the led value
	 * @param otherValue the other value
	 * @param totalValue the total value
	 */
	public AnalyticsGroupTypeLight(Integer id, String name, Double inductionValue, Double ledValue, Double otherValue,
			Double totalValue)
	{
		super(id, name);
		setInduction(inductionValue);
		setLed(ledValue);
		setOther(otherValue);
		setTotal(totalValue);
	}

	/**
	 * Gets the induction.
	 * 
	 * @return the induction
	 */
	public Double getInduction()
	{
		return induction;
	}

	/**
	 * Sets the induction.
	 * 
	 * @param induction the new induction
	 */
	public void setInduction(Double induction)
	{
		this.induction = induction;
	}

	/**
	 * Gets the led.
	 * 
	 * @return the led
	 */
	public Double getLed()
	{
		return led;
	}

	/**
	 * Sets the led.
	 * 
	 * @param led the new led
	 */
	public void setLed(Double led)
	{
		this.led = led;
	}

	/**
	 * Gets the other.
	 * 
	 * @return the other
	 */
	public Double getOther()
	{
		return other;
	}

	/**
	 * Sets the other.
	 * 
	 * @param other the new other
	 */
	public void setOther(Double other)
	{
		this.other = other;
	}

	/**
	 * Gets the total.
	 * 
	 * @return the total
	 */
	public Double getTotal()
	{
		return total;
	}

	/**
	 * Sets the total.
	 * 
	 * @param total the new total
	 */
	public void setTotal(Double total)
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
		return "AnalyticsGroupTypeLight [getInduction()=" + getInduction() + ", getLed()=" + getLed() + ", getOther()="
				+ getOther() + ", getTotal()=" + getTotal() + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getColumns()=" + getColumns() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
