package com.qat.samples.complexmo.model;

@SuppressWarnings("serial")
public class Email extends Contact
{

	private String emailaddress;

	public String getEmailAddress()
	{
		return emailaddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		emailaddress = emailAddress;
	}

	@Override
	public String toString()
	{
		return "Email [getEmailAddress()=" + getEmailAddress() + ", getContactType()=" + getContactType()
				+ ", getEffectiveEndDate()=" + getEffectiveEndDate() + ", getEffectiveStartDate()="
				+ getEffectiveStartDate() + ", getParentKey()="
				+ getParentKey() + ", getPriority()=" + getPriority() + ", isVerified()=" + isVerified()
				+ ", getModelAction()=" + getModelAction() + "]";
	}

}
