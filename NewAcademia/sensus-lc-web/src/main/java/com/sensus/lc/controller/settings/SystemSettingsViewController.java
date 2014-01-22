package com.sensus.lc.controller.settings;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.settings.bcf.ISettingsBCF;
import com.sensus.lc.settings.model.response.SettingsResponse;

/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/generalsettings")
public class SystemSettingsViewController extends BaseViewController
{
	/*
	 * URLs Mapping
	 */

	/** The Constant SYSTEM_SETTINGS. */
	private static final String FETCH_SETTINGS = "";

	/** The Constant VIEW_SYSTEM_SETTINGS_MAIN. */
	private static final String VIEW_SYSTEM_SETTINGS_MAIN = "/systemsettings/general_settings";

	/** The Constant TIME_ZONE. */
	public static final String TIME_ZONE = "TIME_ZONE";

	/**
	 * The ISettingsBCF object.
	 */
	private ISettingsBCF settingsBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SystemSettingsViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SystemSettingsViewController";

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
	 * @param settingsBCF
	 *            the new settings bcf
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * System.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_SETTINGS, method = RequestMethod.GET)
	public ModelAndView system(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_SYSTEM_SETTINGS_MAIN);
		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}
		SettingsResponse settingsResponse = new SettingsResponse();

		// ADD user context to request
		LightingControlRequest lightingControlRequest = new LightingControlRequest(getUserContext(request));

		settingsResponse = getSettingsBCF().fetchSystemSettings(lightingControlRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(settingsResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}
}
