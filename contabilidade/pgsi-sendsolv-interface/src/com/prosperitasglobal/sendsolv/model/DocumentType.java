package com.prosperitasglobal.sendsolv.model;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.qat.framework.model.QATModel;

/**
 * The Class DocumentType carries information about the different categories of documents allowed in the system.
 */
@SuppressWarnings("serial")
public class DocumentType extends QATModel
{
	/** Attributes. */
	private Integer id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The applicability determines whether a type is valid for {@link Person} or {@link Business} . */
	private BusinessTypeEnum applicability;

	/**
	 * The Constructor.
	 */
	public DocumentType()
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
	 * @param name the name
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
	 * @param description the description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the applicability.
	 *
	 * @return the applicability
	 */
	public BusinessTypeEnum getApplicability()
	{
		return applicability;
	}

	/**
	 * Sets the applicability.
	 *
	 * @param applicability the applicability
	 */
	public void setApplicability(BusinessTypeEnum applicability)
	{
		this.applicability = applicability;
	}

	/**
	 * Gets the applicability value.
	 *
	 * @return the applicability value
	 */
	public Integer getApplicabilityValue()
	{
		if (applicability == null)
		{
			return null;
		}

		return applicability.getValue();
	}

	/**
	 * Sets the applicability value.
	 *
	 * @param applicabilityValue the applicability value
	 */
	public void setApplicabilityValue(Integer applicabilityValue)
	{
		applicability = BusinessTypeEnum.enumForValue(applicabilityValue);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DocumentType [getId()=" + getId() + ", getName()=" + getName() + ", getDescription()="
				+ getDescription() + ", getApplicability()=" + getApplicability() + ", getApplicabilityValue()="
				+ getApplicabilityValue() + ", toString()=" + super.toString() + "]";
	}

}