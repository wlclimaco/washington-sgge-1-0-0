package com.sensus.lc.settings.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class SettingsRequest.
 */
public class SettingsRequest extends LightingControlRequest
{

	/** The settings. */
	private List<Setting> settings;

	/** The actual password. */
	private String actualPassword;

	/** The new password. */
	private String newPassword;

	/**
	 * Instantiates a new settings request.
	 */
	public SettingsRequest()
	{
	}

	/**
	 * Instantiates a new settings request.
	 * 
	 * @param userContext the user context
	 */
	public SettingsRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new settings request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public SettingsRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the actual password.
	 * 
	 * @return the actual password
	 */
	public String getActualPassword()
	{
		return actualPassword;
	}

	/**
	 * Sets the actual password.
	 * 
	 * @param actualPassword the new actual password
	 */
	public void setActualPassword(String actualPassword)
	{
		this.actualPassword = actualPassword;
	}

	/**
	 * Gets the new password.
	 * 
	 * @return the new password
	 */
	public String getNewPassword()
	{
		return newPassword;
	}

	/**
	 * Sets the new password.
	 * 
	 * @param newPassword the new new password
	 */
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	/**
	 * @return the settings
	 */
	public List<Setting> getSettings()
	{
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setSettings(List<Setting> settings)
	{
		this.settings = settings;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "SettingsRequest [getActualPassword()=" + getActualPassword() + ", getNewPassword()=" + getNewPassword()
				+ ", getSettings()=" + getSettings() + ", toString()=" + super.toString() + "]";
	}
}
