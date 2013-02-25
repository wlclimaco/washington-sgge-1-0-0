package com.sensus.mlc.wui.base.action;

import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.wui.base.util.PreferencesSessionUtil;

/**
 * The Class MlcPageAction.
 */
@SuppressWarnings("serial")
public class MlcPageAction extends LayoutBase
{

	/** The Constant NOTENTANT. */
	private static final String NOTENTANT = "notenant";

	/** Settings BCF. */
	private ISettingsBCF settingsBCF;

	/** User BCF. */
	private IUserBCF userBCF;

	/** The locale language. */
	private String localeLanguage;

	/**
	 * Gets the server name.
	 * 
	 * @return the server name
	 */
	public String getServerName()
	{
		if (!ValidationUtil.isNull(getServletRequest().getServerName()))
		{
			return getServletRequest().getServerName().trim();
		}
		return getServletRequest().getServerName();
	}

	/**
	 * Creates the user profile.
	 * 
	 * @return the string
	 */
	public String createUserProfile()
	{
		try
		{
			// First we check the user RL address to check what is the Tenant he is trying to connect
			if (LOG.isDebugEnabled())
			{
				LOG.debug("######### CREATE USER PROFILE ####");
			}

			LightingControlRequest request = new LightingControlRequest(getUserContext());
			SettingsResponse response = getSettingsBCF().fetchUserSettings(request);

			if (!response.isOperationSuccess())
			{
				return NOTENTANT;
			}

			List<Setting> settings = response.getSettings();

			PreferencesSessionUtil.setSession(settings, getServletRequest(), true);

		}
		catch (Exception e)
		{
			LOG.error("Error when create user profile", e);
		}

		return SUCCESS;
	}

	/**
	 * Gets the settings bcf.
	 * 
	 * @return the settings bcf
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings bcf.
	 * 
	 * @param settingsBCF the new settings bcf
	 */
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Gets the user bcf.
	 * 
	 * @return the user bcf
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * Sets the user bcf.
	 * 
	 * @param userBCF the new user bcf
	 */
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/**
	 * Gets the locale language.
	 * 
	 * @return the localeLanguage
	 */
	public String getLocaleLanguage()
	{
		return localeLanguage;
	}

	/**
	 * Sets the locale language.
	 * 
	 * @param localeLanguage the localeLanguage to set
	 */
	public void setLocaleLanguage(String localeLanguage)
	{
		this.localeLanguage = localeLanguage;
	}

}
