package com.qat.samples.sysmgmt.dp.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class FuncionarioMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "FuncionarioMaintenanceRequest [getFuncionario()=" + getFuncionario() + ", toString()="
				+ super.toString() + "]";
	}

}