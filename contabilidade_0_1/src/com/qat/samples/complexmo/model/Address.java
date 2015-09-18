package com.qat.samples.complexmo.model;

@SuppressWarnings("serial")
public class Address extends Contact
{
	private String addressline1;
	private String addressline2;
	private String city;
	private County county;
	private State state;
	private Country country;
	private String postalCode;
	private String note;

	public Address()
	{
	}

	public String getAddressline1()
	{
		return addressline1;
	}

	public void setAddressline1(String addressline1)
	{
		this.addressline1 = addressline1;
	}

	public String getAddressline2()
	{
		return addressline2;
	}

	public void setAddressline2(String addressline2)
	{
		this.addressline2 = addressline2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public County getCounty()
	{
		return county;
	}

	public void setCounty(County county)
	{
		this.county = county;
	}

	public Country getCountry()
	{
		return country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	@Override
	public String toString()
	{
		return "Address [getAddressline1()=" + getAddressline1() + ", getAddressline2()=" + getAddressline2()
				+ ", getCity()=" + getCity() + ", getCountry()=" + getCountry()
				+ ", getCounty()=" + getCounty() + ", getNote()=" + getNote() + ", getPostalCode()=" + getPostalCode()
				+ ", getState()=" + getState()
				+ ", getContactType()=" + getContactType() + ", getEffectiveEndDate()=" + getEffectiveEndDate()
				+ ", getEffectiveStartDate()=" + getEffectiveStartDate()
				+ ", getParentKey()=" + getParentKey() + ", getPriority()=" + getPriority() + ", isVerified()="
				+ isVerified()
				+ ", getModelAction()=" + getModelAction() + "]";
	}
}
