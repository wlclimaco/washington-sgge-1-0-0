package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.Fornecedor;

public class FornecedorMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Fornecedor fornecedor;

	/**
	 * The Constructor.
	 */
	public FornecedorMaintenanceRequest()
	{

	}

	/**
	 * @return the fornecedor
	 */
	public Fornecedor getFornecedor()
	{
		return fornecedor;
	}

	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor(Fornecedor fornecedor)
	{
		this.fornecedor = fornecedor;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FornecedorMaintenanceRequest [getFornecedor()=" + getFornecedor() + ", getUserContext()="
				+ getUserContext()
				+ "]";
	}

}