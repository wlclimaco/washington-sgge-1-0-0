package com.qat.samples.sysmgmt.financeiro.model.request;

import com.qat.samples.sysmgmt.financeiro.model.BaixaTitulo;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class BaixaTituloMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private BaixaTitulo baixaTitulo;

	/**
	 * The Constructor.
	 */
	public BaixaTituloMaintenanceRequest()
	{

	}

	public BaixaTitulo getBaixaTitulo() {
		return baixaTitulo;
	}

	public void setBaixaTitulo(BaixaTitulo baixaTitulo) {
		this.baixaTitulo = baixaTitulo;
	}

	@Override
	public String toString() {
		return "BaixaTituloMaintenanceRequest [getBaixaTitulo()=" + getBaixaTitulo() + ", toString()="
				+ super.toString() + "]";
	}

	
}