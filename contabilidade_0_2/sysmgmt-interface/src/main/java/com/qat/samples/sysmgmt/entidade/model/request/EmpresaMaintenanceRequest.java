package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class EmpresaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Empresa empresa;

	/**
	 * The Constructor.
	 */
	public EmpresaMaintenanceRequest()
	{

	}

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

	@Override
	public String toString() {
		return "EmpresaMaintenanceRequest [getEmpresa()=" + getEmpresa() + ", toString()=" + super.toString() + "]";
	}

}