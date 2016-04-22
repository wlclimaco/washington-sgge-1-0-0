package com.qat.samples.sysmgmt.arquivo.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.arquivo.model.Arquivo;

public class ArquivoMaintenanceRequest extends Request
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

	@Override
	public String toString() {
		return "ArquivoMaintenanceRequest [getArquivo()=" + getArquivo() + ", toString()=" + super.toString() + "]";
	}

}