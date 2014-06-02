package com.qat.samples.complexmo.model;

import java.util.Calendar;
import java.util.Date;

import com.qat.framework.model.QATModel;
import com.qat.framework.util.QATConvertUtil;

@SuppressWarnings("serial")
public class Contact extends QATModel
{
	private Integer parentKey = -1;
	private ContactTypeEnum contactType = ContactTypeEnum.UNKNOWN;
	private Integer priority;
	private Boolean verified = false;
	private Date effectiveStartDate = Calendar.getInstance().getTime();
	private Date effectiveEndDate;

	public Integer getParentKey()
	{
		return parentKey;
	}

	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	public ContactTypeEnum getContactType()
	{
		return contactType;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 * 
	 * @return
	 */
	public Integer getContactTypeValue()
	{
		return contactType.getValue();
	}

	public void setContactType(ContactTypeEnum contactType)
	{
		this.contactType = contactType;
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 * 
	 * @return
	 */
	public void setContactTypeValue(Integer contactTypeValue)
	{
		contactType = ContactTypeEnum.enumForValue(contactTypeValue);
	}

	public Integer getPriority()
	{
		return priority;
	}

	public void setPriority(Integer priority)
	{
		this.priority = priority;
	}

	public Boolean isVerified()
	{
		return verified;
	}

	public void setVerified(boolean verified)
	{
		this.verified = verified;
	}

	public String getVerifiedValue()
	{
		return QATConvertUtil.fromBoolean(isVerified());
	}

	public void setVerifiedValue(String value)
	{
		verified = QATConvertUtil.toBoolean(value);
	}

	public Date getEffectiveStartDate()
	{
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate)
	{
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate()
	{
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate)
	{
		this.effectiveEndDate = effectiveEndDate;
	}

	@Override
	public String toString()
	{
		return "Contact [getContactType()=" + getContactType()
				+ ", getContactTypeValue()=" + getContactTypeValue() + ", getEffectiveEndDate()="
				+ getEffectiveEndDate() + ", getEffectiveStartDate()=" + getEffectiveStartDate()
				+ ", getParentKey()=" + getParentKey() + ", getPriority()=" + getPriority() + ", isVerified()="
				+ isVerified()
				+ ", getModelAction()=" + getModelAction() + "]";
	}

}
