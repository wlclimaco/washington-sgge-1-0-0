package com.sensus.lc.api.service.model;

import org.springframework.http.HttpStatus;

/**
 * Encapsulates an API exception including a text message and HTTP status code.
 */
@SuppressWarnings("serial")
public class APIException extends Exception
{

	/** The message. */
	private String message;

	/** The http status. */
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	/**
	 * Instantiates new with just a message.
	 * 
	 * @param message the message
	 */
	public APIException(String message)
	{
		this.message = message;
	}

	/**
	 * Instantiates with a message and http status code.
	 * 
	 * @param message the message
	 * @param status the status
	 */
	public APIException(String message, HttpStatus status)
	{
		setMessage(message);
		setHttpStatus(status);
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	@Override
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Gets the http status.
	 * 
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}

	/**
	 * Sets the http status.
	 * 
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus)
	{
		this.httpStatus = httpStatus;
	}

}
