package com.sensus.lc.controller.profile;

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
import com.sensus.lc.user.bcf.IUserBCF;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.UserRequest;
import com.sensus.lc.user.model.response.UserResponse;

/**
 * The Class ProfileViewController.
 * 
 * @author QATEmployee
 */
@Controller
@RequestMapping("/profile")
public class ProfileViewController extends BaseViewController
{
	/*
	 * URLs Mapping
	 */

	/** The Constant PROFILE. */
	private static final String FETCH_PROFILE = "";

	/** The Constant SUCCESS. */
	public static final String SUCCESS = "success";

	/** The Constant VIEW_PROFILE_MAIN. */
	private static final String VIEW_PROFILE_MAIN = "/profile/profile_main";

	/** The Constant TIME_ZONE. */
	public static final String TIME_ZONE = "TIME_ZONE";

	/** The Constant USER_RESPONSE. */
	public static final String USER_RESPONSE = "user_response";

	/**
	 * The Schedule BCF object.
	 */
	private ISettingsBCF settingsBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProfileViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "ProfileViewController";

	/** The user bcf. */
	private IUserBCF userBCF;

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
	@Resource
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
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
	 * @param settingsBCF
	 *            the new settings bcf
	 */
	@Resource
	public void setSettingsBCF(ISettingsBCF settingsBCF)
	{
		this.settingsBCF = settingsBCF;
	}

	/**
	 * Open settings.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_PROFILE, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_PROFILE_MAIN);
		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		LightingControlRequest lightingControlRequest = new LightingControlRequest(getUserContext(request));
		lightingControlRequest.setUserId(lightingControlRequest.getUserContext().getId());
		SettingsResponse settingsResponse = getSettingsBCF().fetchUserSettings(lightingControlRequest);

		User user = new User();
		user.setId(getUserContext(request).getId());

		UserRequest userRequest = new UserRequest(getUserContext(request));
		userRequest.setUser(user);

		UserResponse userResponse = getUserBCF().fetchUserById(userRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(settingsResponse));
			modelAndView.addObject(USER_RESPONSE, getMapper().writeValueAsString(userResponse));
		}
		catch (Exception e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;

	}
}
