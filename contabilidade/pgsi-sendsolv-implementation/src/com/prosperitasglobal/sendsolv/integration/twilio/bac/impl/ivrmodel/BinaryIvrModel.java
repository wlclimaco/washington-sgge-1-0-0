package com.prosperitasglobal.sendsolv.integration.twilio.bac.impl.ivrmodel;

public class BinaryIvrModel extends IvrModel
{
	private String additionalViewName;
	private String additionalNextController;

	public String getAdditionalViewName()
	{
		return additionalViewName;
	}

	public void setAdditionalViewName(String additionalResourceName)
	{
		this.additionalViewName = additionalResourceName;
	}

	public String getAdditionalNextController()
	{
		return additionalNextController;
	}

	public void setAdditionalNextController(String additionalNextAction)
	{
		this.additionalNextController = additionalNextAction;
	}

	@Override
	public String toString()
	{
		return "BinaryIvrAction [getFailResourceName()=" + getAdditionalViewName() + ", getFailNextAction()="
				+ getAdditionalNextController() + ", getResourceName()=" + getViewName() + ", getNextAction()="
				+ getNextController() + ", getExceptionResourceName()=" + getExceptionViewName() + "]";
	}
}
