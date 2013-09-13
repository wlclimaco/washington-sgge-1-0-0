package com.sensus.common.exception;

/**
 * Use this class within a given BAC where you need to throw an exception.<br/>
 * Often this is done in the context of requiring a rollback to occur. Make sure you populate the
 * {@link com.sensus.common.model.MessageInfo} collection with the reason for the exception.
 */
public class SensusBacException extends SensusRuntimeException
{

	/**
	 * Instantiates a new QAT BAC exception.
	 */
	public SensusBacException()
	{
		super();
	}

	/**
	 * Instantiates a new QAT BAC exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public SensusBacException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Instantiates a new QAT BAC exception.
	 * 
	 * @param message the message
	 */
	public SensusBacException(String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new QAT BAC exception.
	 * 
	 * @param cause the cause
	 */
	public SensusBacException(Throwable cause)
	{
		super(cause);
	}

}
