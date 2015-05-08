package com.prosperitasglobal.sendsolv.model;

/**
 * The Class Liaison models a {@link Person} that acts as a contact point within a {@link Business}.
 *
 * @author abarros
 * @version 1.0
 * @created 05-Sep-2014 11:18:00 AM
 */
@SuppressWarnings("serial")
public class Liaison extends Person
{

	/** The liaison type. */
	private LiaisonTypeEnum liaisonType = LiaisonTypeEnum.OTHER;

	/** The title. */
	private String title;

	/** The location id. */
	private Integer locationId;

	/**
	 * The Constructor.
	 */
	public Liaison()
	{

	}

	/**
	 * Gets the liaison type.
	 *
	 * @return the liaison type
	 */
	public LiaisonTypeEnum getLiaisonType()
	{
		return liaisonType;
	}

	/**
	 * Sets the liaison type.
	 *
	 * @param liaisonType the liaison type
	 */
	public void setLiaisonType(LiaisonTypeEnum liaisonType)
	{
		this.liaisonType = liaisonType;
	}

	/**
	 * Gets the liaison type value.
	 *
	 * @return the liaison type value
	 */
	public Integer getLiaisonTypeValue()
	{
		if (liaisonType == null)
		{
			return null;
		}

		return liaisonType.getValue();
	}

	/**
	 * Sets the status value.
	 *
	 * @param statusValue the status value
	 */
	public void setLiaisonTypeValue(Integer statusValue)
	{
		liaisonType = LiaisonTypeEnum.enumForValue(statusValue);
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the location id.
	 *
	 * @return the location id
	 */
	public Integer getLocationId()
	{
		return locationId;
	}

	/**
	 * Sets the location id.
	 *
	 * @param locationId the location id
	 */
	public void setLocationId(Integer locationId)
	{
		this.locationId = locationId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Liaison [getLiaisonType()=" + getLiaisonType() + ", getLiaisonTypeValue()=" + getLiaisonTypeValue()
				+ ", getTitle()=" + getTitle() + ", getLocationId()=" + getLocationId() + ", toString()="
				+ super.toString() + "]";
	}

}