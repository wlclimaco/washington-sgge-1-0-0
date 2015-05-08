package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

public class TernaryIvrModel extends BinaryIvrModel
{
	private String additionalViewName2;
	private String additionalNextController2;

	public String getAdditionalViewName2()
	{
		return additionalViewName2;
	}

	public void setAdditionalViewName2(String additionalResourceName2)
	{
		additionalViewName2 = additionalResourceName2;
	}

	public String getAdditionalNextController2()
	{
		return additionalNextController2;
	}

	public void setAdditionalNextController2(String additionalNextAction2)
	{
		additionalNextController2 = additionalNextAction2;
	}

	@Override
	public String toString()
	{
		return "TernaryIvrModel [getAdditionalViewName2()=" + getAdditionalViewName2()
				+ ", getAdditionalNextController2()=" + getAdditionalNextController2() + ", toString()="
				+ super.toString() + "]";
	}
}
