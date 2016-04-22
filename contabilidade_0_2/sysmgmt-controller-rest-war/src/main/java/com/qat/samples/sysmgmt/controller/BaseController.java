package com.qat.samples.sysmgmt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * The Class BaseController.
 */
public abstract class BaseController
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

	/**
	 * Fetch active user.
	 *
	 * @return the user
	 */
	@ModelAttribute("activeUser")
	public User fetchActiveUser()
	{
		User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (LOG.isDebugEnabled())
		{
			LOG.debug(loggedUser.toString());
		}
		return loggedUser;
	}

}