package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Produto;

/**
 * The Class ProdutoMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 2:15:09 PM
 */
public class ProdutoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Produto produto;

	/**
	 * The Constructor.
	 */
	public ProdutoMaintenanceRequest()
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