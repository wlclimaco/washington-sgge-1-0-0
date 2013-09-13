package com.sensus.common.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.sensus.common.model.UserContext;

/**
 * This request object provides a base class for other "request" type object to extend from.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Request
{

	/** The user context. */
	private UserContext userContext = null;

	/**
	 * Sets the user context.
	 * 
	 * @param userContext the new user context
	 */
	public void setUserContext(UserContext userContext)
	{
		this.userContext = userContext;
	}

	/**
	 * Gets the user context.
	 * 
	 * @return the user context
	 */
	public UserContext getUserContext()
	{
		return userContext;
	}
}
