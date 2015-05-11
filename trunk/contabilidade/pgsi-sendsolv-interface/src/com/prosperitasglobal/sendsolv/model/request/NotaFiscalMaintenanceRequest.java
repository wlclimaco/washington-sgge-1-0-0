package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Produto;
import com.qat.framework.model.request.MaintenanceRequest;

public class NotaFiscalMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Produto produto;

	/**
	 * The Constructor.
	 */
	public NotaFiscalMaintenanceRequest()
	{

	}

	/**
	 * Gets the produto.
	 *
	 * @return the produto
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * Sets the produto.
	 *
	 * @param produto the produto
	 */
	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoMaintenanceRequest [getProduto()=" + getProduto() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}