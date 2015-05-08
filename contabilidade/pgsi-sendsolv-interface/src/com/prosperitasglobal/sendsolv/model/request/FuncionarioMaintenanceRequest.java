package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.qat.framework.model.request.MaintenanceRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class FuncionarioMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Funcionario funcionario;

	/**
	 * The Constructor.
	 */
	public FuncionarioMaintenanceRequest()
	{

	}

	/**
	 * Gets the funcionario.
	 *
	 * @return the funcionario
	 */
	public Funcionario getFuncionario()
	{
		return funcionario;
	}

	/**
	 * Sets the funcionario.
	 *
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario)
	{
		this.funcionario = funcionario;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FuncionarioMaintenanceRequest [getFuncionario()=" + getFuncionario() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}