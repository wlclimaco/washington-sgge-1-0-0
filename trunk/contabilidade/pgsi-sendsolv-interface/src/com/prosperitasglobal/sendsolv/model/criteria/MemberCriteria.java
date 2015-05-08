package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Member;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class MemberCriteria implements Serializable
{

	/** The member. */
	private Member member;

	/** The business id. */
	private Integer businessId;

	/** The business type. */
	private BusinessTypeEnum businessType;

	/** The recipient id. */
	private Integer recipientId;

	/** The location name. */
	private String locationName;

	/** The organization name. */
	private String organizationName;

	/** The primary phone number. */
	private String primaryPhoneNumber;

	/**
	 * The Constructor.
	 */
	public MemberCriteria()
	{
		super();
	}

	/**
	 * Gets the business id.
	 *
	 * @return the business id
	 */
	public Integer getBusinessId()
	{
		return businessId;
	}

	/**
	 * Sets the business id.
	 *
	 * @param businessId the business id
	 */
	public void setBusinessId(Integer businessId)
	{
		this.businessId = businessId;
	}

	/**
	 * Gets the business type.
	 *
	 * @return the business type
	 */
	public BusinessTypeEnum getBusinessType()
	{
		return businessType;
	}

	/**
	 * Sets the business type.
	 *
	 * @param businessType the business type
	 */
	public void setBusinessType(BusinessTypeEnum businessType)
	{
		this.businessType = businessType;
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
	 * Sets the business type value.
	 *
	 * @param businessTypeValue the business type value
	 */
	public void setBusinessTypeValue(Integer businessTypeValue)
	{
		businessType = BusinessTypeEnum.enumForValue(businessTypeValue);
	}

	/**
	 * Gets the recipient id.
	 *
	 * @return the recipient id
	 */
	public Integer getRecipientId()
	{
		return recipientId;
	}

	/**
	 * Sets the recipient id.
	 *
	 * @param recipientId the recipient id
	 */
	public void setRecipientId(Integer recipientId)
	{
		this.recipientId = recipientId;
	}

	/**
	 * @return the member
	 */
	public Member getMember()
	{
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(Member member)
	{
		this.member = member;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName()
	{
		return locationName;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}

	/**
	 * @return the organizationName
	 */
	public String getOrganizationName()
	{
		return organizationName;
	}

	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName)
	{
		this.organizationName = organizationName;
	}

	/**
	 * @return the primaryPhoneNumber
	 */
	public String getPrimaryPhoneNumber()
	{
		return primaryPhoneNumber;
	}

	/**
	 * @param primaryPhoneNumber the primaryPhoneNumber to set
	 */
	public void setPrimaryPhoneNumber(String primaryPhoneNumber)
	{
		this.primaryPhoneNumber = primaryPhoneNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MemberCriteria [getBusinessId()=" + getBusinessId() + ", getBusinessType()=" + getBusinessType()
				+ ", getBusinessTypeValue()=" + getBusinessTypeValue() + ", getRecipientId()=" + getRecipientId()
				+ ", getMember()=" + getMember() + ", getLocationName()=" + getLocationName()
				+ ", getOrganizationName()=" + getOrganizationName() + ", getPrimaryPhoneNumber()="
				+ getPrimaryPhoneNumber() + "]";
	}
}
