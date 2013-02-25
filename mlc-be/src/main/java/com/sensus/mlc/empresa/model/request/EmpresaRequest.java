package com.sensus.mlc.empresa.model.request;

import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.empresa.model.Empresa;

/**
 * The Model Object Action.
 * 
 * @author - QAT Brazil
 */
public class EmpresaRequest extends LightSelectionRequest
{

	/** The action. */
	private Empresa empresa;

	/** The parent retry process. */
	private Integer parentRetry;

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa()
	{
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa)
	{
		this.empresa = empresa;
	}

	/**
	 * @return the parentRetry
	 */
	public Integer getParentRetry()
	{
		return parentRetry;
	}

	/**
	 * @param parentRetry the parentRetry to set
	 */
	public void setParentRetry(Integer parentRetry)
	{
		this.parentRetry = parentRetry;
	}

}
