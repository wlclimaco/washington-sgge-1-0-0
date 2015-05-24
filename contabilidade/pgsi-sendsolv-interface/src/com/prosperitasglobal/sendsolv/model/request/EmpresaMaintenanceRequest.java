package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Empresa;
import com.qat.framework.model.request.MaintenanceRequest;

/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class EmpresaMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EmpresaMaintenanceRequest [getEmpresa()=" + getEmpresa() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}