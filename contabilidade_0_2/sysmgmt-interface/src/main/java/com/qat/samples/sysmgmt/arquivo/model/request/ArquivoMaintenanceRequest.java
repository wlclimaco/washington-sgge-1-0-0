package com.qat.samples.sysmgmt.arquivo.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.arquivo.model.Arquivo;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ArquivoMaintenanceRequest extends UtilMaintenanceRequest
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