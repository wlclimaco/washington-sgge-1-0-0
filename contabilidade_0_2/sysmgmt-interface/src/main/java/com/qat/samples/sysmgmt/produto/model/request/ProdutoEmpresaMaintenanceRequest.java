package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.ProdutoEmpresa;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ProdutoEmpresaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private ProdutoEmpresa produtoEmpresa;

	/**
	 * The Constructor.
	 */
	public ProdutoEmpresaMaintenanceRequest()
	{

	}

	public ProdutoEmpresa getProdutoEmpresa() {
		return produtoEmpresa;
	}

	public void setProdutoEmpresa(ProdutoEmpresa produtoEmpresa) {
		this.produtoEmpresa = produtoEmpresa;
	}

	@Override
	public String toString() {
		return "ProdutoEmpresaMaintenanceRequest [getProdutoEmpresa()=" + getProdutoEmpresa() + ", toString()="
				+ super.toString() + "]";
	}

	
}