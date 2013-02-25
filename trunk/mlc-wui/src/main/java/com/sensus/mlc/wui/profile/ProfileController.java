package com.sensus.mlc.wui.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/profile")
public class ProfileController
{

	public final String PROFILE = "profile/profile_main";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String openSystemSettings()
	{
		return PROFILE;
	}

}
