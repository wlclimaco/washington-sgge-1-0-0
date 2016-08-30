package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Categoria;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CategoriaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Categoria categoria;

	/**
	 * The Constructor.
	 */
	public CategoriaMaintenanceRequest()
	{

	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "CategoriaMaintenanceRequest [getCategoria()=" + getCategoria() + ", toString()=" + super.toString()
				+ "]";
	}

	
}