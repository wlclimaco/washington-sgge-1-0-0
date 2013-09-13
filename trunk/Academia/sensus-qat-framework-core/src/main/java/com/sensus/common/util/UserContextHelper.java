package com.sensus.common.util;

import com.sensus.common.model.UserContext;

/**
 * The Class UserContextHelper. Used to set and get a thread local instance of a UserContext object.
 */
public final class UserContextHelper
{

	private static ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>()
	{
	};

	private UserContextHelper()
	{
	}

	/**
	 * Gets the thread local UserContext.
	 * 
	 * @return The UserContext - If not set then a new, default, UserContext will be created, set, and returned.
	 */
	public static UserContext getUserContext()
	{
		UserContext myUserContext = userContext.get();

		if (myUserContext == null)
		{
			myUserContext = new UserContext();
			setUserContext(myUserContext);
		}

		return myUserContext;
	}

	/**
	 * Sets the thread local UserContext.
	 * 
	 * @param context the new user context
	 */
	public static void setUserContext(UserContext context)
	{
		userContext.set(context);
	}

}
