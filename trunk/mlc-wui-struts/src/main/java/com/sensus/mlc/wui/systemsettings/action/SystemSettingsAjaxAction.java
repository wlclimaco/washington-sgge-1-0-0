/*
 * 
 */
package com.sensus.mlc.wui.systemsettings.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.AllEnumsDefinitions;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.util.PreferencesSessionUtil;

/**
 * Struts action for System Settings Ajax callbacks. This action primarily supports the actions available from
 * the
 * Action menu list.
 * 
 * @author Alexandre Tiveron
 * 
 */

@SuppressWarnings("serial")
public class SystemSettingsAjaxAction extends ActionBase
{

	/** CONSTANTS *. */

	/** The Constant FETCH_ERROR_MESSAGE. */
	public static final String FETCH_ERROR_MESSAGE = "Error searching for properties";

	/** The logger. */
	private final Log logger = LogFactory.getLog(this.getClass());

	/** The settings request. */
	private SettingsRequest settingsRequest;

	/** The response. */
	private Response response;

	/**
	 * The Schedule BCF object.
	 */
	private ISettingsBCF settingsBCF;

	/** The response. */
	private HashMap<String, HashMap<String, String>> enumResponse;

	/**
	 * Open profile.
	 * 
	 * @return the string
	 */
	public String openProfile()
	{
		try
		{
			LightingControlRequest request = new LightingControlRequest(getUserContext());
			SettingsResponse settingsResp = getSettingsBCF().fetchUserSettings(request);// fetchSystemSettings(request);
			setResponse(settingsResp);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				logger.error(FETCH_ERROR_MESSAGE, e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Save profile settings.
	 * 
	 * @return the string
	 */
	public String saveSystemSettings()
	{
		try
		{
			SettingsRequest request = getSettingsRequest();
			SettingsResponse resp = getSettingsBCF().upsertSettings(request);
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
	 * Fill enuns.
	 * 
	 * @return the string
	 */
	public String fillEnuns()
	{

		try
		{
			setEnumResponse(AllEnumsDefinitions.getSystemEnumsDefinitions());
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				logger.error("Error when filling enums", e);
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
	 * Gets the enum response.
	 * 
	 * @return the enumResponse
	 */
	public HashMap<String, HashMap<String, String>> getEnumResponse()
	{
		return enumResponse;
	}

	/**
	 * Sets the enum response.
	 * 
	 * @param enumResponse the enumResponse to set
	 */
	public void setEnumResponse(HashMap<String, HashMap<String, String>> enumResponse)
	{
		this.enumResponse = enumResponse;
	}

	/**
	 * Gets the settings request.
	 * 
	 * @return the settingsRequest
	 */
	public SettingsRequest getSettingsRequest()
	{
		settingsRequest.setUserContext(getUserContext());
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

}
