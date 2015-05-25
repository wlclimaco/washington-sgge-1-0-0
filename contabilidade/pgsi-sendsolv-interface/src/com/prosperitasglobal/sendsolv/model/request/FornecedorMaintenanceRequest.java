package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Fornecedor;

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