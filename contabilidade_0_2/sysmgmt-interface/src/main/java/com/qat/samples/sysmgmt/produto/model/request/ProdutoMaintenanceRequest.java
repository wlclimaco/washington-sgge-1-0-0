package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ProdutoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "ProdutoMaintenanceRequest [getProduto()=" + getProduto() + ", toString()=" + super.toString() + "]";
	}
}