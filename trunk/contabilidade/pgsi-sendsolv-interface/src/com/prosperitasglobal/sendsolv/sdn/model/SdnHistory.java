package com.prosperitasglobal.sendsolv.sdn.model;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;

/**
 * The Class SdnHistory.
 */
public class SdnHistory
{

	/** The name. */
	private String name;

	/** The sdn status. */
	private SDNStatusEnum sdnStatus;

	/** The business type. */
	private BusinessTypeEnum businessType;

	/** The note text. */
	private String noteText;

	/** The date. */
	private Long date;

	/** The parent key. */
	private Integer parentKey;

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
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the sdn status.
	 *
	 * @return the sdnStatus
	 */
	public SDNStatusEnum getSdnStatus()
	{
		return sdnStatus;
	}

	/**
	 * Sets the sdn status.
	 *
	 * @param sdnStatus the sdnStatus to set
	 */
	public void setSdnStatus(SDNStatusEnum sdnStatus)
	{
		this.sdnStatus = sdnStatus;
	}

	/**
	 * Gets the business type.
	 *
	 * @return the businessType
	 */
	public BusinessTypeEnum getBusinessType()
	{
		return businessType;
	}

	/**
	 * Sets the business type.
	 *
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(BusinessTypeEnum businessType)
	{
		this.businessType = businessType;
	}

	/**
	 * Gets the note text.
	 *
	 * @return the noteText
	 */
	public String getNoteText()
	{
		return noteText;
	}

	/**
	 * Sets the note text.
	 *
	 * @param noteText the noteText to set
	 */
	public void setNoteText(String noteText)
	{
		this.noteText = noteText;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Long getDate()
	{
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the date to set
	 */
	public void setDate(Long date)
	{
		this.date = date;
	}

	/**
	 * @return the parentKey
	 */
	public Integer getParentKey()
	{
		return parentKey;
	}

	/**
	 * @param parentKey the parentKey to set
	 */
	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	/**
	 * Gets the business type value.
	 *
	 * @return the business type value
	 */
	public Integer getBusinessTypeValue()
	{
		if (businessType == null)
		{
			return null;
		}

		return businessType.getValue();
	}

	/**
	 * Gets the sdn status value.
	 *
	 * @return the sdn status value
	 */
	public Integer getSdnStatusValue()
	{
		if (sdnStatus == null)
		{
			return null;
		}

		return sdnStatus.getValue();
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 *
	 * @param businessValue the business type value
	 */
	public void setBusinessTypeValue(Integer businessValue)
	{
		businessType = BusinessTypeEnum.enumForValue(businessValue);
	}

	/**
	 * Sets the sdn status value.
	 *
	 * @param sdnStatusValue the new sdn status value
	 */
	public void setSdnStatusValue(Integer sdnStatusValue)
	{
		sdnStatus = SDNStatusEnum.enumForValue(sdnStatusValue);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SdnHistory [getName()=" + getName() + ", getSdnStatus()=" + getSdnStatus() + ", getBusinessType()="
				+ getBusinessType() + ", getNoteText()=" + getNoteText() + ", getDate()=" + getDate()
				+ ", getParentKey()=" + getParentKey() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
