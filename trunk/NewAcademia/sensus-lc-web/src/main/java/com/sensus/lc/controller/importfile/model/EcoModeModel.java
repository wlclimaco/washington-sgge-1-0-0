package com.sensus.lc.controller.importfile.model;

import com.sensus.lc.foto.model.Foto;

/**
 * The Class EcoModeModel.
 */
public class EcoModeModel
{

	/** The operation success. */
	private Boolean operationSuccess;

	/** The message code. */
	private String messageCode;

	/** The arguments. */
	private String arguments;

	/** The foto. */
	private Foto foto;

	/**
	 * Gets the operation success.
	 * 
	 * @return the operation success
	 */
	public Boolean getOperationSuccess()
	{
		return operationSuccess;
	}

	/**
	 * Sets the operation success.
	 * 
	 * @param operationSuccess the new operation success
	 */
	public void setOperationSuccess(Boolean operationSuccess)
	{
		this.operationSuccess = operationSuccess;
	}

	/**
	 * Gets the message code.
	 * 
	 * @return the message code
	 */
	public String getMessageCode()
	{
		return messageCode;
	}

	/**
	 * Sets the message code.
	 * 
	 * @param messageCode the new message code
	 */
	public void setMessageCode(String messageCode)
	{
		this.messageCode = messageCode;
	}

	/**
	 * Gets the arguments.
	 * 
	 * @return the arguments
	 */
	public String getArguments()
	{
		return arguments;
	}

	/**
	 * Sets the arguments.
	 * 
	 * @param arguments the new arguments
	 */
	public void setArguments(String arguments)
	{
		this.arguments = arguments;
	}

	/**
	 * Gets the foto.
	 * 
	 * @return the foto
	 */
	public Foto getFoto()
	{
		return foto;
	}

	/**
	 * Sets the foto.
	 * 
	 * @param foto the new foto
	 */
	public void setFoto(Foto foto)
	{
		this.foto = foto;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EcoModeModel [getOperationSuccess()=" + getOperationSuccess() + ", getMessageCode()="
				+ getMessageCode() + ", getArguments()=" + getArguments() + ", getFoto()=" + getFoto()
				+ ", toString()=" + super.toString() + "]";
	}

}