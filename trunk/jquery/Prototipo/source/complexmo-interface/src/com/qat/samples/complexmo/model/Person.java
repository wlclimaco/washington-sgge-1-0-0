package com.qat.samples.complexmo.model;

import java.util.List;

import com.qat.framework.model.QATModelOL;

@SuppressWarnings("serial")
public class Person extends QATModelOL
{
	private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer employeeNumber;
	private List<Contact> contactList;
	private EFT eft;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public Integer getEmployeeNumber()
	{
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber)
	{
		this.employeeNumber = employeeNumber;
	}

	public List<Contact> getContactList()
	{
		return contactList;
	}

	public void setContactList(List<Contact> contactList)
	{
		this.contactList = contactList;
	}

	public EFT getEft()
	{
		return eft;
	}

	public void setEft(EFT eft)
	{
		this.eft = eft;
	}

	@Override
	public String toString()
	{
		return "Person [getId()=" + getId() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getMiddleName()=" + getMiddleName() + ", getEmployeeNumber()=" + getEmployeeNumber()
				+ ", getContactList()=" + getContactList() + ", getEft()=" + getEft() + "]";
	}

}
