package com.prosperitasglobal.sendsolv.sdn.model;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class Name extends QATModel
{
	String firstName;
	String lastName;

	public Name(String first, String last)
	{
		setFirstName(first);
		setLastName(last);
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

	@Override
	public String toString()
	{
		return "Name [getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + "]";
	}
}
