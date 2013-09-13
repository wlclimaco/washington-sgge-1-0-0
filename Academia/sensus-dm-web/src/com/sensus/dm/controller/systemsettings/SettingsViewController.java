package com.sensus.dm.controller.systemsettings;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.property.model.response.PropertyResponse;
import com.sensus.dm.controller.base.BaseViewController;
import com.sensus.dm.elec.settings.bcf.ISettingsBCF;

/**
 * The Class SettingsViewController.
 */
@Controller
@RequestMapping("/settings")
public class SettingsViewController extends BaseViewController
{

	/** The Constant SYSTEM. */
	private static final String SYSTEM = "/system";

	/** The Constant PROFILE. */
	private static final String PROFILE = "/profile";

	/** The Constant VIEW_SYSTEM_SETTINGS. */
	private static final String VIEW_SYSTEM_SETTINGS = "/systemsettings/system_settings_general_main";

	/** The Constant VIEW_PROFILE. */
	private static final String VIEW_PROFILE = "/profile/profile_main";

	/** The Constant PROPERTIES. */
	private static final String PROPERTIES = "properties";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "SettingsViewController";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SettingsViewController.class);

	/** The settings BCF. */
	private ISettingsBCF settingsBCF;

	/**
	 * Gets the settings BCF.
	 * 
	 * @return the settingsBCF
	 */
	public ISettingsBCF getSettingsBCF()
	{
		return settingsBCF;
	}

	/**
	 * Sets the settings BCF.
	 * 
	 * @param settingsBCF the settingsBCF to set
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Profile.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = PROFILE, method = RequestMethod.GET)
	public ModelAndView profile(HttpServletRequest request)
	{
		PropertyRequest propertyRequest = new PropertyRequest();

		// ADD user context to request
		addUserContextToRequest(propertyRequest);

		PropertyResponse propertyResponse = getSettingsBCF().fetchUserSettings(propertyRequest);
		ModelAndView modelAndView = new ModelAndView(VIEW_PROFILE);
		try
		{
			modelAndView.addObject(PROPERTIES, getMapper().writeValueAsString(propertyResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(PROPERTIES, null);
		}

		return modelAndView;
	}

	/**
	 * System.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = SYSTEM, method = RequestMethod.GET)
	public ModelAndView system(HttpServletRequest request)
	{
		PropertyRequest propertyRequest = new PropertyRequest();

		// ADD user context to request
		addUserContextToRequest(propertyRequest);

		PropertyResponse propertyResponse = getSettingsBCF().fetchSystemSettings(propertyRequest);
		ModelAndView modelAndView = new ModelAndView(VIEW_SYSTEM_SETTINGS);
		try
		{
			modelAndView.addObject(PROPERTIES, getMapper().writeValueAsString(propertyResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(PROPERTIES, null);
		}

		return modelAndView;
	}
}
