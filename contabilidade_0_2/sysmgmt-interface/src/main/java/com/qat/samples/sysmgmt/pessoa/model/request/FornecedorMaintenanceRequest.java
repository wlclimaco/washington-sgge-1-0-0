package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class FornecedorMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "FornecedorMaintenanceRequest [getFornecedor()=" + getFornecedor() + ", toString()=" + super.toString()
				+ "]";
	}

}