package com.prosperitasglobal.sendsolv.integration.twilio.model;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class SupportedLanguage extends QATModel
{
	String ivrOption;
	String language;
	String greeting;
	String voice;

	public String getIvrOption()
	{
		return ivrOption;
	}

	public void setIvrOption(String ivrOption)
	{
		this.ivrOption = ivrOption;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getGreeting()
	{
		return greeting;
	}

	public void setGreeting(String greeting)
	{
		this.greeting = greeting;
	}

	public String getVoice()
	{
		return voice;
	}

	public void setVoice(String voice)
	{
		this.voice = voice;
	}

	@Override
	public String toString()
	{
		return "SupportedLanguage [getIvrOption()=" + getIvrOption() + ", getLanguage()=" + getLanguage()
				+ ", getGreeting()=" + getGreeting() + ", getVoice()=" + getVoice() + ", toString()="
				+ super.toString() + "]";
	}
}
