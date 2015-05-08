package com.prosperitasglobal.sendsolv.common.model;

import java.util.Map;

import com.qat.framework.model.UserContext;

/**
 * The SystemResponse Class.
 *
 * @author Flavio Tosta
 *
 */
public class SystemResponse
{
	/** The user context. */
	private UserContext userContext;

	/** The user settings. */
	private UserSettings userSettings;

	/** The user settings. */
	private Map<String, Object> uiProperties;

	private String language;

	/**
	 * @return the userSettings
	 */
	public UserSettings getUserSettings()
	{
		return userSettings;
	}

	/**
	 * @param userSettings the userSettings to set
	 */
	public void setUserSettings(UserSettings userSettings)
	{
		this.userSettings = userSettings;
	}

	/**
	 * Gets the user context.
	 *
	 * @return the user context
	 */
	public UserContext getUserContext()
	{
		return userContext;
	}

	/**
	 * @return the language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Sets the user context.
	 *
	 * @param userContext the new user context
	 */
	public void setUserContext(UserContext userContext)
	{
		this.userContext = userContext;
	}

	/**
	 * Gets the ui properties.
	 *
	 * @return the ui properties
	 */
	public Map<String, Object> getUiProperties()
	{
		return uiProperties;
	}

	/**
	 * Sets the ui properties.
	 *
	 * @param uiProperties the ui properties
	 */
	public void setUiProperties(Map<String, Object> uiProperties)
	{
		this.uiProperties = uiProperties;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SystemResponse [getUserContext()=" + getUserContext() + ", getLanguage()=" + getLanguage()
				+ ", getUiProperties()=" + getUiProperties() + "]";
	}
}
