package com.prosperitasglobal.sendsolv.integration.twilio.model.request;

import java.util.List;

import com.prosperitasglobal.sendsolv.integration.twilio.model.IvrIdentity;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.qat.framework.model.request.MaintenanceRequest;

public class IvrRequest extends MaintenanceRequest
{
	private IvrIdentity ivrIdentity;

	private String ivrController;

	private String previousController;

	private String action;

	private String digits;

	private String callSid;

	private Integer durationInSeconds;

	private String mainUrl;

	private String controllerPath;

	private String resourcePath;

	private String staticResourcePath;

	private List<SupportedLanguage> supportedLanguagesList;

	/**
	 * The Constructor.
	 */
	public IvrRequest()
	{

	}

	public IvrIdentity getIvrIdentity()
	{
		return ivrIdentity;
	}

	public void setIvrIdentity(IvrIdentity ivrIdentity)
	{
		this.ivrIdentity = ivrIdentity;
	}

	public String getIvrController()
	{
		return ivrController;
	}

	public void setIvrController(String controllerName)
	{
		ivrController = controllerName;
	}

	public String getDigits()
	{
		return digits;
	}

	public void setDigits(String digits)
	{
		this.digits = digits;
	}

	public String getCallSid()
	{
		return callSid;
	}

	public void setCallSid(String callSid)
	{
		this.callSid = callSid;
	}

	public List<SupportedLanguage> getSupportedLanguagesList()
	{
		return supportedLanguagesList;
	}

	public void setSupportedLanguagesList(List<SupportedLanguage> supportedLanguagesList)
	{
		this.supportedLanguagesList = supportedLanguagesList;
	}

	public String getResourcePath()
	{
		return resourcePath;
	}

	public void setResourcePath(String resourcePath)
	{
		this.resourcePath = resourcePath;
	}

	public Integer getDurationInSeconds()
	{
		return durationInSeconds;
	}

	public void setDurationInSeconds(Integer durationInSeconds)
	{
		this.durationInSeconds = durationInSeconds;
	}

	public String getPreviousController()
	{
		return previousController;
	}

	public void setPreviousController(String previousControllerName)
	{
		previousController = previousControllerName;
	}

	public String getMainUrl()
	{
		return mainUrl;
	}

	public void setMainUrl(String mainUrl)
	{
		this.mainUrl = mainUrl;
	}

	public String getControllerPath()
	{
		return controllerPath;
	}

	public void setControllerPath(String controllerPath)
	{
		this.controllerPath = controllerPath;
	}

	public String getStaticResourcePath()
	{
		return staticResourcePath;
	}

	public void setStaticResourcePath(String staticResourcePath)
	{
		this.staticResourcePath = staticResourcePath;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	@Override
	public String toString()
	{
		return "IvrRequest [getIvrIdentity()=" + getIvrIdentity() + ", getAction()=" + getIvrController()
				+ ", getDigits()="
				+ getDigits() + ", getCallSid()=" + getCallSid() + ", getSupportedLanguagesList()="
				+ getSupportedLanguagesList() + ", getResourcePath()=" + getResourcePath()
				+ ", getDurationInSeconds()=" + getDurationInSeconds() + ", getPreviousAction()="
				+ getPreviousController()
				+ ", getMainUrl()=" + getMainUrl() + ", getActionPath()=" + getControllerPath()
				+ ", getStaticResourcePath()=" + getStaticResourcePath() + ", getSubAction()=" + getAction()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}