package com.prosperitasglobal.sendsolv.integration.twilio.model;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class IvrIdentity extends QATModel
{
	String phoneNumber;
	String pinNumber;

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getPinNumber()
	{
		return pinNumber;
	}

	public void setPinNumber(String pinNumber)
	{
		this.pinNumber = pinNumber;
	}

	@Override
	public String toString()
	{
		return "IvrIdentity [getPhoneNumber()=" + getPhoneNumber() + ", getPinNumber()=" + getPinNumber()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + "]";
	}
}
