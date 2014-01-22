package com.sensus.lc.controller.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.response.SettingsResponse;

/**
 * The Class SystemResponse.
 */
public class SystemResponse extends SettingsResponse
{

	/** The enums. */
	private Map<String, HashMap<String, String>> enums;

	/** The settings. */
	private List<Setting> settings;

	/** The properties. */
	private UserContext userContext;

	/** The session. */
	private Map<String, Object> session;

	/** The ui properties. */
	private Map<String, String> uiProperties;

	/** The system properties. */
	private Map<String, String> systemProperties;

	/**
	 * @return the enums
	 */
	public Map<String, HashMap<String, String>> getEnums()
	{
		return enums;
	}

	/**
	 * @return the uiProperties
	 */
	public Map<String, String> getUiProperties()
	{
		return uiProperties;
	}

	/**
	 * @param uiProperties the uiProperties to set
	 */
	public void setUiProperties(Map<String, String> uiProperties)
	{
		this.uiProperties = uiProperties;
	}

	/**
	 * @param enums the enums to set
	 */
	public void setEnums(Map<String, HashMap<String, String>> enums)
	{
		this.enums = enums;
	}

	public UserContext getUserContext()
	{
		return userContext;
	}

	public void setUserContext(UserContext userContext)
	{
		this.userContext = userContext;
	}

	/**
	 * @return the session
	 */
	public Map<String, Object> getSession()
	{
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HashMap<String, Object> session)
	{
		this.session = session;
	}

	/**
	 * Adds the attribute to session.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void addAttributeToSession(String key, Object value)
	{
		if (ValidationUtil.isNull(getSession()))
		{
			setSession(new HashMap<String, Object>());
		}

		getSession().put(key, value);
	}

	/**
	 * @return the setting
	 */
	@Override
	public List<Setting> getSettings()
	{
		return settings;
	}

	/**
	 * @param setting the setting to set
	 */
	@Override
	public void setSettings(List<Setting> setting)
	{
		settings = setting;
	}

	/**
	 * @return the systemProperties
	 */
	public Map<String, String> getSystemProperties()
	{
		return systemProperties;
	}

	/**
	 * @param systemProperties the systemProperties to set
	 */
	public void setSystemProperties(Map<String, String> systemProperties)
	{
		this.systemProperties = systemProperties;
	}

}
