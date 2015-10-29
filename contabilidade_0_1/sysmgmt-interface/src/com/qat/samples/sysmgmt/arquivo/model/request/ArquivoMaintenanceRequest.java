package com.qat.samples.sysmgmt.arquivo.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.Arquivo;

public class ArquivoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Arquivo arquivo;

	/**
	 * The Constructor.
	 */
	public ArquivoMaintenanceRequest()
	{

	}

	/**
	 * @return the arquivo
	 */
	public Arquivo getArquivo()
	{
		return arquivo;
	}

	/**
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(Arquivo arquivo)
	{
		this.arquivo = arquivo;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ArquivoMaintenanceRequest [getArquivo()=" + getArquivo() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}