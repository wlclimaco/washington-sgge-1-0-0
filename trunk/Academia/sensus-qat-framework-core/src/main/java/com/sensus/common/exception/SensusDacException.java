package com.sensus.common.exception;


/**
 * Use this class within a given DAC where you need to throw an exception.<br/>
 * Often this is done in the context of requiring a rollback to occur. Make sure you populate the
 * {@link com.sensus.common.model.MessageInfo} collection with the reason for the exception.
 */
public class SensusDacException extends SensusRuntimeException
{

	/**
	 * Instantiates a new QAT Dac exception.
	 */
	public SensusDacException()
	{
		super();
	}

	/**
	 * Instantiates a new QAT DAC exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public SensusDacException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Instantiates a new QAT Dac exception.
	 * 
	 * @param message the message
	 */
	public SensusDacException(String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new QAT Dac exception.
	 * 
	 * @param cause the cause
	 */
	public SensusDacException(Throwable cause)
	{
		super(cause);
	}

}
