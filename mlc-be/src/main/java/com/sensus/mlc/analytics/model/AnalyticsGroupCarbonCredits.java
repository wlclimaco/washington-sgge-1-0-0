package com.sensus.mlc.analytics.model;

/**
 * * The Model Object Analytics Group Carbon Credits.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
@SuppressWarnings("serial")
public class AnalyticsGroupCarbonCredits extends AnalyticsGroup
{
	/** The credits created. */
	private Double creditsCreated;

	/** The energy saved. */
	private Double energySaved;

	/** The barrels of oil saved. */
	private Double barrelsOfOilSaved;

	/** The tons of co saved. */
	private Double tonsOfCOSaved;

	/**
	 * Instantiates a new analytics group alarm.
	 */
	public AnalyticsGroupCarbonCredits()
	{
	}

	/**
	 * Instantiates a new analytics group carbon credits.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param creditsCreatedValue the credits created value
	 * @param energySavedValue the energy saved value
	 * @param barrelsOfOilSavedValue the barrels of oil saved value
	 * @param tonsOfCOSavedValue the tons of co saved value
	 */
	public AnalyticsGroupCarbonCredits(Integer id, String name, Double creditsCreatedValue, Double energySavedValue,
			Double barrelsOfOilSavedValue, Double tonsOfCOSavedValue)
	{
		super(id, name);
		setCreditsCreated(creditsCreatedValue);
		setEnergySaved(energySavedValue);
		setBarrelsOfOilSaved(barrelsOfOilSavedValue);
		setTonsOfCOSaved(tonsOfCOSavedValue);
	}

	/**
	 * Gets the credits created.
	 * 
	 * @return the credits created
	 */
	public Double getCreditsCreated()
	{
		return creditsCreated;
	}

	/**
	 * Sets the credits created.
	 * 
	 * @param creditsCreated the new credits created
	 */
	public void setCreditsCreated(Double creditsCreated)
	{
		this.creditsCreated = creditsCreated;
	}

	/**
	 * Gets the energy saved.
	 * 
	 * @return the energy saved
	 */
	public Double getEnergySaved()
	{
		return energySaved;
	}

	/**
	 * Sets the energy saved.
	 * 
	 * @param energySaved the new energy saved
	 */
	public void setEnergySaved(Double energySaved)
	{
		this.energySaved = energySaved;
	}

	/**
	 * Gets the barrels of oil saved.
	 * 
	 * @return the barrels of oil saved
	 */
	public Double getBarrelsOfOilSaved()
	{
		return barrelsOfOilSaved;
	}

	/**
	 * Sets the barrels of oil saved.
	 * 
	 * @param barrelsOfOilSaved the new barrels of oil saved
	 */
	public void setBarrelsOfOilSaved(Double barrelsOfOilSaved)
	{
		this.barrelsOfOilSaved = barrelsOfOilSaved;
	}

	/**
	 * Gets the tons of co saved.
	 * 
	 * @return the tons of co saved
	 */
	public Double getTonsOfCOSaved()
	{
		return tonsOfCOSaved;
	}

	/**
	 * Sets the tons of co saved.
	 * 
	 * @param tonsOfCOSaved the new tons of co saved
	 */
	public void setTonsOfCOSaved(Double tonsOfCOSaved)
	{
		this.tonsOfCOSaved = tonsOfCOSaved;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.model.AnalyticsGroup#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyticsGroupCarbonCredits [getCreditsCreated()=" + getCreditsCreated() + ", getEnergySaved()="
				+ getEnergySaved() + ", getBarrelsOfOilSaved()=" + getBarrelsOfOilSaved() + ", getTonsOfCOSaved()="
				+ getTonsOfCOSaved() + ", getId()=" + getId() + ", getName()=" + getName() + ", getColumns()="
				+ getColumns() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
