package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * The Class PersonName is used to carry information about other names used by {@link Person} other than their primary
 * name.
 */
@SuppressWarnings("serial")
public class PersonName extends QATModel
{

	/** The id. */
	private Integer id;

	/** The person id. */
	private Integer personId;

	/** The other name. */
	private String otherName;

	/**
	 * The Constructor.
	 */
	public PersonName()
	{

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
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public Integer getPersonId()
	{
		return personId;
	}

	/**
	 * Sets the person id.
	 *
	 * @param personId the person id
	 */
	public void setPersonId(Integer personId)
	{
		this.personId = personId;
	}

	/**
	 * Gets the other name.
	 *
	 * @return the other name
	 */
	public String getOtherName()
	{
		return otherName;
	}

	/**
	 * Sets the other name.
	 *
	 * @param otherName the other name
	 */
	public void setOtherName(String otherName)
	{
		this.otherName = otherName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PersonName [getId()=" + getId() + ", getPersonId()=" + getPersonId() + ", getOtherName()="
				+ getOtherName() + ", toString()=" + super.toString() + "]";
	}

}