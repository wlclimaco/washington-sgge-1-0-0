package com.sensus.mlc.wui.model;

import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.response.SettingsResponse;

/**
 * The Class SystemResponse.
 */
public class SystemResponse extends SettingsResponse
{

	/** The enums. */
	private HashMap<String, HashMap<String, String>> enums;

	/** The settings. */
	private List<Setting> setting;

	/** The properties. */
	private UserContext userContext;

	/** The session. */
	private HashMap<String, Object> session;

	/** The time zone hours. */
	private String timeZoneHours;

	/**
	 * @return the enums
	 */
	public HashMap<String, HashMap<String, String>> getEnums()
	{
		return enums;
	}

	/**
	 * @param enums the enums to set
	 */
	public void setEnums(HashMap<String, HashMap<String, String>> enums)
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
	public HashMap<String, Object> getSession()
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
	public List<Setting> getSetting()
	{
		return setting;
	}

	/**
	 * @param setting the setting to set
	 */
	public void setSetting(List<Setting> setting)
	{
		this.setting = setting;
	}

	public String getTimeZoneHours()
	{
		return timeZoneHours;
	}

	public void setTimeZoneHours(String timeZoneHours)
	{
		this.timeZoneHours = timeZoneHours;
	}

	@Override
	public void setMessageList(List<Message> msgList)
	{
		// TODO Auto-generated method stub
		super.setMessageList(msgList);
	}

}
