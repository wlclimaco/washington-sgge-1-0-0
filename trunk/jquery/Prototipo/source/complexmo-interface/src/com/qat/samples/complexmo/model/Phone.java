package com.qat.samples.complexmo.model;

@SuppressWarnings("serial")
public class Phone extends Contact
{
	private String countryCode;
	private String areaCode;
	private String number;
	private String extension;

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getCountryCode()
	{
		return countryCode;
	}

	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getExtension()
	{
		return extension;
	}

	public void setExtension(String extension)
	{
		this.extension = extension;
	}

	@Override
	public String toString()
	{
		return "Phone [getAreaCode()=" + getAreaCode() + ", getCountryCode()=" + getCountryCode() + ", getExtension()="
				+ getExtension() + ", getNumber()=" + getNumber()
				+ ", getContactType()=" + getContactType() + ", getEffectiveEndDate()=" + getEffectiveEndDate()
				+ ", getEffectiveStartDate()=" + getEffectiveStartDate() + ", getParentKey()=" + getParentKey()
				+ ", getPriority()=" + getPriority()
				+ ", isVerified()=" + isVerified() + ", getModelAction()=" + getModelAction() + "]";
	}

}
