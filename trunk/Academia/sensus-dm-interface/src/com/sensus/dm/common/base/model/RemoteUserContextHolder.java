package com.sensus.dm.common.base.model;

/**
 * This class provides ThreadLocal storage for a RemoteUserContext object. It is
 * non-instantiable (abstract) by design.
 */
public abstract class RemoteUserContextHolder
{

	/** The Constant contextHolder. */
	private static final ThreadLocal<RemoteUserContext> CONTEXT_HOLDER = new ThreadLocal<RemoteUserContext>();

	/**
	 * Associate the given <code>RemoteUserContext</code> with the current
	 * thread.
	 * 
	 * @param context
	 *            the current message context
	 */
	public static void setContext(RemoteUserContext context)
	{
		CONTEXT_HOLDER.set(context);
	}

	/**
	 * Return the <code>RemoteUserContext</code> associated with the current
	 * thread, if any.
	 * 
	 * @return the current context, or <code>null</code> if none
	 */
	public static RemoteUserContext getContext()
	{
		return CONTEXT_HOLDER.get();
	}

	/**
	 * Clear the current context.
	 */
	public static void clearContext()
	{
		CONTEXT_HOLDER.set(null);
	}
}
