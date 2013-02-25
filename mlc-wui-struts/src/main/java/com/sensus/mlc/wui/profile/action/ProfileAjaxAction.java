/**
 * 
 */
package com.sensus.mlc.wui.profile.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.util.PreferencesSessionUtil;

/**
 * Struts action for profile Ajax callbacks. This action primarily supports the actions available from
 * the
 * Action menu list.
 * 
 * @author Alexandre Tiveron
 * 
 */

@SuppressWarnings("serial")
public class ProfileAjaxAction extends ActionBase
{
	/** The logger. */
	private final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * The Schedule BCF object.
	 */
	private ISettingsBCF settingsBCF;

	/** The response. */
	private Response response;

	/** The settings request. */
	private SettingsRequest settingsRequest;

	/**
	 * Save profile settings.
	 * 
	 * @return the string
	 */
	public String saveProfileSettings()
	{
		try
		{
			SettingsResponse resp = getSettingsBCF().upsertSettings(getSettingsRequest());
			setResponse(resp);
			if (!ValidationUtil.isNull(resp) && resp.isOperationSuccess())
			{
				PreferencesSessionUtil.setSession(getSettingsRequest().getSettings(), getServletRequest(), false);
			}
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				logger.error("Error Saving properties", e);
			}
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
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * Gets the settings request.
	 * 
	 * @return the settingsRequest
	 */
	public SettingsRequest getSettingsRequest()
	{
		return settingsRequest;
	}

	/**
	 * Sets the settings request.
	 * 
	 * @param settingsRequest the settingsRequest to set
	 */
	public void setSettingsRequest(SettingsRequest settingsRequest)
	{
		this.settingsRequest = settingsRequest;
	}

}
