package com.qat.samples.sysmgmt.Dto;

import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ProdutoMaintenanceRequest extends UtilMaintenanceRequest {

	/** Attributes. */
	private ProdutoDto produto;

	/**
	 * The Constructor.
	 */
	public ProdutoMaintenanceRequest() {

	}

	/**
	 * Gets the produto.
	 *
	 * @return the produto
	 */
	public ProdutoDto getProduto() {
		return produto;
	}

	/**
	 * Sets the produto.
	 *
	 * @param produto
	 *            the produto
	 */
	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ProdutoMaintenanceRequest [getProduto()=" + getProduto() + ", toString()=" + super.toString() + "]";
	}
}