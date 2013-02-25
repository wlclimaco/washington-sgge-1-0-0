package com.sensus.mlc.wui.base.unittest.util;

/**
 * Base class for mock BCF implementation. Provides the mode field and common constants.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public abstract class BaseMockImpl
{

	/**
	 * Indicates the object should provide a successful response.
	 */
	public static final String MODE_SUCCESS = "success";

	/**
	 * Indicates the object should provide a successful but empty response (for fetch operations).
	 */
	public static final String MODE_EMPTY = "empty";

	/**
	 * Indicates the object should provide a failed response.
	 */
	public static final String MODE_FAILURE = "fail";

	/**
	 * Indicates the object should throw an exception. The real BCFs shouldn't throw exceptions but this is useful to
	 * test certain logic paths in the calling class.
	 */
	public static final String MODE_EXCEPTION = "exception";

	/**
	 * The mode field. Defaults to success mode.
	 */
	private String mode = MODE_SUCCESS;

	/**
	 * Default message code for error message.
	 */
	public static final String MESSAGE_ERROR = "error";

	/**
	 * Default message code for warning message.
	 */
	public static final String MESSAGE_WARN = "warn";

	/**
	 * Default message code for info message.
	 */
	public static final String MESSAGE_INFO = "info";

	/**
	 * Constructs a new Base Mock Object.
	 */
	public BaseMockImpl()
	{
		super();
	}

	/**
	 * Set the response mode.
	 * 
	 * @param mode the response mode
	 */
	public void setMode(String modeIn)
	{
		mode = modeIn;
	}

	/**
	 * Get the response mode.
	 * 
	 * @return the response mode
	 */
	public String getMode()
	{
		return mode;
	}

}