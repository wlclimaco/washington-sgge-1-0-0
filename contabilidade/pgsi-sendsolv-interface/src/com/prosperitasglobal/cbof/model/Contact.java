package com.prosperitasglobal.cbof.model;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.prosperitasglobal.sendsolv.model.PriorityEnum;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.util.QATConvertUtil;

/**
 * The Class Contact.
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@Type(value = Email.class, name = "email"),
		@Type(value = Phone.class, name = "phone"),
		@Type(value = Address.class, name = "address")})
@SuppressWarnings("serial")
public class Contact extends QATModelOL
{
	/** The id. */
	private Integer id;

	/** The parent id. */
	private Integer parentKey = -1;

	/** The type of business. */
	private BusinessTypeEnum parentKeyType = BusinessTypeEnum.UNKNOWN;

	/** The contact type. */
	private ContactTypeEnum contactType = ContactTypeEnum.UNKNOWN;

	/** The priority. */
	private PriorityEnum priority = PriorityEnum.UNKNOWN;

	/** Flag to indicate if verified. */
	private Boolean verified = false;

	/** The effective start date. */
	private Long effectiveStartDate = System.currentTimeMillis();

	/** The effective end date. */
	private Long effectiveEndDate;

	/**
	 * The Constructor.
	 */
	public Contact()
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
	 * Gets the parent key.
	 *
	 * @return the parent key
	 */
	public Integer getParentKey()
	{
		return parentKey;
	}

	/**
	 * Sets the parent key.
	 *
	 * @param parentKey the parent key
	 */
	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	/**
	 * ParentKeyType get/set and support for Enum.
	 *
	 * @return the parent key type
	 */
	public BusinessTypeEnum getParentKeyType()
	{
		return parentKeyType;
	}

	/**
	 * Sets the parent key type.
	 *
	 * @param parentKeyType the parent key type
	 */
	public void setParentKeyType(BusinessTypeEnum parentKeyType)
	{
		this.parentKeyType = parentKeyType;
	}

	/**
	 * Sets the parent key value.
	 *
	 * @param parentKeyValue the parent key value
	 */
	public void setParentKeyValue(Integer parentKeyValue)
	{
		parentKeyType = BusinessTypeEnum.enumForValue(parentKeyValue);
	}

	/**
	 * Gets the parent key value.
	 *
	 * @return the parent key value
	 */
	public Integer getParentKeyValue()
	{
		if (parentKeyType != null)
		{
			return parentKeyType.getValue();
		}
		return null;
	}

	/**
	 * ContactType get/set and support for Enum.
	 *
	 * @return the contact type
	 */

	public ContactTypeEnum getContactType()
	{
		return contactType;
	}

	/**
	 * Sets the contact type.
	 *
	 * @param contactType the contact type
	 */
	public void setContactType(ContactTypeEnum contactType)
	{
		this.contactType = contactType;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 *
	 * @return the contact type value
	 */
	public Integer getContactTypeValue()
	{
		if (contactType != null)
		{
			return contactType.getValue();
		}
		return null;
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 *
	 * @param contactTypeValue the contact type value
	 */
	public void setContactTypeValue(Integer contactTypeValue)
	{
		contactType = ContactTypeEnum.enumForValue(contactTypeValue);
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public PriorityEnum getPriority()
	{
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the priority
	 */
	public void setPriority(PriorityEnum priority)
	{
		this.priority = priority;
	}

	/**
	 * Get the value of the priority.
	 *
	 * @return The value.
	 */
	public Integer getPriorityValue()
	{
		if (priority != null)
		{
			return priority.getValue();
		}
		return null;
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 *
	 * @param priorityValue the contact type value
	 */
	public void setPriorityValue(Integer priorityValue)
	{
		priority = PriorityEnum.enumForValue(priorityValue);
	}

	/**
	 * Checks if is verified.
	 *
	 * @return true, if checks if is verified
	 */
	public Boolean isVerified()
	{
		return verified;
	}

	/**
	 * Sets the verified.
	 *
	 * @param verified the verified
	 */
	public void setVerified(Boolean verified)
	{
		this.verified = verified;
	}

	/**
	 * Gets the verified value.
	 *
	 * @return the verified value
	 */
	public String getVerifiedValue()
	{
		return QATConvertUtil.fromBoolean(isVerified());
	}

	/**
	 * Sets the verified value.
	 *
	 * @param value the verified value
	 */
	public void setVerifiedValue(String value)
	{
		verified = QATConvertUtil.toBoolean(value);
	}

	/**
	 * Gets the effective start date.
	 *
	 * @return the effective start date
	 */
	public Long getEffectiveStartDate()
	{
		return effectiveStartDate;
	}

	/**
	 * Sets the effective start date.
	 *
	 * @param effectiveStartDate the effective start date
	 */
	public void setEffectiveStartDate(Long effectiveStartDate)
	{
		this.effectiveStartDate = effectiveStartDate;
	}

	/**
	 * Gets the effective end date.
	 *
	 * @return the effective end date
	 */
	public Long getEffectiveEndDate()
	{
		return effectiveEndDate;
	}

	/**
	 * Sets the effective end date.
	 *
	 * @param effectiveEndDate the effective end date
	 */
	public void setEffectiveEndDate(Long effectiveEndDate)
	{
		this.effectiveEndDate = effectiveEndDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Contact [getId()=" + getId() + ", getParentKey()=" + getParentKey() + ", getParentKeyType()="
				+ getParentKeyType() + ", getParentKeyValue()=" + getParentKeyValue() + ", getContactType()="
				+ getContactType() + ", getContactTypeValue()=" + getContactTypeValue() + ", getPriority()="
				+ getPriority() + ", getPriorityValue()=" + getPriorityValue() + ", isVerified()=" + isVerified()
				+ ", getVerifiedValue()=" + getVerifiedValue() + ", getEffectiveStartDate()=" + getEffectiveStartDate()
				+ ", getEffectiveEndDate()=" + getEffectiveEndDate() + ", toString()=" + super.toString() + "]";
	}

}
